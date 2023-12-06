package com.spongzi.subject.domain.service.impl;

import com.alibaba.fastjson.JSON;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.spongzi.club.common.enums.IsDeletedEnum;
import com.spongzi.subject.domain.convert.SubjectCategoryConvert;
import com.spongzi.subject.domain.convert.SubjectLabelConvert;
import com.spongzi.subject.domain.entity.SubjectCategoryBO;
import com.spongzi.subject.domain.entity.SubjectLabelBO;
import com.spongzi.subject.domain.service.SubjectCategoryDomainService;
import com.spongzi.subject.infra.basic.entity.SubjectCategory;
import com.spongzi.subject.infra.basic.entity.SubjectLabel;
import com.spongzi.subject.infra.basic.entity.SubjectMapping;
import com.spongzi.subject.infra.basic.service.SubjectCategoryService;
import com.spongzi.subject.infra.basic.service.SubjectLabelService;
import com.spongzi.subject.infra.basic.service.SubjectMappingService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * 主题类别域服务实施
 *
 * @author spong
 * @date 2023/10/06
 */
@Slf4j
@Service
public class SubjectCategoryDomainServiceImpl implements SubjectCategoryDomainService {

    @Resource
    private SubjectCategoryService subjectCategoryService;

    @Resource
    private SubjectMappingService subjectMappingService;

    @Resource
    private SubjectLabelService subjectLabelService;

    @Resource
    private ThreadPoolExecutor labelThreadPool;

    private Cache<String, String> localCache = CacheBuilder.newBuilder()
            .maximumSize(5000)
            .expireAfterWrite(10, TimeUnit.SECONDS)
            .build();


    @Override
    public void add(SubjectCategoryBO subjectCategoryBO) {
        if (log.isInfoEnabled()) {
            log.info("SubjectCategoryDomainServiceImpl.add.bo: {}", JSON.toJSONString(subjectCategoryBO));
        }
        SubjectCategory subjectCategory = SubjectCategoryConvert.INSTANCE.convertBoToEntity(subjectCategoryBO);
        subjectCategory.setIsDeleted(IsDeletedEnum.UN_DELETED.getCode());
        subjectCategoryService.insert(subjectCategory);
    }

    @Override
    public List<SubjectCategoryBO> queryCategory(SubjectCategoryBO subjectCategoryBO) {
        SubjectCategory subjectCategory = SubjectCategoryConvert.INSTANCE.convertBoToEntity(subjectCategoryBO);
        subjectCategory.setIsDeleted(IsDeletedEnum.UN_DELETED.getCode());
        List<SubjectCategory> subjectCategoryList = subjectCategoryService.queryCategory(subjectCategory);
        List<SubjectCategoryBO> subjectCategoryBOList = SubjectCategoryConvert.INSTANCE.convertEntityListToBoList(subjectCategoryList);
        if (log.isInfoEnabled()) {
            log.info("SubjectCategoryDomainServiceImpl.queryPrimaryCategory.subjectCategoryBOList: {}", JSON.toJSONString(subjectCategoryBOList));
        }
        subjectCategoryBOList.forEach(bo -> {
            Integer subjectCount = subjectCategoryService.querySubjectCount(bo.getId());
            bo.setCount(subjectCount);
        });
        return subjectCategoryBOList;
    }

    @Override
    public Boolean update(SubjectCategoryBO subjectCategoryBO) {
        SubjectCategory subjectCategory = SubjectCategoryConvert.INSTANCE.convertBoToEntity(subjectCategoryBO);
        int count = subjectCategoryService.update(subjectCategory);
        return count > 0;
    }

    @Override
    public Boolean delete(SubjectCategoryBO subjectCategoryBO) {
        SubjectCategory subjectCategory = SubjectCategoryConvert.INSTANCE.convertBoToEntity(subjectCategoryBO);
        subjectCategory.setIsDeleted(IsDeletedEnum.DELETED.getCode());
        int count = subjectCategoryService.update(subjectCategory);
        return count > 0;
    }

    @SneakyThrows
    @Override
    public List<SubjectCategoryBO> queryCategoryAndLabel(SubjectCategoryBO subjectCategoryBO) {
        String cacheKey = "categoryAndLabel." + subjectCategoryBO.getId();
        String content = localCache.getIfPresent(cacheKey);
        List<SubjectCategoryBO> subjectCategoryBOS;
        if (StringUtils.isBlank(content)) {
            subjectCategoryBOS = getSubjectCategoryBOS(subjectCategoryBO.getId());
            // 缓存为空查完数据之后，需要把数据放入本地缓存中
            localCache.put(cacheKey, JSON.toJSONString(subjectCategoryBOS));
            return subjectCategoryBOS;
        }
        subjectCategoryBOS = JSON.parseArray(content, SubjectCategoryBO.class);
        return subjectCategoryBOS;
    }

    /**
     * 获取主题类别Bos
     *
     * @param categoryId 类别ID
     * @return {@link List}<{@link SubjectCategoryBO}>
     */
    private List<SubjectCategoryBO> getSubjectCategoryBOS(Long categoryId) {
        // 1. 查出大类下所有分类
        SubjectCategory subjectCategory = new SubjectCategory();
        subjectCategory.setParentId(categoryId);
        subjectCategory.setIsDeleted(IsDeletedEnum.UN_DELETED.getCode());
        List<SubjectCategory> subjectCategoryList = subjectCategoryService.queryCategory(subjectCategory);
        if (log.isInfoEnabled()) {
            log.info("SubjectCategoryDomainServiceImpl.queryCategoryAndLabel.subjectCategoryList: {}",
                    JSON.toJSONString(subjectCategoryList));
        }
        List<SubjectCategoryBO> categoryBOList = SubjectCategoryConvert
                .INSTANCE
                .convertEntityListToBoList(subjectCategoryList);

        Map<Long, List<SubjectLabelBO>> map = new HashMap<>();

        List<CompletableFuture<Map<Long, List<SubjectLabelBO>>>> completableFutureList = categoryBOList.stream().map(category ->
                CompletableFuture.supplyAsync(() -> getLabelBOList(category), labelThreadPool)
        ).collect(Collectors.toList());

        completableFutureList.forEach(future -> {
            Map<Long, List<SubjectLabelBO>> resultMap = null;
            try {
                resultMap = future.get();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            map.putAll(resultMap);
        });

        // 组装数据
        categoryBOList.forEach(categoryBO -> {
            List<SubjectLabelBO> labelBOList = map.get(categoryBO.getId());
            categoryBO.setLabelBOList(labelBOList);
        });

        return categoryBOList;
    }

    /**
     * 获取标签词组列表
     *
     * @param categoryBO 类别bo
     * @return {@link List}<{@link SubjectLabelBO}>
     */
    private Map<Long, List<SubjectLabelBO>> getLabelBOList(SubjectCategoryBO categoryBO) {
        if (log.isInfoEnabled()) {
            log.info("getLabelBOList: {}", JSON.toJSONString(categoryBO));
        }
        Map<Long, List<SubjectLabelBO>> labelMap = new HashMap<>();
        SubjectMapping subjectMapping = new SubjectMapping();
        subjectMapping.setCategoryId(categoryBO.getId());
        List<SubjectMapping> mappingList = subjectMappingService.queryLabelId(subjectMapping);
        if (CollectionUtils.isEmpty(mappingList)) {
            return null;
        }
        List<Long> labelIdList = mappingList.stream().map(SubjectMapping::getLabelId).collect(Collectors.toList());
        List<SubjectLabel> labelList = subjectLabelService.batchQueryById(labelIdList);
        List<SubjectLabelBO> labelBOList = new LinkedList<>();
        labelList.forEach(label -> {
            SubjectLabelBO labelBO = SubjectLabelConvert.INSTANCE.convertEntityToBo(label);
            labelBOList.add(labelBO);
        });
        labelMap.put(categoryBO.getId(), labelBOList);
        return labelMap;
    }
}

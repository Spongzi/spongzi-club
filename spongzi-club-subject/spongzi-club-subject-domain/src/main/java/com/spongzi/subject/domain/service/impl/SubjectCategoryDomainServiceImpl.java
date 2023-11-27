package com.spongzi.subject.domain.service.impl;

import com.alibaba.fastjson.JSON;
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
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;
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

    @Override
    public List<SubjectCategoryBO> queryCategoryAndLabel(SubjectCategoryBO subjectCategoryBO) {
        // 1. 查出大类下所有分类
        SubjectCategory subjectCategory = new SubjectCategory();
        subjectCategory.setParentId(subjectCategoryBO.getId());
        subjectCategory.setIsDeleted(IsDeletedEnum.UN_DELETED.getCode());
        List<SubjectCategory> subjectCategoryList = subjectCategoryService.queryCategory(subjectCategory);
        if (log.isInfoEnabled()) {
            log.info("SubjectCategoryDomainServiceImpl.queryCategoryAndLabel.subjectCategoryList: {}",
                    JSON.toJSONString(subjectCategoryList));
        }
        List<SubjectCategoryBO> categoryBOList = SubjectCategoryConvert
                .INSTANCE
                .convertEntityListToBoList(subjectCategoryList);
        // 2. 依次获取标签信息
        categoryBOList.forEach(categoryBO -> {
            SubjectMapping subjectMapping = new SubjectMapping();
            subjectMapping.setCategoryId(categoryBO.getId());
            List<SubjectMapping> mappingList = subjectMappingService.queryLabelId(subjectMapping);
            if (CollectionUtils.isEmpty(mappingList)) {
                return;
            }
            List<Long> labelIdList = mappingList.stream().map(SubjectMapping::getLabelId).collect(Collectors.toList());
            List<SubjectLabel> labelList = subjectLabelService.batchQueryById(labelIdList);
            List<SubjectLabelBO> labelBOList = new LinkedList<>();
            labelList.forEach(label -> {
                SubjectLabelBO labelBO = SubjectLabelConvert.INSTANCE.convertEntityToBo(label);
                labelBOList.add(labelBO);
            });
            categoryBO.setLabelBOList(labelBOList);
        });
        return categoryBOList;
    }
}

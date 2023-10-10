package com.spongzi.subject.domain.service.impl;

import com.alibaba.fastjson.JSON;
import com.spongzi.subject.common.entity.PageResult;
import com.spongzi.subject.common.enums.IsDeletedEnum;
import com.spongzi.subject.domain.convert.SubjectInfoConvert;
import com.spongzi.subject.domain.entity.SubjectInfoBO;
import com.spongzi.subject.domain.entity.SubjectOptionBO;
import com.spongzi.subject.domain.handler.subject.SubjectHandlerTypeFactory;
import com.spongzi.subject.domain.handler.subject.SubjectTypeHandler;
import com.spongzi.subject.domain.service.SubjectInfoDomainService;
import com.spongzi.subject.infra.basic.entity.SubjectInfo;
import com.spongzi.subject.infra.basic.entity.SubjectLabel;
import com.spongzi.subject.infra.basic.entity.SubjectMapping;
import com.spongzi.subject.infra.basic.service.SubjectInfoService;
import com.spongzi.subject.infra.basic.service.SubjectLabelService;
import com.spongzi.subject.infra.basic.service.SubjectMappingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 题目信息服务
 *
 * @author spong
 * @date 2023/10/08
 */
@Slf4j
@Service
public class SubjectInfoDomainServiceImpl implements SubjectInfoDomainService {

    @Resource
    private SubjectInfoService subjectInfoService;

    @Resource
    private SubjectMappingService subjectMappingService;

    @Resource
    private SubjectLabelService subjectLabelService;

    @Resource
    private SubjectHandlerTypeFactory subjectHandlerTypeFactory;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean add(SubjectInfoBO subjectInfoBO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("SubjectInfoDomainServiceImpl.add.bo: {}", JSON.toJSONString(subjectInfoBO));
            }
            // 使用工厂策略模式，根据传入的type来自动选择对应的映射处理
            SubjectInfo subjectInfo = SubjectInfoConvert.INSTANCE.convertBoToEntity(subjectInfoBO);
            subjectInfo.setIsDeleted(IsDeletedEnum.UN_DELETED.getCode());
            subjectInfoService.insert(subjectInfo);
            SubjectTypeHandler handler = subjectHandlerTypeFactory.getHandler(subjectInfo.getSubjectType());
            subjectInfoBO.setId(subjectInfo.getId());
            handler.add(subjectInfoBO);
            List<SubjectMapping> mappingList = getSubjectMappings(subjectInfoBO, subjectInfo);
            subjectMappingService.insertBatch(mappingList);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private static List<SubjectMapping> getSubjectMappings(SubjectInfoBO subjectInfoBO, SubjectInfo subjectInfo) {
        List<Long> categoryIds = subjectInfoBO.getCategoryIds();
        List<Long> labelIds = subjectInfoBO.getLabelIds();
        List<SubjectMapping> mappingList = new LinkedList<>();
        categoryIds.forEach(categoryId -> {
            labelIds.forEach(labelId -> {
                SubjectMapping subjectMapping = new SubjectMapping();
                subjectMapping.setSubjectId(subjectInfo.getId());
                subjectMapping.setCategoryId(categoryId);
                subjectMapping.setLabelId(labelId);
                subjectMapping.setIsDeleted(IsDeletedEnum.UN_DELETED.getCode());
                mappingList.add(subjectMapping);
            });
        });
        return mappingList;
    }

    @Override
    public PageResult<SubjectInfoBO> getSubjectPage(SubjectInfoBO subjectInfoBO) {
        PageResult<SubjectInfoBO> pageResult = new PageResult<>();
        pageResult.setPageNo(subjectInfoBO.getPageNo());
        pageResult.setPageSize(subjectInfoBO.getPageSize());
        SubjectInfo subjectInfo = SubjectInfoConvert.INSTANCE.convertBoToEntity(subjectInfoBO);
        Integer categoryId = subjectInfoBO.getCategoryId();
        Integer labelId = subjectInfoBO.getLabelId();
        int start = (subjectInfoBO.getPageNo() - 1) * subjectInfoBO.getPageSize();
        int count = subjectInfoService.countByCondition(subjectInfo, categoryId, labelId);
        if (count == 0) {
            return pageResult;
        }
        List<SubjectInfo> subjectInfoList = subjectInfoService.queryPage(subjectInfo, categoryId, labelId, start,
                subjectInfoBO.getPageNo(), subjectInfoBO.getPageSize());
        List<SubjectInfoBO> subjectInfoBOList = SubjectInfoConvert.INSTANCE.convertEntityListToBoList(subjectInfoList);
        pageResult.setRecords(subjectInfoBOList);
        return pageResult;
    }

    @Override
    public SubjectInfoBO querySubjectInfo(SubjectInfoBO subjectInfoBO) {
        SubjectInfo subjectInfo = subjectInfoService.queryById(subjectInfoBO.getId());
        Integer subjectType = subjectInfo.getSubjectType();
        SubjectTypeHandler handler = subjectHandlerTypeFactory.getHandler(subjectType);
        SubjectOptionBO optionBO = handler.query(subjectInfo.getId());
        SubjectInfoBO boResult = SubjectInfoConvert.INSTANCE.convertOptionAndEntityToBO(optionBO, subjectInfo);
        SubjectMapping subjectMapping = new SubjectMapping();
        subjectMapping.setSubjectId(subjectInfo.getId());
        subjectMapping.setIsDeleted(IsDeletedEnum.UN_DELETED.getCode());
        List<SubjectMapping> subjectMappingList = subjectMappingService.queryLabelId(subjectMapping);
        // TODO 数据判空
        List<Long> labelIdList = subjectMappingList.stream().map(SubjectMapping::getLabelId).collect(Collectors.toList());
        List<SubjectLabel> subjectLabelList = subjectLabelService.batchQueryById(labelIdList);
        List<String> labelNameList = subjectLabelList.stream().map(SubjectLabel::getLabelName).collect(Collectors.toList());
        boResult.setLabelNames(labelNameList);
        return boResult;
    }
}

package com.spongzi.subject.domain.service.impl;

import com.alibaba.fastjson.JSON;
import com.spongzi.club.common.enums.IsDeletedEnum;
import com.spongzi.subject.common.enums.CategoryTypeEnum;
import com.spongzi.subject.domain.convert.SubjectLabelConvert;
import com.spongzi.subject.domain.entity.SubjectLabelBO;
import com.spongzi.subject.domain.service.SubjectLabelDomainService;
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
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class SubjectLabelDomainServiceImpl implements SubjectLabelDomainService {

    @Resource
    private SubjectLabelService subjectLabelService;

    @Resource
    private SubjectMappingService subjectMappingService;

    @Resource
    private SubjectCategoryService subjectCategoryService;

    @Override
    public Boolean add(SubjectLabelBO subjectLabelBO) {
        if (log.isInfoEnabled()) {
            log.info("SubjectLabelDomainServiceImpl.add.bo: {}", JSON.toJSONString(subjectLabelBO));
        }
        SubjectLabel subjectLabel = SubjectLabelConvert.INSTANCE.convertBoToEntity(subjectLabelBO);
        subjectLabel.setIsDeleted(IsDeletedEnum.UN_DELETED.getCode());
        int count = subjectLabelService.insert(subjectLabel);
        return count > 0;
    }

    @Override
    public Boolean update(SubjectLabelBO subjectLabelBO) {
        if (log.isInfoEnabled()) {
            log.info("SubjectLabelDomainServiceImpl.update.bo: {}", JSON.toJSONString(subjectLabelBO));
        }
        SubjectLabel subjectLabel = SubjectLabelConvert.INSTANCE.convertBoToEntity(subjectLabelBO);
        subjectLabel.setIsDeleted(IsDeletedEnum.UN_DELETED.getCode());
        int count = subjectLabelService.update(subjectLabel);
        return count > 0;
    }

    @Override
    public Boolean delete(SubjectLabelBO subjectLabelBO) {
        if (log.isInfoEnabled()) {
            log.info("SubjectLabelDomainServiceImpl.delete.bo: {}", JSON.toJSONString(subjectLabelBO));
        }
        SubjectLabel subjectLabel = SubjectLabelConvert.INSTANCE.convertBoToEntity(subjectLabelBO);
        subjectLabel.setIsDeleted(IsDeletedEnum.DELETED.getCode());
        int count = subjectLabelService.update(subjectLabel);
        return count > 0;
    }

    @Override
    public List<SubjectLabelBO> queryLabelByCategoryId(SubjectLabelBO subjectLabelBO) {
        if (log.isInfoEnabled()) {
            log.info("SubjectLabelDomainServiceImpl.queryLabelByCategoryId.bo: {}", JSON.toJSONString(subjectLabelBO));
        }
        // 如果当前分类是一级分类，则查询所有标签
        SubjectCategory subjectCategory = subjectCategoryService.queryById(subjectLabelBO.getCategoryId());
        if (CategoryTypeEnum.PRIMARY.getCode() == subjectCategory.getCategoryType()) {
            SubjectLabel subjectLabel = new SubjectLabel();
            subjectLabel.setCategoryId(subjectLabelBO.getCategoryId());
            List<SubjectLabel> labelList = subjectLabelService.queryByCondition(subjectLabel);
            return SubjectLabelConvert.INSTANCE.convertEntityListToBoList(labelList);
        }
        Long categoryId = subjectLabelBO.getCategoryId();
        SubjectMapping subjectMapping = new SubjectMapping();
        subjectMapping.setCategoryId(categoryId);
        subjectMapping.setIsDeleted(IsDeletedEnum.UN_DELETED.getCode());
        List<SubjectMapping> subjectMappingList = subjectMappingService.queryLabelId(subjectMapping);
        if (CollectionUtils.isEmpty(subjectMappingList)) {
            return Collections.emptyList();
        }
        List<Long> labelIdList = subjectMappingList.stream().map(SubjectMapping::getLabelId).collect(Collectors.toList());
        List<SubjectLabel> subjectLabelList = subjectLabelService.batchQueryById(labelIdList);
        List<SubjectLabelBO> boList = new LinkedList<>();
        subjectLabelList.forEach(label -> {
            SubjectLabelBO bo = new SubjectLabelBO();
            bo.setLabelName(label.getLabelName());
            bo.setId(label.getId());
            bo.setCategoryId(categoryId);
            boList.add(bo);
        });
        return boList;
    }
}

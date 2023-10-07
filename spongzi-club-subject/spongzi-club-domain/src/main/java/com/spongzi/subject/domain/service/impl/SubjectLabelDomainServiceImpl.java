package com.spongzi.subject.domain.service.impl;

import com.alibaba.fastjson.JSON;
import com.spongzi.subject.common.enums.IsDeletedEnum;
import com.spongzi.subject.domain.convert.SubjectCategoryConvert;
import com.spongzi.subject.domain.convert.SubjectLabelConvert;
import com.spongzi.subject.domain.entity.SubjectLabelBO;
import com.spongzi.subject.domain.service.SubjectLabelDomainService;
import com.spongzi.subject.infra.basic.entity.SubjectCategory;
import com.spongzi.subject.infra.basic.entity.SubjectLabel;
import com.spongzi.subject.infra.basic.service.SubjectLabelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Slf4j
@Service
public class SubjectLabelDomainServiceImpl implements SubjectLabelDomainService {

    @Resource
    private SubjectLabelService subjectLabelService;

    @Override
    public void add(SubjectLabelBO subjectLabelBO) {
        if (log.isInfoEnabled()) {
            log.info("SubjectLabelDomainServiceImpl.add.bo: {}", JSON.toJSONString(subjectLabelBO));
        }
        SubjectLabel subjectLabel = SubjectLabelConvert.INSTANCE.convertBoToSubjectLabel(subjectLabelBO);
        subjectLabel.setIsDeleted(IsDeletedEnum.UN_DELETED.getCode());
        subjectLabelService.insert(subjectLabel);
    }
}

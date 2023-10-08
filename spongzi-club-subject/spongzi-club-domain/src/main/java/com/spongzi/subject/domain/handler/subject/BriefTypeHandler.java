package com.spongzi.subject.domain.handler.subject;

import com.spongzi.subject.common.enums.IsDeletedEnum;
import com.spongzi.subject.common.enums.SubjectInfoTypeEnum;
import com.spongzi.subject.domain.convert.SubjectBriefConvert;
import com.spongzi.subject.domain.convert.SubjectRadioConvert;
import com.spongzi.subject.domain.entity.SubjectAnswerBO;
import com.spongzi.subject.domain.entity.SubjectInfoBO;
import com.spongzi.subject.infra.basic.entity.SubjectBrief;
import com.spongzi.subject.infra.basic.entity.SubjectRadio;
import com.spongzi.subject.infra.basic.service.SubjectBriefService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

/**
 * 简答题目的策略类型
 *
 * @author spong
 * @date 2023/10/08
 */
@Component
public class BriefTypeHandler implements SubjectTypeHandler {

    @Resource
    private SubjectBriefService subjectBriefService;

    @Override
    public SubjectInfoTypeEnum getHandlerType() {
        return SubjectInfoTypeEnum.BRIEF;
    }

    @Override
    public void add(SubjectInfoBO subjectInfoBO) {
        // 简答题目的插入
        SubjectBrief subjectBrief = SubjectBriefConvert.INSTANCE.convertBoToEntity(subjectInfoBO);
        subjectBrief.setSubjectId(subjectInfoBO.getId());
        subjectBrief.setIsDeleted(IsDeletedEnum.UN_DELETED.getCode());
        subjectBriefService.insert(subjectBrief);
    }
}

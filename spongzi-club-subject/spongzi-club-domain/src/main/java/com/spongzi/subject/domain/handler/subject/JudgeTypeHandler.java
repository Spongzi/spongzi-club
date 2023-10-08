package com.spongzi.subject.domain.handler.subject;

import com.spongzi.subject.common.enums.IsDeletedEnum;
import com.spongzi.subject.common.enums.SubjectInfoTypeEnum;
import com.spongzi.subject.domain.entity.SubjectAnswerBO;
import com.spongzi.subject.domain.entity.SubjectInfoBO;
import com.spongzi.subject.infra.basic.entity.SubjectJudge;
import com.spongzi.subject.infra.basic.service.SubjectJudgeService;

import javax.annotation.Resource;

/**
 * 判断题目的策略类型
 *
 * @author spong
 * @date 2023/10/08
 */
public class JudgeTypeHandler implements SubjectTypeHandler {

    @Resource
    private SubjectJudgeService subjectJudgeService;

    @Override
    public SubjectInfoTypeEnum getHandlerType() {
        return SubjectInfoTypeEnum.JUDGE;
    }

    @Override
    public void add(SubjectInfoBO subjectInfoBO) {
        // 判断题目的插入
        SubjectJudge subjectJudge = new SubjectJudge();
        SubjectAnswerBO subjectAnswerBO = subjectInfoBO.getOptionList().get(0);
        subjectJudge.setSubjectId(subjectInfoBO.getId());
        subjectJudge.setIsCorrect(subjectAnswerBO.getIsCorrect());
        subjectJudge.setIsDeleted(IsDeletedEnum.UN_DELETED.getCode());
        subjectJudgeService.insert(subjectJudge);
    }
}

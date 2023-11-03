package com.spongzi.subject.domain.handler.subject;

import com.spongzi.club.common.enums.IsDeletedEnum;
import com.spongzi.subject.common.enums.SubjectInfoTypeEnum;
import com.spongzi.subject.domain.convert.SubjectJudgeConvert;
import com.spongzi.subject.domain.entity.SubjectAnswerBO;
import com.spongzi.subject.domain.entity.SubjectInfoBO;
import com.spongzi.subject.domain.entity.SubjectOptionBO;
import com.spongzi.subject.infra.basic.entity.SubjectJudge;
import com.spongzi.subject.infra.basic.service.SubjectJudgeService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * 判断题目的策略类型
 *
 * @author spong
 * @date 2023/10/08
 */
@Component
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

    @Override
    public SubjectOptionBO query(Long subjectId) {
        SubjectJudge subjectJudge = new SubjectJudge();
        subjectJudge.setSubjectId(subjectId);
        List<SubjectJudge> result = subjectJudgeService.queryByCondition(subjectJudge);
        List<SubjectAnswerBO> subjectAnswerBOList = SubjectJudgeConvert.INSTANCE.convertEntityListToAnswerBoList(result);
        SubjectOptionBO subjectOptionBO = new SubjectOptionBO();
        subjectOptionBO.setOptionList(subjectAnswerBOList);
        return subjectOptionBO;
    }
}

package com.spongzi.subject.domain.convert;

import com.spongzi.subject.domain.entity.SubjectAnswerBO;
import com.spongzi.subject.domain.entity.SubjectLabelBO;
import com.spongzi.subject.infra.basic.entity.SubjectJudge;
import com.spongzi.subject.infra.basic.entity.SubjectLabel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 主题标签转换
 *
 * @author spong
 * @date 2023/10/07
 */
@Mapper
public interface SubjectJudgeConvert {
    SubjectJudgeConvert INSTANCE = Mappers.getMapper(SubjectJudgeConvert.class);

    List<SubjectAnswerBO> convertEntityListToAnswerBoList(List<SubjectJudge> subjectJudgeList);
}

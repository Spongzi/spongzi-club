package com.spongzi.subject.domain.convert;

import com.spongzi.subject.domain.entity.SubjectAnswerBO;
import com.spongzi.subject.infra.basic.entity.SubjectMultiple;
import com.spongzi.subject.infra.basic.entity.SubjectRadio;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 单选题类别转换
 *
 * @author spong
 * @date 2023/10/06
 */
@Mapper
public interface SubjectRadioConvert {
    SubjectRadioConvert INSTANCE = Mappers.getMapper(SubjectRadioConvert.class);

    SubjectRadio convertBoToEntity(SubjectAnswerBO subjectAnswerBO);

    List<SubjectAnswerBO> convertEntityListToAnswerBoList(List<SubjectRadio> subjectRadioList);
}

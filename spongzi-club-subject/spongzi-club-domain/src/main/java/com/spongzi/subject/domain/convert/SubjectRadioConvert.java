package com.spongzi.subject.domain.convert;

import com.spongzi.subject.domain.entity.SubjectAnswerBO;
import com.spongzi.subject.infra.basic.entity.SubjectRadio;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

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

}

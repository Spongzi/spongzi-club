package com.spongzi.subject.domain.convert;

import com.spongzi.subject.domain.entity.SubjectAnswerBO;
import com.spongzi.subject.domain.entity.SubjectInfoBO;
import com.spongzi.subject.infra.basic.entity.SubjectBrief;
import com.spongzi.subject.infra.basic.entity.SubjectMultiple;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 单选题类别转换
 *
 * @author spong
 * @date 2023/10/06
 */
@Mapper
public interface SubjectMultipleConvert {
    SubjectMultipleConvert INSTANCE = Mappers.getMapper(SubjectMultipleConvert.class);

    SubjectMultiple convertBoToEntity(SubjectAnswerBO subjectAnswerBO);

}

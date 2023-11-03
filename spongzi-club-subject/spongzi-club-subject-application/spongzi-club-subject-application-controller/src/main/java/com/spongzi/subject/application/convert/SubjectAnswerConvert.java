package com.spongzi.subject.application.convert;

import com.spongzi.subject.application.dto.SubjectAnswerDTO;
import com.spongzi.subject.domain.entity.SubjectAnswerBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SubjectAnswerConvert {
    SubjectAnswerConvert INSTANCE = Mappers.getMapper(SubjectAnswerConvert.class);

    List<SubjectAnswerBO> convertDtoListToBoList(List<SubjectAnswerDTO> subjectAnswerBOList);
}

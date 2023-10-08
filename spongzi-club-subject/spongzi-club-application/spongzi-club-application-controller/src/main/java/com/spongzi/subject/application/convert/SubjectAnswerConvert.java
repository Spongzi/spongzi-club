package com.spongzi.subject.application.convert;

import com.spongzi.subject.application.dto.SubjectAnswerDTO;
import com.spongzi.subject.application.dto.SubjectInfoDTO;
import com.spongzi.subject.domain.entity.SubjectAnswerBO;
import com.spongzi.subject.domain.entity.SubjectInfoBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SubjectAnswerConvert {
    SubjectAnswerConvert INSTANCE = Mappers.getMapper(SubjectAnswerConvert.class);

    SubjectAnswerBO convertDtoToBo(SubjectAnswerDTO subjectAnswerDTO);

    List<SubjectAnswerBO> convertDtoToBo(List<SubjectAnswerDTO> subjectAnswerBOList);

    List<SubjectAnswerDTO> convertBoToDto(List<SubjectAnswerBO> subjectAnswerBOList);
}

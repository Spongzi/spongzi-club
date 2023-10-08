package com.spongzi.subject.application.convert;

import com.spongzi.subject.application.dto.SubjectInfoDTO;
import com.spongzi.subject.domain.entity.SubjectInfoBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SubjectInfoConvert {
    SubjectInfoConvert INSTANCE = Mappers.getMapper(SubjectInfoConvert.class);

    SubjectInfoBO convertDtoToBo(SubjectInfoDTO subjectInfoDTO);

    List<SubjectInfoDTO> convertBoListToDtoList(List<SubjectInfoBO> subjectInfoBOList);
}

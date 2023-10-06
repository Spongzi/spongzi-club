package com.spongzi.subject.application.convert;

import com.spongzi.subject.application.dto.SubjectCategoryDTO;
import com.spongzi.subject.domain.entity.SubjectCategoryBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SubjectCategoryConvert {
    SubjectCategoryConvert INSTANCE = Mappers.getMapper(SubjectCategoryConvert.class);

    SubjectCategoryBO convertBoToCategory(SubjectCategoryDTO subjectCategoryDTO);
}

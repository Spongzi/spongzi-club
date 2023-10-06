package com.spongzi.subject.application.convert;

import com.spongzi.subject.application.dto.SubjectCategoryDTO;
import com.spongzi.subject.domain.entity.SubjectCategoryBO;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-10-06T19:55:25+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_381 (Oracle Corporation)"
)
public class SubjectCategoryConvertImpl implements SubjectCategoryConvert {

    @Override
    public SubjectCategoryBO convertBoToCategory(SubjectCategoryDTO subjectCategoryDTO) {
        if ( subjectCategoryDTO == null ) {
            return null;
        }

        SubjectCategoryBO subjectCategoryBO = new SubjectCategoryBO();

        subjectCategoryBO.setId( subjectCategoryDTO.getId() );
        subjectCategoryBO.setCategoryName( subjectCategoryDTO.getCategoryName() );
        subjectCategoryBO.setCategoryType( subjectCategoryDTO.getCategoryType() );
        subjectCategoryBO.setImageUrl( subjectCategoryDTO.getImageUrl() );
        subjectCategoryBO.setParentId( subjectCategoryDTO.getParentId() );

        return subjectCategoryBO;
    }
}

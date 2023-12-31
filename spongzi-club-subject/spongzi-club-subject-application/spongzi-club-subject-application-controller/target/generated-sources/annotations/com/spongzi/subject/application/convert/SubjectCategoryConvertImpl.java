package com.spongzi.subject.application.convert;

import com.spongzi.subject.application.dto.SubjectCategoryDTO;
import com.spongzi.subject.domain.entity.SubjectCategoryBO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-12-24T16:11:01+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_381 (Oracle Corporation)"
)
public class SubjectCategoryConvertImpl implements SubjectCategoryConvert {

    @Override
    public SubjectCategoryBO convertDtoToBo(SubjectCategoryDTO subjectCategoryDTO) {
        if ( subjectCategoryDTO == null ) {
            return null;
        }

        SubjectCategoryBO subjectCategoryBO = new SubjectCategoryBO();

        subjectCategoryBO.setId( subjectCategoryDTO.getId() );
        subjectCategoryBO.setCategoryName( subjectCategoryDTO.getCategoryName() );
        subjectCategoryBO.setCategoryType( subjectCategoryDTO.getCategoryType() );
        subjectCategoryBO.setImageUrl( subjectCategoryDTO.getImageUrl() );
        subjectCategoryBO.setParentId( subjectCategoryDTO.getParentId() );
        subjectCategoryBO.setCount( subjectCategoryDTO.getCount() );

        return subjectCategoryBO;
    }

    @Override
    public List<SubjectCategoryDTO> convertBoListToDtoList(List<SubjectCategoryBO> subjectCategoryBOList) {
        if ( subjectCategoryBOList == null ) {
            return null;
        }

        List<SubjectCategoryDTO> list = new ArrayList<SubjectCategoryDTO>( subjectCategoryBOList.size() );
        for ( SubjectCategoryBO subjectCategoryBO : subjectCategoryBOList ) {
            list.add( convertBoToDto( subjectCategoryBO ) );
        }

        return list;
    }

    @Override
    public SubjectCategoryDTO convertBoToDto(SubjectCategoryBO bo) {
        if ( bo == null ) {
            return null;
        }

        SubjectCategoryDTO subjectCategoryDTO = new SubjectCategoryDTO();

        subjectCategoryDTO.setId( bo.getId() );
        subjectCategoryDTO.setCategoryName( bo.getCategoryName() );
        subjectCategoryDTO.setCategoryType( bo.getCategoryType() );
        subjectCategoryDTO.setImageUrl( bo.getImageUrl() );
        subjectCategoryDTO.setParentId( bo.getParentId() );
        subjectCategoryDTO.setCount( bo.getCount() );

        return subjectCategoryDTO;
    }
}

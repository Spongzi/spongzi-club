package com.spongzi.subject.domain.convert;

import com.spongzi.subject.domain.entity.SubjectLabelBO;
import com.spongzi.subject.infra.basic.entity.SubjectLabel;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-12-24T16:10:59+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_381 (Oracle Corporation)"
)
public class SubjectLabelConvertImpl implements SubjectLabelConvert {

    @Override
    public SubjectLabel convertBoToEntity(SubjectLabelBO subjectLabelBO) {
        if ( subjectLabelBO == null ) {
            return null;
        }

        SubjectLabel subjectLabel = new SubjectLabel();

        subjectLabel.setId( subjectLabelBO.getId() );
        subjectLabel.setLabelName( subjectLabelBO.getLabelName() );
        subjectLabel.setCategoryId( subjectLabelBO.getCategoryId() );
        subjectLabel.setSortNum( subjectLabelBO.getSortNum() );
        subjectLabel.setIsDeleted( subjectLabelBO.getIsDeleted() );

        return subjectLabel;
    }

    @Override
    public List<SubjectLabelBO> convertEntityListToBoList(List<SubjectLabel> subjectLabelList) {
        if ( subjectLabelList == null ) {
            return null;
        }

        List<SubjectLabelBO> list = new ArrayList<SubjectLabelBO>( subjectLabelList.size() );
        for ( SubjectLabel subjectLabel : subjectLabelList ) {
            list.add( convertEntityToBo( subjectLabel ) );
        }

        return list;
    }

    @Override
    public SubjectLabelBO convertEntityToBo(SubjectLabel label) {
        if ( label == null ) {
            return null;
        }

        SubjectLabelBO subjectLabelBO = new SubjectLabelBO();

        subjectLabelBO.setId( label.getId() );
        subjectLabelBO.setCategoryId( label.getCategoryId() );
        subjectLabelBO.setLabelName( label.getLabelName() );
        subjectLabelBO.setSortNum( label.getSortNum() );
        subjectLabelBO.setIsDeleted( label.getIsDeleted() );

        return subjectLabelBO;
    }
}

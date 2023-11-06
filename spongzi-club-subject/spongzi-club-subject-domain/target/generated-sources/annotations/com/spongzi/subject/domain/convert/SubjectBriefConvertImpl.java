package com.spongzi.subject.domain.convert;

import com.spongzi.subject.domain.entity.SubjectInfoBO;
import com.spongzi.subject.infra.basic.entity.SubjectBrief;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-11-06T09:50:47+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_381 (Oracle Corporation)"
)
public class SubjectBriefConvertImpl implements SubjectBriefConvert {

    @Override
    public SubjectBrief convertBoToEntity(SubjectInfoBO subjectInfoBO) {
        if ( subjectInfoBO == null ) {
            return null;
        }

        SubjectBrief subjectBrief = new SubjectBrief();

        subjectBrief.setId( subjectInfoBO.getId() );
        subjectBrief.setSubjectAnswer( subjectInfoBO.getSubjectAnswer() );
        subjectBrief.setIsDeleted( subjectInfoBO.getIsDeleted() );

        return subjectBrief;
    }
}

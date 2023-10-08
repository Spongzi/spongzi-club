package com.spongzi.subject.domain.convert;

import com.spongzi.subject.domain.entity.SubjectInfoBO;
import com.spongzi.subject.infra.basic.entity.SubjectInfo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 主题类别转换
 *
 * @author spong
 * @date 2023/10/06
 */
@Mapper
public interface SubjectInfoConvert {
    SubjectInfoConvert INSTANCE = Mappers.getMapper(SubjectInfoConvert.class);

    SubjectInfo convertBoToEntity(SubjectInfoBO subjectInfoBO);

    List<SubjectInfoBO> convertEntityListToBoList(List<SubjectInfo> subjectCategoryList);
}

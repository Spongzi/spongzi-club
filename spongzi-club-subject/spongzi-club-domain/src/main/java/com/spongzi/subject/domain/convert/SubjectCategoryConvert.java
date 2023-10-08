package com.spongzi.subject.domain.convert;

import com.spongzi.subject.domain.entity.SubjectCategoryBO;
import com.spongzi.subject.infra.basic.entity.SubjectCategory;
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
public interface SubjectCategoryConvert {
    SubjectCategoryConvert INSTANCE = Mappers.getMapper(SubjectCategoryConvert.class);

    SubjectCategory convertBoToEntity(SubjectCategoryBO subjectCategoryBO);

    List<SubjectCategoryBO> convertEntityListToBoList(List<SubjectCategory> subjectCategoryList);
}

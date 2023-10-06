package com.spongzi.subject.domain.convert;

import com.spongzi.subject.domain.entity.SubjectCategoryBO;
import com.spongzi.subject.infra.basic.entity.SubjectCategory;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 主题类别转换
 *
 * @author spong
 * @date 2023/10/06
 */
@Mapper
public interface SubjectCategoryConvert {
    SubjectCategoryConvert INSTANCE = Mappers.getMapper(SubjectCategoryConvert.class);

    SubjectCategory convertBoToCategory(SubjectCategoryBO subjectCategoryBO);
}

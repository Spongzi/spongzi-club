package com.spongzi.subject.application.convert;

import com.spongzi.subject.application.dto.SubjectCategoryDTO;
import com.spongzi.subject.application.dto.SubjectLabelDTO;
import com.spongzi.subject.domain.entity.SubjectCategoryBO;
import com.spongzi.subject.domain.entity.SubjectLabelBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 主题标签转换
 *
 * @author spong
 * @date 2023/10/07
 */
@Mapper
public interface SubjectLabelConvert {
    SubjectLabelConvert INSTANCE = Mappers.getMapper(SubjectLabelConvert.class);

    SubjectLabelBO convertDtoToBo(SubjectLabelDTO subjectLabelDTO);

    List<SubjectLabelDTO> convertBoToDto(List<SubjectLabelBO> SubjectLabelBOList);
}

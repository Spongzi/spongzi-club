package com.spongzi.subject.domain.service;

import com.spongzi.subject.domain.entity.SubjectLabelBO;

import java.util.List;

public interface SubjectLabelDomainService {
    /**
     * 添加标签
     *
     * @param subjectLabelBO 主题标签BO
     * @return {@link Boolean}
     */
    Boolean add(SubjectLabelBO subjectLabelBO);

    /**
     * 更新标签
     *
     * @param subjectLabelBO 主题标签BO
     * @return {@link Boolean}
     */
    Boolean update(SubjectLabelBO subjectLabelBO);

    /**
     * 删除标签
     *
     * @param subjectLabelBO 主题标签BO
     * @return {@link Boolean}
     */
    Boolean delete(SubjectLabelBO subjectLabelBO);

    /**
     * 查询分类下标签
     *
     * @param subjectLabelBO 主题标签BO
     * @return {@link List}<{@link SubjectLabelBO}>
     */
    List<SubjectLabelBO> queryLabelByCategoryId(SubjectLabelBO subjectLabelBO);
}

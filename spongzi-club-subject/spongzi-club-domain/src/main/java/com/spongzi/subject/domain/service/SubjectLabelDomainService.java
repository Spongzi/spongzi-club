package com.spongzi.subject.domain.service;

import com.spongzi.subject.domain.entity.SubjectLabelBO;

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
}

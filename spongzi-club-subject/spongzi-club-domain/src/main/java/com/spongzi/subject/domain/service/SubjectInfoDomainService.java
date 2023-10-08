package com.spongzi.subject.domain.service;

import com.spongzi.subject.domain.entity.SubjectInfoBO;

/**
 * 题目信息服务
 *
 * @author spong
 * @date 2023/10/08
 */
public interface SubjectInfoDomainService {

    /**
     * 新增题目
     *
     * @param subjectInfoBO 主题信息BO
     * @return {@link Boolean}
     */
    Boolean add(SubjectInfoBO subjectInfoBO);
}

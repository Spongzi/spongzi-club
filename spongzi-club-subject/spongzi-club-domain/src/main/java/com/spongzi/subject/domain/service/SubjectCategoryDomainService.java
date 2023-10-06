package com.spongzi.subject.domain.service;

import com.spongzi.subject.domain.entity.SubjectCategoryBO;
import com.spongzi.subject.infra.basic.entity.SubjectCategory;

/**
 * 主题类域服务
 *
 * @author spong
 * @date 2023/10/06
 */
public interface SubjectCategoryDomainService {

    void add(SubjectCategoryBO subjectCategoryBO);
}

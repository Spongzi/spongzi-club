package com.spongzi.subject.domain.service;

import com.spongzi.subject.domain.entity.SubjectCategoryBO;
import com.spongzi.subject.infra.basic.entity.SubjectCategory;

import java.util.List;

/**
 * 主题类域服务
 *
 * @author spong
 * @date 2023/10/06
 */
public interface SubjectCategoryDomainService {

    /**
     * 新增分类
     *
     * @param subjectCategoryBO 主题类别bo
     */
    void add(SubjectCategoryBO subjectCategoryBO);


    /**
     * 查询类别
     *
     * @param subjectCategoryBO 主题类别bo
     * @return {@link List}<{@link SubjectCategoryBO}>
     */
    List<SubjectCategoryBO> queryCategory(SubjectCategoryBO subjectCategoryBO);
}

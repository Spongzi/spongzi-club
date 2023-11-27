package com.spongzi.subject.domain.service;

import com.spongzi.subject.domain.entity.SubjectCategoryBO;

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

    /**
     * 更新分类
     *
     * @param subjectCategoryBO 主题类别bo
     * @return {@link Boolean}
     */
    Boolean update(SubjectCategoryBO subjectCategoryBO);

    /**
     * 删除分类
     *
     * @param subjectCategoryBO 主题类别bo
     * @return {@link Boolean}
     */
    Boolean delete(SubjectCategoryBO subjectCategoryBO);

    /**
     * 查询类别和标签
     *
     * @param subjectCategoryBO 主题类别bo
     * @return {@link List}<{@link SubjectCategoryBO}>
     */
    List<SubjectCategoryBO> queryCategoryAndLabel(SubjectCategoryBO subjectCategoryBO);
}

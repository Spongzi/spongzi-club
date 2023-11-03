package com.spongzi.subject.infra.basic.service;

import com.spongzi.subject.infra.basic.entity.SubjectBrief;

/**
 * 简答题(SubjectBrief)表服务接口
 *
 * @author spongzi
 * @since 2023-10-08 13:30:23
 */
public interface SubjectBriefService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SubjectBrief queryById(Long id);

    /**
     * 新增数据
     *
     * @param subjectBrief 实例对象
     * @return 实例对象
     */
    SubjectBrief insert(SubjectBrief subjectBrief);

    /**
     * 修改数据
     *
     * @param subjectBrief 实例对象
     * @return 实例对象
     */
    SubjectBrief update(SubjectBrief subjectBrief);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

    /**
     * 按条件查询
     *
     * @param subjectBrief 主题简介
     * @return {@link SubjectBrief}
     */
    SubjectBrief queryByCondition(SubjectBrief subjectBrief);
}

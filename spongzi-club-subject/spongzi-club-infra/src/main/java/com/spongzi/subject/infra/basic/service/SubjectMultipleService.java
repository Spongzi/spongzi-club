package com.spongzi.subject.infra.basic.service;

import com.spongzi.subject.infra.basic.entity.SubjectMultiple;

import java.util.List;

/**
 * 多选题信息表(SubjectMultiple)表服务接口
 *
 * @author spongzi
 * @since 2023-10-08 13:31:14
 */
public interface SubjectMultipleService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SubjectMultiple queryById(Long id);

    /**
     * 新增数据
     *
     * @param subjectMultiple 实例对象
     * @return 实例对象
     */
    SubjectMultiple insert(SubjectMultiple subjectMultiple);

    /**
     * 修改数据
     *
     * @param subjectMultiple 实例对象
     * @return 实例对象
     */
    SubjectMultiple update(SubjectMultiple subjectMultiple);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

    /**
     * 插入批次
     *
     * @param subjectMultipleList 主题多列表
     */
    void insertBatch(List<SubjectMultiple> subjectMultipleList);

    /**
     * 按条件查询
     *
     * @param subjectMultiple 多个主题
     * @return {@link List}<{@link SubjectMultiple}>
     */
    List<SubjectMultiple> queryByCondition(SubjectMultiple subjectMultiple);
}

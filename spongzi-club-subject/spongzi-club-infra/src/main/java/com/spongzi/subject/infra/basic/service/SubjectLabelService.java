package com.spongzi.subject.infra.basic.service;

import com.spongzi.subject.infra.basic.entity.SubjectLabel;

import java.util.List;

/**
 * 题目标签表(SubjectLabel)表服务接口
 *
 * @author makejava
 * @since 2023-10-07 19:39:32
 */
public interface SubjectLabelService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SubjectLabel queryById(Long id);

    /**
     * 新增数据
     *
     * @param subjectLabel 实例对象
     * @return 实例对象
     */
    int insert(SubjectLabel subjectLabel);

    /**
     * 修改数据
     *
     * @param subjectLabel 实例对象
     * @return 实例对象
     */
    int update(SubjectLabel subjectLabel);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

    /**
     * 按ID查询批次
     *
     * @param labelIdList 标签ID列表
     * @return {@link List}<{@link SubjectLabel}>
     */
    List<SubjectLabel> batchQueryById(List<Long> labelIdList);

    /**
     * 按条件查询
     *
     * @param subjectLabel 主题标签
     * @return {@link List}<{@link SubjectLabel}>
     */
    List<SubjectLabel> queryByCondition(SubjectLabel subjectLabel);
}

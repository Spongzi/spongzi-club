package com.spongzi.subject.infra.basic.service;

import com.spongzi.subject.infra.basic.entity.SubjectMapping;

import java.util.List;

/**
 * 题目分类关系表(SubjectMapping)表服务接口
 *
 * @author spongzi
 * @since 2023-10-07 20:22:58
 */
public interface SubjectMappingService {

    /**
     * 通过ID查询单条数据
     *
     * @return 实例对象
     */
    SubjectMapping queryById(Long id);

    /**
     * 新增数据
     *
     * @param subjectMapping 实例对象
     * @return 实例对象
     */
    int insert(SubjectMapping subjectMapping);

    /**
     * 修改数据
     *
     * @param subjectMapping 实例对象
     * @return 实例对象
     */
    int update(SubjectMapping subjectMapping);

    /**
     * 通过主键删除数据
     *
     * @param id ID
     * @return 是否成功
     */
    boolean deleteById(Long id);

    /**
     * 查询标签ID
     *
     * @param subjectMapping 主题映射
     * @return {@link List}<{@link SubjectMapping}>
     */
    List<SubjectMapping> queryLabelId(SubjectMapping subjectMapping);

    /**
     * 插入批次
     *
     * @param mappingList 映射列表
     */
    void insertBatch(List<SubjectMapping> mappingList);
}

package com.spongzi.subject.infra.basic.service;

import com.spongzi.subject.infra.basic.entity.SubjectMapping;

/**
 * 题目分类关系表(SubjectMapping)表服务接口
 *
 * @author makejava
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

}

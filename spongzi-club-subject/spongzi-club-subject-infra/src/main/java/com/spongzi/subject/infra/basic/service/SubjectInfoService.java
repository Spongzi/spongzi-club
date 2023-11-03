package com.spongzi.subject.infra.basic.service;

import com.spongzi.subject.infra.basic.entity.SubjectInfo;

import java.util.List;

/**
 * 题目信息表(SubjectInfo)表服务接口
 *
 * @author spongzi
 * @since 2023-10-08 13:30:03
 */
public interface SubjectInfoService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SubjectInfo queryById(Long id);
    /**
     * 新增数据
     *
     * @param subjectInfo 实例对象
     * @return 实例对象
     */
    int insert(SubjectInfo subjectInfo);

    /**
     * 修改数据
     *
     * @param subjectInfo 实例对象
     * @return 实例对象
     */
    int update(SubjectInfo subjectInfo);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

    /**
     * 查询所有的数量
     *
     * @param subjectInfo 主题信息
     * @param categoryId  类别ID
     * @param labelId     标签ID
     * @return int
     */
    int countByCondition(SubjectInfo subjectInfo, Integer categoryId, Integer labelId);

    /**
     * 分页查询
     *
     * @param subjectInfo 主题信息
     * @param categoryId  类别ID
     * @param labelId     标签ID
     * @param start       开始
     * @param pageNo      页码
     * @param pageSize    页面大小
     * @return {@link List}<{@link SubjectInfo}>
     */
    List<SubjectInfo> queryPage(SubjectInfo subjectInfo, Integer categoryId, Integer labelId, int start, Integer pageNo, Integer pageSize);
}

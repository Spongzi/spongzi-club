package com.spongzi.subject.infra.basic.service;

import com.spongzi.club.common.entity.PageResult;
import com.spongzi.subject.infra.basic.entity.SubjectInfoEs;

/**
 * Subject Es 服务
 *
 * @author spong
 * @date 2023/12/23
 */
public interface SubjectEsService {

    /**
     * 插入
     *
     * @param subjectInfoEs 主题信息ES
     * @return boolean
     */
    boolean insert(SubjectInfoEs subjectInfoEs);

    /**
     * 查询主题列表
     *
     * @param subjectInfoEs 主题信息ES
     * @return {@link PageResult}<{@link SubjectInfoEs}>
     */
    PageResult<SubjectInfoEs> querySubjectList(SubjectInfoEs subjectInfoEs);
}

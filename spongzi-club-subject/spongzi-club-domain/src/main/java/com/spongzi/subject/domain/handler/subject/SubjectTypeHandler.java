package com.spongzi.subject.domain.handler.subject;

import com.spongzi.subject.common.enums.SubjectInfoTypeEnum;
import com.spongzi.subject.domain.entity.SubjectInfoBO;

/**
 * 主题类型处理程序
 *
 * @author spong
 * @date 2023/10/08
 */
public interface SubjectTypeHandler {

    /**
     * 获取处理程序类型
     *
     * @return {@link SubjectInfoTypeEnum}
     */
    SubjectInfoTypeEnum getHandlerType();

    /**
     * 实际的题目插入
     *
     * @param subjectInfoBO 主题信息BO
     */
    void add(SubjectInfoBO subjectInfoBO);
}

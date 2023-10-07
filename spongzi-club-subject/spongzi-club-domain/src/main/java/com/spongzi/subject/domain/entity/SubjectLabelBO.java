package com.spongzi.subject.domain.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 题目标签表(SubjectLabel)实体类
 *
 * @author spongzi
 * @since 2023-10-07 19:39:32
 */
@Data
public class SubjectLabelBO implements Serializable {
    private static final long serialVersionUID = -19420519166490061L;
    /**
     * 主键
     */
    private Long id;

    private Long categoryId;

    /**
     * 标签分类
     */
    private String labelName;

    /**
     * 排序
     */
    private Integer sortNum;

    /**
     * 逻辑删除
     */
    private Integer isDeleted;
}


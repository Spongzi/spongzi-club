package com.spongzi.subject.domain.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 题目分类(SubjectCategory)实体类
 *
 * @author spongzi
 * @since 2023-10-06 16:57:54
 */
@Data
public class SubjectCategoryBO implements Serializable {

    private static final long serialVersionUID = 412015292565140186L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 分类名称
     */
    private String categoryName;

    /**
     * 分类类型
     */
    private Integer categoryType;

    /**
     * 图标连接
     */
    private String imageUrl;

    /**
     * 父级id
     */
    private Long parentId;

    /**
     * 计数
     */
    private Integer count;
}


package com.spongzi.subject.infra.basic.entity;

import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * 题目分类关系表(SubjectMapping)实体类
 *
 * @author makejava
 * @since 2023-10-07 20:22:58
 */
@Data
public class SubjectMapping implements Serializable {
    private static final long serialVersionUID = 577341870090458638L;
    /**
     * 主键
     */
    private Long id;

    /**
     * 题目id
     */
    private Integer subjectId;

    /**
     * 分类id
     */
    private Integer categoryId;

    /**
     * 标签id
     */
    private Integer labelId;

    /**
     * 逻辑删除
     */
    private Integer isDeleted;

}


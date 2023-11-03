package com.spongzi.subject.infra.basic.entity;

import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * 简答题(SubjectBrief)实体类
 *
 * @author spongzi
 * @since 2023-10-08 13:30:23
 */
@Data
public class SubjectBrief implements Serializable {
    private static final long serialVersionUID = 293060243668805619L;
    /**
     * 主键
     */
    private Long id;

    /**
     * 题目答案
     */
    private String subjectAnswer;

    /**
     * 题目id
     */
    private Long subjectId;

    /**
     * 创建人
     */
    private String createdBy;

    /**
     * 创建时间
     */
    private Date createdTime;

    /**
     * 更新人
     */
    private String updateBy;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 逻辑删除
     */
    private Integer isDeleted;

}


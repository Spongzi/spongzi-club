package com.spongzi.subject.infra.basic.entity;

import com.spongzi.club.common.entity.PageInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * es主题
 *
 * @author spong
 * @date 2023/12/23
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SubjectInfoEs extends PageInfo implements Serializable {

    /**
     * 主题ID
     */
    private Long subjectId;

    /**
     * 文档ID
     */
    private Long docId;

    /**
     * 使用者名称
     */
    private String subjectName;

    /**
     * 主题式答案
     */
    private String subjectAnswer;

    /**
     * 创建用户
     */
    private String createUser;

    /**
     * 创建时间
     */
    private Long createTime;

    /**
     * 主题类型
     */
    private Integer subjectType;

    /**
     * 关键词
     */
    private String keywords;

    /**
     * 得分
     */
    private BigDecimal score;
}

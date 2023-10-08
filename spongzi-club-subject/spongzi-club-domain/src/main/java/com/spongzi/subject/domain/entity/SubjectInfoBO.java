package com.spongzi.subject.domain.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 题目信息表(SubjectInfo)BO
 *
 * @author spongzi
 * @since 2023-10-08 13:30:03
 */
@Data
public class SubjectInfoBO implements Serializable {
    private static final long serialVersionUID = -96353715297443704L;
    /**
     * 主键
     */
    private Long id;

    /**
     * 题目名称
     */
    private String subjectName;

    /**
     * 题目难度
     */
    private Integer subjectDifficult;

    /**
     * 出题人名
     */
    private String settleName;

    /**
     * 题目类型 1单选 2多选 3判断 4简答
     */
    private Integer subjectType;

    /**
     * 题目分数
     */
    private Integer subjectScore;

    /**
     * 题目解析
     */
    private String subjectParse;

    /**
     * 逻辑删除
     */
    private Integer isDeleted;

    /**
     * 题目答案
     */
    private String subjectAnswer;

    /**
     * 类别ID列表
     */
    private List<Long> categoryIds;

    /**
     * 标签id列表
     */
    private List<Long> labelIds;

    /**
     * 题目答案列表
     */
    private List<SubjectAnswerBO> optionList;
}


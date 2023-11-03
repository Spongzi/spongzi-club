package com.spongzi.subject.domain.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 题目答案DTO
 *
 * @author spongzi
 * @since 2023-10-08 13:30:03
 */
@Data
public class SubjectAnswerBO implements Serializable {
    private static final long serialVersionUID = -96353715297443704L;

    /**
     * 选项类型
     */
    private Integer optionType;

    /**
     * 选项内容
     */
    private String optionContent;

    /**
     * 是正确吗
     */
    private Integer isCorrect;
}


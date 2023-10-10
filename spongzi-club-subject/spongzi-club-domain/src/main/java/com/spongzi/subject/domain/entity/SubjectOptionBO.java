package com.spongzi.subject.domain.entity;

import com.spongzi.subject.common.entity.PageInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * 题目操作(SubjectInfo)BO
 *
 * @author spongzi
 * @date 2023/10/10
 * @since 2023-10-08 13:30:03
 */
@Data
public class SubjectOptionBO implements Serializable {
    private static final long serialVersionUID = -96353715297443704L;

    /**
     * 题目答案
     */
    private String subjectAnswer;

    /**
     * 题目答案列表
     */
    private List<SubjectAnswerBO> optionList;
}


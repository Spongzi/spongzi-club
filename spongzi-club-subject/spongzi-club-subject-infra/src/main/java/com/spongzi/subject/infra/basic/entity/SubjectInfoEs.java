package com.spongzi.subject.infra.basic.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.Date;

/**
 * es主题
 *
 * @author spong
 * @date 2023/12/23
 */
@Data
public class SubjectInfoEs {

    @Id
    private Long id;

    private String subjectName;

    private String subjectAnswer;

    private String createUser;

    private Date createTime;
}

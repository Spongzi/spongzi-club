package com.spongzi.subject.infra.basic.entity;

/**
 * ES主题字段
 *
 * @author spong
 * @date 2023/12/24
 */
public class EsSubjectFields {

    /**
     * 文档ID
     */
    public static final String DOC_ID = "doc_id";

    /**
     * ID
     */
    public static final String SUBJECT_ID = "subject_id";

    /**
     * 主题名称
     */
    public static final String SUBJECT_NAME = "subject_name";

    /**
     * 主题答案
     */
    public static final String SUBJECT_ANSWER = "subject_answer";

    /**
     * 主题类型
     */
    public static final String SUBJECT_TYPE = "subject_type";

    /**
     * 创建用户
     */
    public static final String CREATE_USER = "create_user";

    /**
     * 创建时间
     */
    public static final String CREATE_TIME = "create_time";

    public static final String[] FIELD_QUERY = {
            SUBJECT_ID,
            SUBJECT_NAME,
            SUBJECT_ANSWER,
            SUBJECT_TYPE,
            CREATE_USER,
            CREATE_TIME
    };
}

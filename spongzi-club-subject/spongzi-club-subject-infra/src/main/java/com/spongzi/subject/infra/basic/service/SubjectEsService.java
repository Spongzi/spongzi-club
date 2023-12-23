package com.spongzi.subject.infra.basic.service;

/**
 * Subject Es 服务
 *
 * @author spong
 * @date 2023/12/23
 */
public interface SubjectEsService {

    /**
     * 创建索引
     */
    void createIndex();

    /**
     * 添加文档
     */
    void addDoc();

    /**
     * 发现
     */
    void find();

    /**
     * 搜索
     */
    void search();
}

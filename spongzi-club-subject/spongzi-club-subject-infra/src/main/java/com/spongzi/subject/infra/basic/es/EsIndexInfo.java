package com.spongzi.subject.infra.basic.es;

import lombok.Data;

import java.io.Serializable;

/**
 * ES索引信息
 *
 * @author spong
 * @date 2023/12/23
 */
@Data
public class EsIndexInfo implements Serializable {

    /**
     * 群集名称
     */
    private String clusterName;

    /**
     * 索引名称
     */
    private String indexName;

}

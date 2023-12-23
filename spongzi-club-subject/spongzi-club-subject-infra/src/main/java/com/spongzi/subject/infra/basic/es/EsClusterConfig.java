package com.spongzi.subject.infra.basic.es;

import lombok.Data;

import java.io.Serializable;

/**
 * 是否为群集配置
 *
 * @author spong
 * @date 2023/12/23
 */
@Data
public class EsClusterConfig implements Serializable {

    /**
     * 集群的名称
     */
    private String name;

    /**
     * 集群的节点
     */
    private String nodes;
}

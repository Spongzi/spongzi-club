package com.spongzi.subject.infra.basic.es;

import lombok.Data;

import java.io.Serializable;
import java.util.Map;

/**
 * ES源数据
 *
 * @author spong
 * @date 2023/12/23
 */
@Data
public class EsSourceData implements Serializable {

    private String docId;

    private Map<String, Object> data;

}

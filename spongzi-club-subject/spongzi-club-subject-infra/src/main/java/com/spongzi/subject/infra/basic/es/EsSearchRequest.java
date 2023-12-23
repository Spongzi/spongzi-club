package com.spongzi.subject.infra.basic.es;

import lombok.Data;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.SortOrder;

/**
 * ES搜索请求
 *
 * @author spong
 * @date 2023/12/23
 */
@Data
public class EsSearchRequest {

    /**
     * Bq 查询条件
     */
    private BoolQueryBuilder bq;

    /**
     * 字段
     */
    private String[] fields;

    /**
     * 从…
     */
    private int from;

    /**
     * 大小
     */
    private int size;

    /**
     * 需要快照
     */
    private Boolean needScroll;

    /**
     * 缓存时间
     */
    private Long minutes;

    /**
     * 排序字段
     */
    private String sortName;

    /**
     * 排序方式
     */
    private SortOrder sortOrder;

    /**
     * 高亮显示构建器
     */
    private HighlightBuilder highlightBuilder;
}

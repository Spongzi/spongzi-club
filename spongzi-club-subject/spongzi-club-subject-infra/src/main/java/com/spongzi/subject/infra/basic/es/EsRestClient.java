package com.spongzi.subject.infra.basic.es;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.http.HttpHost;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.elasticsearch.index.reindex.DeleteByQueryRequest;
import org.elasticsearch.search.Scroll;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.elasticsearch.search.sort.ScoreSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.IOException;
import java.util.*;

/**
 * es rest客户端
 *
 * @author spong
 * @date 2023/12/23
 */
@Component
@Slf4j
public class EsRestClient {

    @Resource
    private EsConfigProperties esConfigProperties;

    public static Map<String, RestHighLevelClient> clientMap = new HashMap<>();

    private static final RequestOptions COMMON_OPTIONS;

    static {
        RequestOptions.Builder builder = RequestOptions.DEFAULT.toBuilder();
        COMMON_OPTIONS = builder.build();
    }

    /**
     * 初始化
     */
    @PostConstruct
    public void initialize() {
        List<EsClusterConfig> esConfigs = esConfigProperties.getEsConfigs();
        esConfigs.forEach(esConfig -> {
            log.info("initialize.config.name: {}", esConfig.getName());
            log.info("initialize.config.node: {}", esConfig.getNodes());
            RestHighLevelClient restHighLevelClient = initRestClient(esConfig);
            clientMap.put(esConfig.getName(), restHighLevelClient);
        });
    }

    // 初始化REST客户端
    private RestHighLevelClient initRestClient(EsClusterConfig esClusterConfig) {
        // 将ES集群节点IP和端口以逗号分隔的字符串分割成数组
        String[] ipPortArr = esClusterConfig.getNodes().split(",");

        // 创建一个ArrayList用于存储HttpHost对象，长度为IP和端口数组的长度
        List<HttpHost> httpHostList = new ArrayList<>(ipPortArr.length);

        // 遍历IP和端口数组
        for (String ipPort : ipPortArr) {
            // 将每个IP和端口以冒号分隔的字符串分割成数组
            String[] ipPortInfo = ipPort.split(":");

            // 如果分割结果数组的长度为2，说明包含IP和端口
            if (ipPortInfo.length == 2) {
                // 创建一个HttpHost对象，使用分割得到的IP和端口
                HttpHost httpHost = new HttpHost(ipPortInfo[0], NumberUtils.toInt(ipPortInfo[1]));

                // 将HttpHost对象添加到httpHostList中
                httpHostList.add(httpHost);
            }
        }

        // 将httpHostList转换为HttpHost数组
        HttpHost[] httpHosts = new HttpHost[httpHostList.size()];
        httpHostList.toArray(httpHosts);

        // 使用builder模式构建RestClient对象
        RestClientBuilder builder = RestClient.builder(httpHosts);

        // 创建并返回一个RestHighLevelClient对象，该对象是RestClient的高级封装
        return new RestHighLevelClient(builder);
    }


    private static RestHighLevelClient getClient(String clusterName) {
        return clientMap.get(clusterName);
    }

    /**
     * 插入文档
     *
     * @param esIndexInfo  ES索引信息
     * @param esSourceData ES源数据
     * @return boolean
     */
    public static boolean insertDoc(EsIndexInfo esIndexInfo, EsSourceData esSourceData) {
        IndexRequest indexRequest = new IndexRequest(esIndexInfo.getIndexName());
        indexRequest.source(esSourceData.getData());
        indexRequest.id(esSourceData.getDocId());
        try {
            getClient(esIndexInfo.getClusterName()).index(indexRequest, COMMON_OPTIONS);
            return true;
        } catch (IOException e) {
            log.error("insertDoc.exception: {}", e.getMessage(), e);
        }
        return false;
    }

    /**
     * 删除
     *
     * @param esIndexInfo ES索引信息
     * @return boolean
     */
    public static boolean delete(EsIndexInfo esIndexInfo) {
        try {
            DeleteByQueryRequest deleteByQueryRequest = new DeleteByQueryRequest(esIndexInfo.getIndexName());
            deleteByQueryRequest.setQuery(QueryBuilders.matchAllQuery());
            BulkByScrollResponse response = getClient(esIndexInfo.getClusterName())
                    .deleteByQuery(deleteByQueryRequest, COMMON_OPTIONS);
            long deleted = response.getDeleted();
            log.info("deleted.size: {}", deleted);
            return true;
        } catch (Exception e) {
            log.error("delete.exception: {}", e.getMessage(), e);
        }
        return false;
    }

    /**
     * 按单据删除
     *
     * @param esIndexInfo ES索引信息
     * @param docId       文档ID
     * @return boolean
     */
    public static boolean deleteByDoc(EsIndexInfo esIndexInfo, String docId) {
        try {
            DeleteRequest deleteRequest = new DeleteRequest(esIndexInfo.getIndexName());
            deleteRequest.id(docId);
            DeleteResponse response = getClient(esIndexInfo.getClusterName()).delete(deleteRequest, COMMON_OPTIONS);
            log.info("deleteByDoc.response: {}", JSON.toJSONString(response));
            return true;
        } catch (Exception e) {
            log.error("deleteByDoc.exception: {}", e.getMessage(), e);
        }
        return false;
    }

    /**
     * 是否按ID对现有文档进行记录
     *
     * @param esIndexInfo ES索引信息
     * @param docId       文档ID
     * @return boolean
     */
    public static boolean isExistDocById(EsIndexInfo esIndexInfo, String docId) {
        try {
            GetRequest getRequest = new GetRequest(esIndexInfo.getIndexName());
            getRequest.id(docId);
            return getClient(esIndexInfo.getClusterName()).exists(getRequest, COMMON_OPTIONS);
        } catch (Exception e) {
            log.error("isExistDocById.exception: {}", e.getMessage(), e);
        }
        return false;
    }

    /**
     * 按ID获取文档
     *
     * @param esIndexInfo ES索引信息
     * @param docId       文档ID
     * @return {@link Map}<{@link String}, {@link Object}>
     */
    public static Map<String, Object> getDocById(EsIndexInfo esIndexInfo, String docId) {
        try {
            GetRequest getRequest = new GetRequest(esIndexInfo.getIndexName());
            getRequest.id(docId);
            GetResponse response = getClient(esIndexInfo.getClusterName()).get(getRequest, COMMON_OPTIONS);
            log.info("getDocById.source: {}", response.getSource());
            return response.getSource();
        } catch (Exception e) {
            log.error("getDocById.exception: {}", e.getMessage(), e);
        }
        return null;
    }

    /**
     * 按ID获取文档, 有一个数据的过滤
     *
     * @param esIndexInfo ES索引信息
     * @param docId       文档ID
     * @param fields      字段
     * @return {@link Map}<{@link String}, {@link Object}>
     */
    public static Map<String, Object> getDocById(EsIndexInfo esIndexInfo, String docId, String[] fields) {
        try {
            GetRequest getRequest = new GetRequest(esIndexInfo.getIndexName());
            getRequest.id(docId);
            FetchSourceContext fetchSourceContext = new FetchSourceContext(true, fields, null);
            getRequest.fetchSourceContext(fetchSourceContext);
            GetResponse response = getClient(esIndexInfo.getClusterName()).get(getRequest, COMMON_OPTIONS);
            log.info("getDocById.source: {}", response.getSource());
            return response.getSource();
        } catch (Exception e) {
            log.error("getDocById.exception: {}", e.getMessage(), e);
        }
        return null;
    }

    /**
     * 带术语查询搜索
     *
     * @param esIndexInfo     ES索引信息
     * @param esSearchRequest es搜索请求
     * @return {@link SearchResponse}
     */
    public static SearchResponse searchWithTermQuery(EsIndexInfo esIndexInfo, EsSearchRequest esSearchRequest) {
        try {
            BoolQueryBuilder bq = esSearchRequest.getBq();
            String[] fields = esSearchRequest.getFields();
            int from = esSearchRequest.getFrom();
            int size = esSearchRequest.getSize();
            Long minutes = esSearchRequest.getMinutes();
            Boolean needScroll = esSearchRequest.getNeedScroll();
            String sortName = esSearchRequest.getSortName();
            SortOrder sortOrder = esSearchRequest.getSortOrder();

            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            searchSourceBuilder.query(bq);
            searchSourceBuilder.fetchSource(fields, null).from(from).size(size);

            if (Objects.nonNull(esSearchRequest.getHighlightBuilder())) {
                searchSourceBuilder.highlighter(esSearchRequest.getHighlightBuilder());
            }

            if (StringUtils.isBlank(sortName)) {
                searchSourceBuilder.sort(sortName);
            }

            searchSourceBuilder.sort(new ScoreSortBuilder().order(SortOrder.DESC));

            SearchRequest searchRequest = new SearchRequest();
            searchRequest.searchType(SearchType.DEFAULT);
            searchRequest.indices(esIndexInfo.getIndexName());
            searchRequest.source(searchSourceBuilder);
            if (needScroll) {
                Scroll scroll = new Scroll(TimeValue.timeValueMinutes(minutes));
                searchRequest.scroll(scroll);
            }

            return getClient(esIndexInfo.getClusterName()).search(searchRequest, COMMON_OPTIONS);
        } catch (Exception e) {
            log.error("searchWithTermQuery.exception: {}", e.getMessage(), e);
        }
        return null;
    }

    /**
     * 更新文档
     *
     * @param esIndexInfo  ES索引信息
     * @param esSourceData ES源数据
     * @return boolean
     */
    public static boolean updateDoc(EsIndexInfo esIndexInfo, EsSourceData esSourceData) {
        try {
            UpdateRequest updateRequest = new UpdateRequest();
            updateRequest.index(esIndexInfo.getIndexName());
            updateRequest.id(esSourceData.getDocId());
            updateRequest.doc(esSourceData.getData());
            getClient(esIndexInfo.getClusterName()).update(updateRequest, COMMON_OPTIONS);
            return true;
        } catch (IOException e) {
            log.error("updateDoc.exception: {}", e.getMessage(), e);
        }
        return false;
    }

    /**
     * 批量回写单据
     *
     * @param esIndexInfo      ES索引信息
     * @param esSourceDataList ES源数据列表
     * @return boolean
     */
    public static boolean batchUpdateDoc(EsIndexInfo esIndexInfo,
                                         List<EsSourceData> esSourceDataList) {
        try {
            boolean flag = false;
            BulkRequest bulkRequest = new BulkRequest();
            for (EsSourceData esSourceData : esSourceDataList) {
                if (StringUtils.isBlank(esSourceData.getDocId())) {
                    UpdateRequest updateRequest = new UpdateRequest();
                    updateRequest.index(esIndexInfo.getIndexName());
                    updateRequest.id(esSourceData.getDocId());
                    updateRequest.doc(esSourceData.getData());
                    bulkRequest.add(updateRequest);
                    flag = true;
                }
            }
            if (flag) {
                BulkResponse bulkResponse = getClient(esIndexInfo.getClusterName()).bulk(bulkRequest, COMMON_OPTIONS);
                if (bulkResponse.hasFailures()) {
                    log.error("batchUpdateDoc.exception: {}", bulkResponse.buildFailureMessage());
                    return false;
                }
            }
            return true;
        } catch (Exception e) {
            log.error("batchUpdateDoc.exception: {}", e.getMessage(), e);
        }
        return false;
    }
}

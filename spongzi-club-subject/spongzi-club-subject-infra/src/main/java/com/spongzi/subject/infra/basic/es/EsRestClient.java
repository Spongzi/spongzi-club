package com.spongzi.subject.infra.basic.es;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    private RestHighLevelClient initRestClient(EsClusterConfig esClusterConfig) {
        String[] ipPortArr = esClusterConfig.getNodes().split(",");
        List<HttpHost> httpHostList = new ArrayList<>(ipPortArr.length);
        for (String ipPort : ipPortArr) {
            String[] ipPortInfo = ipPort.split(":");
            if (ipPortInfo.length == 2) {
                HttpHost httpHost = new HttpHost(ipPortInfo[0], NumberUtils.toInt(ipPortInfo[1]));
                httpHostList.add(httpHost);
            }
        }
        HttpHost[] httpHosts = new HttpHost[httpHostList.size()];
        httpHostList.toArray(httpHosts);

        RestClientBuilder builder = RestClient.builder(httpHosts);
        return new RestHighLevelClient(builder);
    }
}

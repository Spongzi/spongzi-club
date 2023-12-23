package com.spongzi.subject.infra.basic.es;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * ES配置属性
 *
 * @author spong
 * @date 2023/12/23
 */
@Getter
@Component
@ConfigurationProperties(prefix = "es.cluster")
public class EsConfigProperties {
    private List<EsClusterConfig> esConfigs = new ArrayList<>();

    public void setEsClusterConfigs(List<EsClusterConfig> esConfigs) {
        this.esConfigs = esConfigs;
    }


}

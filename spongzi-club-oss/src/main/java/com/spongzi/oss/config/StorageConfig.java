package com.spongzi.oss.config;

import com.spongzi.oss.adapter.StorageAdapter;
import com.spongzi.oss.adapter.AliStorageAdapter;
import com.spongzi.oss.adapter.MinioStorageAdapter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 存储配置
 *
 * @author spong
 * @date 2023/10/16
 */
@Configuration
@RefreshScope
public class StorageConfig {

    @Value("${storage.service.type}")
    private String storageType;

    @Bean
    @RefreshScope
    public StorageAdapter storageAdapter() {
        if ("minio".equals(storageType)) {
            return new MinioStorageAdapter();
        } else if ("aliyun".equals(storageType)) {
            return new AliStorageAdapter();
        }
        else {
            throw new IllegalArgumentException("未找到对应的文件处理");
        }
    }
}

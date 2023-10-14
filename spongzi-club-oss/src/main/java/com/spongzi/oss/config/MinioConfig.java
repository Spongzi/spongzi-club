package com.spongzi.oss.config;

import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * minIo配置管理
 *
 * @author spong
 * @date 2023/10/14
 */
@Configuration
public class MinioConfig {

    /**
     * MinIoUrl
     */
    @Value("${minio.url}")
    private String url;

    /**
     * 访问密钥
     */
    @Value("${minio.accessKey}")
    private String accessKey;

    /**
     * 密钥
     */
    @Value("${minio.secretKey}")
    private String secretKey;

    /**
     * 获取minio客户端
     *
     * @return {@link MinioClient}
     */
    @Bean
    public MinioClient getMinioClient() {
        return MinioClient.builder()
                .endpoint(url)
                .credentials(accessKey, secretKey)
                .build();
    }
}

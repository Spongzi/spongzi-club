package com.spongzi.oss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * OSS服务启动器
 *
 * @author spong
 * @date 2023/10/14
 */
@SpringBootApplication
@ComponentScan("com.spongzi")
public class OssApplication {
    public static void main(String[] args) {
        SpringApplication.run(OssApplication.class, args);
    }
}

package com.spongzi.subject;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * 刷题微服务启动类
 *
 * @author spong
 * @date 2023/10/06
 */
@SpringBootApplication
@ComponentScan("com.spongzi")
@MapperScan("com.spongzi.**.mapper")
@EnableFeignClients(basePackages = "com.spongzi")
public class SubjectApplication {
    public static void main(String[] args) {
        SpringApplication.run(SubjectApplication.class, args);
    }
}

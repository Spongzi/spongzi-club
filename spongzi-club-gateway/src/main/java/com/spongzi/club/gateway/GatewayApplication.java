package com.spongzi.club.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 网关应用
 *
 * @author spong
 * @date 2023/10/31
 */
@SpringBootApplication
@ComponentScan("com.spongzi")
public class GatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }
}

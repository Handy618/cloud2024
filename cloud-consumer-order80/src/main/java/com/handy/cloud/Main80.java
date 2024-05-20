package com.handy.cloud;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @auther handy
 * @create 2024-05-04 15:19
 */
@SpringBootApplication
@EnableDiscoveryClient //该注解用于向使用consul为注册中心时注册服务
public class Main80 {
    public static void main(String[] args) {
        SpringApplication.run(Main80.class,args);
    }
}
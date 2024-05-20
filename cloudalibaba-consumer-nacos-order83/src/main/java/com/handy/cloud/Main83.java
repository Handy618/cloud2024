package com.handy.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @auther handy
 * @create 2024-05-17 17:15
 */
@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients
public class Main83 {
    public static void main(String[] args) {
         SpringApplication.run(Main83.class,args);
    }

}
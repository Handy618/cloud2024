package com.handy.cloud;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication

/**
* @Description:    将被哨兵纳入管控的8401微服务提供者
* @Author: handy
* @Date:  2024-05-16
*/
public class Main8401 {
     public static void main(String[] args) {
         SpringApplication.run(Main8401.class,args);
      }
}
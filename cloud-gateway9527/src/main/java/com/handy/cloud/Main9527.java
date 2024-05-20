package com.handy.cloud;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


/**
* @Description:   Gateway网关主启动类
* @Param:
* @return:
*/
@SpringBootApplication
@EnableDiscoveryClient //服务注册和发现
public class Main9527 {

    public static void main(String[] args) {
        SpringApplication.run(Main9527.class,args);
    }

}
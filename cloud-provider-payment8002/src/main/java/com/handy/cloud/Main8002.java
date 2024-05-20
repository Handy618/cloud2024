package com.handy.cloud;




import org.apache.ibatis.annotations.Mapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.handy.cloud.mapper") //import tk.mybatis.spring.annotation.MapperScan;
@EnableWebMvc
@EnableDiscoveryClient  //服务注册和发现
@RefreshScope // consul动态刷新
public class Main8002 {
    public static void main(String[] args) {
        SpringApplication.run(Main8002.class,args);
    }
}
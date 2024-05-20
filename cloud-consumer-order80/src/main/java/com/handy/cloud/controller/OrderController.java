package com.handy.cloud.controller;


import com.handy.cloud.entities.PayDTO;
import com.handy.cloud.resp.ResultData;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * 类描述：
 *
 * @author Handy
 * @date 2024-05-08 14:16
 **/
@RestController
public class OrderController{
    //public static final String PaymentSrv_URL = "http://localhost:8001";//先写死，硬编码

    //服务注册
    public static final String PaymentSrv_URL = "http://cloud-payment-service";//服务注册中心上的微服务名称


    @Autowired
    private RestTemplate restTemplate;

    /**
     * 一般情况下，通过浏览器的地址栏输入url，发送的只能是get请求
     * 我们底层调用的是post方法，模拟消费者发送get请求，客户端消费者
     * 参数可以不添加@RequestBody
     * @param payDTO
     * @return
     */
    @PostMapping("/consumer/pay/add")
    public ResultData addOrder(@RequestBody  PayDTO payDTO){
        return restTemplate.postForObject(PaymentSrv_URL + "/pay/add",payDTO,ResultData.class);
    }


    // 删除+修改操作作为家庭作业，O(∩_∩)O。。。。。。。
    @GetMapping("/consumer/pay/get/{id}")
    public ResultData getPayInfo(@PathVariable("id") Integer id){
        return restTemplate.getForObject(PaymentSrv_URL + "/pay/get/"+id, ResultData.class, id);
    }


    //消费者查看consul服务
    @GetMapping(value = "/consumer/pay/get/info")
    private String getInfoByConsul()
    {
        return restTemplate.getForObject(PaymentSrv_URL + "/pay/get/info", String.class);
    }


    @Resource
    private DiscoveryClient discoveryClient;


    @GetMapping("/consumer/discovery")
    public String discovery()
    {

        //查询可用的服务列表
        List<String> services = discoveryClient.getServices();
        for (String element : services) {
            System.out.println(element);
        }

        System.out.println("===================================");

        //查询指定的服务列表
        List<ServiceInstance> instances = discoveryClient.getInstances("cloud-payment-service");
        for (ServiceInstance element : instances) {
            System.out.println(element.getServiceId()+"\t"+element.getHost()+"\t"+element.getPort()+"\t"+element.getUri());
        }

        return instances.get(0).getServiceId()+":"+instances.get(0).getPort();
    }





}

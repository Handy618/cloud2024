package com.handy.cloud.controller;

import com.handy.cloud.apis.PayFeignApi;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: cloud2024
 * @author: Handy
 * @create: 2024-05-13 23:01
 */
@RestController
@Slf4j
public class OrderMicrometerController
{
    @Resource
    private PayFeignApi payFeignApi;

    //服务调用监控Micometer + zinkip测试用例
    @GetMapping(value = "/feign/micrometer/{id}")
    public String myMicrometer(@PathVariable("id") Integer id)
    {
        return payFeignApi.myMicrometer(id);
    }
}
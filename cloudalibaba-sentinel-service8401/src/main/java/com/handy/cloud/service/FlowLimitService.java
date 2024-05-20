package com.handy.cloud.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.stereotype.Service;

/**
 * @program: cloud2024
 * @author: Handy
 * @create: 2024-05-18 17:40
 */
@Service
public class FlowLimitService
{
    @SentinelResource(value = "common")        //指定防护资源
    public void common()
    {
        System.out.println("------FlowLimitService come in");
    }
}
package com.handy.cloud.controller;

import cn.hutool.core.util.IdUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @program: cloud2024
 * @author: Handy
 * @create 2023-11-13 14:55
 */
@RestController
public class PayCircuitController
{
    //=========Resilience4j CircuitBreaker 服务熔断&服务降级 的例子
    //@GetMapping(value = "/pay/circuit/{id}")
    //public String myCircuit(@PathVariable("id") Integer id)
    //{
    //    //直接报错的异常
    //    if(id == -4) throw new RuntimeException("----circuit id 不能负数");
    //
    //    //五秒的请求，超时请求异常，当前服务提供者规定是4秒。
    //    if(id == 9999){
    //        try { TimeUnit.SECONDS.sleep(5); } catch (InterruptedException e) { e.printStackTrace(); }
    //    }
    //
    //    //直接通过。
    //    return "Hello, circuit! inputId:  "+id+" \t " + IdUtil.simpleUUID();
    //}

    //=========Resilience4j bulkhead 服务隔离 的例子
    @GetMapping(value = "/pay/bulkhead/{id}")
    public String myBulkhead(@PathVariable("id") Integer id)
    {
        if(id == -4) throw new RuntimeException("----bulkhead id 不能-4");

        if(id == 9999)
        {
            try { TimeUnit.SECONDS.sleep(5); } catch (InterruptedException e) { e.printStackTrace(); }
        }

        return "Hello, bulkhead! inputId:  "+id+" \t " + IdUtil.simpleUUID();
    }


    //限流测试用例
    //=========Resilience4j ratelimit 的例子
    @GetMapping(value = "/pay/ratelimit/{id}")
    public String myRatelimit(@PathVariable("id") Integer id)
    {
        return "Hello, myRatelimit欢迎到来 inputId:  "+id+" \t " + IdUtil.simpleUUID();
    }
}
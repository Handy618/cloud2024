package com.handy.cloud.controller;


import com.handy.cloud.apis.PayFeignApi;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @program: cloud2024
 * @author: Handy
 * @create: 2024-05-11 20:45
 */
@RestController
public class OrderCircuitController
{
    @Resource
    private PayFeignApi payFeignApi;


    /**
    * @Description:   服务熔断&降级的例子
    * @Param:
    * @return:
    */
    @GetMapping(value = "/feign/pay/circuit/{id}")
    @CircuitBreaker(name = "cloud-payment-service", fallbackMethod = "myCircuitFallback") //name:服务熔断,fallbackMethod: 服务降级(兜底)
    public String myCircuitBreaker(@PathVariable("id") Integer id)
    {
        return payFeignApi.myCircuit(id);
    }
    //myCircuitFallback就是服务降级后的兜底处理方法
    public String myCircuitFallback(Integer id,Throwable t) {
        // 这里是容错处理逻辑，返回备用结果
        return "myCircuitFallback，系统繁忙，请稍后再试-----/(ㄒoㄒ)/~~";
    }


    /**
     *(船的)舱壁,隔离  例子
     * @param id
     * @return
     */
    //@GetMapping(value = "/feign/pay/bulkhead/{id}")
    //@Bulkhead(name = "cloud-payment-service",fallbackMethod = "myBulkheadFallback",type = Bulkhead.Type.SEMAPHORE)
    //public String myBulkhead(@PathVariable("id") Integer id)
    //{
    //    return payFeignApi.myBulkhead(id);
    //}
    //public String myBulkheadFallback(Throwable t)
    //{
    //    return "myBulkheadFallback，隔板超出最大数量限制，系统繁忙，请稍后再试-----/(ㄒoㄒ)/~~";
    //}


    /**
     * (船的)舱壁,隔离,THREADPOOL
     * @param id
     * @return
     *  !!! 注意测试时采用不同的id,即生成不同的并发线程来进行测试
     */
    //@GetMapping(value = "/feign/pay/bulkhead/{id}")
    //@Bulkhead(name = "cloud-payment-service",fallbackMethod = "myBulkheadPoolFallback",type = Bulkhead.Type.THREADPOOL)
    //public CompletableFuture<String> myBulkheadTHREADPOOL(@PathVariable("id") Integer id)
    //{
    //    //正常通过
    //    System.out.println(Thread.currentThread().getName()+"\t"+"enter the method!!!");
    //    //每个线程休眠3秒，形成并发线程，用于测试
    //    try { TimeUnit.SECONDS.sleep(3); } catch (InterruptedException e) { e.printStackTrace(); }
    //    System.out.println(Thread.currentThread().getName()+"\t"+"exist the method!!!");
    //
    //
    //    /**
    //    * CompletableFuture.supplyAsync()：
    //     * 这是CompletableFuture类的静态方法，用于创建一个新的CompletableFuture对象，并启动一个异步任务以执行指定的操作。
    //     * 该方法接受一个Supplier作为参数，该Supplier会产生异步计算的结果。
    //     *
    //     * () -> payFeignApi.myBulkhead(id)：
    //     * 这是一个lambda表达式，它是一个Supplier，它提供了一个异步计算的实现。在这个lambda表达式中，payFeignApi.myBulkhead(id)
    //     * 是一个调用Feign客户端的方法，并传递了一个id作为参数。这个方法可能会发起一个远程调用或者执行一些耗时的操作。
    //    */
    //    return CompletableFuture.supplyAsync(() -> payFeignApi.myBulkhead(id) + "\t" + " Bulkhead.Type.THREADPOOL");
    //}
    //public CompletableFuture<String> myBulkheadPoolFallback(Integer id,Throwable t)
    //{
    //    //服务隔离，服务降级
    //    return CompletableFuture.supplyAsync(() -> "Bulkhead.Type.THREADPOOL，系统繁忙，请稍后再试-----/(ㄒoㄒ)/~~");
    //}



    /**
    * @Description:  服务限流
    * @Param:
    * @return:
    */
    @GetMapping(value = "/feign/pay/ratelimit/{id}")
    @RateLimiter(name = "cloud-payment-service",fallbackMethod = "myRatelimitFallback")
    public String myBulkhead(@PathVariable("id") Integer id)
    {
        return payFeignApi.myRatelimit(id);
    }
    public String myRatelimitFallback(Integer id,Throwable t)
    {
        return "你被限流了，禁止访问/(ㄒoㄒ)/~~";
    }
}
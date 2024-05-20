package com.handy.cloud.config;

import feign.Logger;
import feign.Retryer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.util.retry.Retry;

/**
 * @program: cloud2024
 * @author: Handy
 * @create: 2024-05-11 16:07
 */
@Configuration
public class FeignConfig {

    @Bean
    public Retryer myRetryer(){


        return Retryer.NEVER_RETRY; //Feign默认配置是不走重试策略的



        /**
        * period: 如果设置为1000毫秒，则表示每次失败后等待1秒再进行下一次重试。默认值是 100 milliseconds。
        * maxPeriod: 表示重试的最大时间间隔。在进行指数退避时，间隔会逐渐增大，但不会超过这个最大时间间隔。默认值是 1000 milliseconds。
        * maxAttempts: 表示最大重试次数。如果请求在达到最大重试次数后仍然失败，则不再重试，直接返回失败结果。默认值是 5 次重试。
        */
        //最大请求次数为3(1+2)，初始间隔时间为100ms，重试间最大间隔时间为1s
        //return new Retryer.Default(100,1,3);
    }


    @Bean
    Logger.Level feignLoggerLevel(){
        return Logger.Level.FULL;
    }
}
package com.handy.cloud.controller;


import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import com.handy.cloud.apis.PayFeignApi;
import com.handy.cloud.entities.PayDTO;
import com.handy.cloud.resp.ResultData;
import com.handy.cloud.resp.ReturnCodeEnum;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class OrderController {

    @Resource
    private PayFeignApi payFeignApi;
   
    
    /** 
    * @Description:   新增一条订单
    * @Param:
    * @return: 
    */ 
    @PostMapping("/feign/pay/add")
    public ResultData addPay(@RequestBody  PayDTO payDTO){

        System.out.println("第一步：模拟本地addOrder新增订单成功(省略sql操作)，第二步：再开启addPay支付微服务远程调用");
        ResultData<String> resultData = payFeignApi.addPay(payDTO);

        return resultData;
    }
    
    /** 
    * @Description:   根据主键查询一条订单
    * @Param:  
    * @return: 
    */ 
    //@GetMapping("/feign/pay/get/{id}")
    //public ResultData getPayInfo(@PathVariable("id") Integer id){
    //
    //    System.out.println("-------支付微服务远程调用，按照id查询订单支付流水信息");
    //
    //
    //    ResultData resultData = payFeignApi.getPayById(id);
    //
    //    return resultData;
    //
    //}

    //测试捕捉超时异常
    @GetMapping("/feign/pay/get/{id}")
    public ResultData getPayInfo(@PathVariable("id") Integer id)
    {
        System.out.println("-------支付微服务远程调用，按照id查询订单支付流水信息");
        ResultData resultData = null;

        try{
            System.out.println("调用开始 ---------"+ DateUtil.now());
            resultData = payFeignApi.getPayById(id);
        }catch(Exception e){
            e.printStackTrace();

            System.out.println("调用结束 ---------"+ DateUtil.now());
            ResultData.fail(ReturnCodeEnum.RC500.getCode(), e.getMessage());
        }
        return resultData;
    }



    /**
    * @Description:   查看当前LoadBalancer
    * @Param:
    * @return:
    */
    @GetMapping(value = "/feign/pay/mybl")
    private String myLoadBalancer()
    {
        return payFeignApi.myLoadBalancer();
    }








}

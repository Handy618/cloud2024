package com.handy.cloud.controller;

import com.handy.cloud.entities.Order;
import com.handy.cloud.resp.ResultData;
import com.handy.cloud.service.OrderService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: cloud2024
 * @author: Handy
 * @create: 2024-05-20 15:10
 */
@RestController
public class OrderController {

    @Resource
    private OrderService orderService;

    /**
     * 创建订单
     */
    @GetMapping("/order/create")
    public ResultData create(Order order)
    {
        orderService.create(order);
        return ResultData.success(order);
    }
}
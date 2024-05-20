package com.handy.cloud.service;

import com.handy.cloud.entities.Order;

/**
 * 类描述：
 **/
public interface OrderService {
    /**
     * 创建订单
     */
    void create(Order order);
}

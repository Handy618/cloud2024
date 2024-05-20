package com.handy.cloud.service;

/**
 * 类描述：  库存Service
 *
 */
public interface StorageService {
    /**
     * 扣减库存
     */
    void decrease(Long productId, Integer count);
}

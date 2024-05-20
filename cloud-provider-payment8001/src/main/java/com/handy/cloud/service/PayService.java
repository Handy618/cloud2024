package com.handy.cloud.service;

import com.handy.cloud.entities.Pay;

import java.util.List;

/**
 * 类描述：
 **/
public interface PayService {


    public int add(Pay pay);
    public int delete(Integer id);
    public int update(Pay pay);

    public Pay getPayById(Integer id);
    public List<Pay> getAllPay();
}

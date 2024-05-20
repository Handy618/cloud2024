package com.handy.cloud.service.impl;

import com.handy.cloud.entities.Pay;
import com.handy.cloud.mapper.PayMapper;
import com.handy.cloud.service.PayService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 类描述：
 *
 * @author Handy
 * @date 2024-05-07 16:02
 **/
@Service
public class PayServiceImpl implements PayService {


    @Resource
    private PayMapper payMapper;

    @Override
    public int add(Pay pay) {
        return payMapper.insertSelective(pay); // insertSelective(Pay pay) : null的属性不会保存，会使用数据库默认值
    }

    @Override
    public int delete(Integer id) {
        return payMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int update(Pay pay) {
        return payMapper.updateByPrimaryKeySelective(pay);
        //return payMapper.updateByPrimaryKey(pay);
    }

    @Override
    public Pay getPayById(Integer id) {
        return payMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Pay> getAllPay() {
        return payMapper.selectAll();
    }
}

package com.handy.cloud.service.impl;

import com.handy.cloud.apis.AccountFeignApi;
import com.handy.cloud.apis.StorageFeignApi;
import com.handy.cloud.entities.Order;
import com.handy.cloud.mapper.OrderMapper;
import com.handy.cloud.service.OrderService;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

/**
 * @program: cloud2024
 * @author: Handy
 * @create: 2024-05-20 14:41
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderMapper orderMapper;

    @Resource//订单微服务通过OpenFeign调用库存微服务
    StorageFeignApi storageFeignApi;

    @Resource
    AccountFeignApi accountFeignApi;


    @Override
    @GlobalTransactional(name = "handy-create-order",rollbackFor = Exception.class) //AT
    public void create(Order order) {

        //0. 检查 Xid
        String xid = RootContext.getXID();


        //1. 新建订单
        log.info("------->开始新建订单"+"\t"+"xid_order:" +xid);

        //订单状态status：0：创建中；1：已完结
        //1-1. 插入到数据库中
        order.setStatus(0);  //初始订单为创建中
        int insertResult = orderMapper.insertSelective(order);


        //1-2. 从数据库中捞出来，验证是否创建订单成功
        Order orderFromDB = null;   //插入订单成功后获得插入mysql的实体对象

        if (insertResult > 0){   //若插入成功，则可以查询到记录

            orderFromDB = orderMapper.selectOne(order);   //查询该订单
            //orderFromDB = orderMapper.selectByPrimaryKey(order.getId()); //查询该订单

            log.info("-------> 新建订单成功，orderFromDB info: "+orderFromDB);
            System.out.println();


            //2. 扣减库存
            log.info("-------> 订单微服务开始调用Storage库存，做扣减count");
            storageFeignApi.decrease(orderFromDB.getProductId(), orderFromDB.getCount());
            log.info("-------> 订单微服务结束调用Storage库存，做扣减完成");
            System.out.println();

            //3. 扣减账户
            log.info("-------> 订单微服务开始调用Account账号，做扣减money");
            accountFeignApi.decrease(orderFromDB.getUserId(), orderFromDB.getMoney());
            log.info("-------> 订单微服务结束调用Account账号，做扣减完成");
            System.out.println();

            //4. 修改订单状态
            //订单状态status：0：创建中；1：已完结
            log.info("-------> 修改订单状态");
            orderFromDB.setStatus(1);

            //4-1. 通过Mybatis 查询 该用户id下  和 状态 = 0 的订单进行修改
            Example whereCondition = new Example(Order.class);   //创建example 对象
            Example.Criteria criteria = whereCondition.createCriteria();  //作为查询的where条件

            //4-2. 输入查询条件
            criteria.andEqualTo("userId",orderFromDB.getUserId());  //用户id
            criteria.andEqualTo("status",0);  //状态为0（订单创建中）

            //4-3. 修改
            int updateResult = orderMapper.updateByExampleSelective(orderFromDB, whereCondition);

            log.info("-------> 修改订单状态完成"+"\t"+updateResult);
            log.info("-------> orderFromDB info: "+orderFromDB);


        }
        System.out.println();
        log.info("==================>结束新建订单"+"\t"+"xid_order:" +xid);

    }
}
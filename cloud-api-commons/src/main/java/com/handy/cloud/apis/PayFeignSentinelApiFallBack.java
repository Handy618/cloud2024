package com.handy.cloud.apis;

import com.handy.cloud.resp.ResultData;
import com.handy.cloud.resp.ReturnCodeEnum;
import org.springframework.stereotype.Component;

/**
 * @program: 做服务降级的通用提示
 * @author: Handy
 * @create: 2024-05-19 17:36
 */
@Component
public class PayFeignSentinelApiFallBack implements PayFeignSentinelApi
{
    @Override
    public ResultData getPayByOrderNo(String orderNo)
    {
        return ResultData.fail(ReturnCodeEnum.RC500.getCode(),"对方服务宕机或不可用，FallBack服务降级o(╥﹏╥)o");
    }
}
package com.handy.cloud.apis;

import cn.hutool.core.util.IdUtil;
import com.handy.cloud.entities.PayDTO;
import com.handy.cloud.resp.ResultData;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 类描述：
 **/
//@FeignClient(value = "cloud-payment-service")  //服务提供者(公司内部微服务访问)
@FeignClient(value = "cloud-gateway")  //服务提供者(有外人的服务访问，通过gateawy网关访问)
public interface PayFeignApi {


    /**
    * @Description:  新增一条支付相关流水记录
    * @Param:  payDTO
    * @return:
    */
    @PostMapping(value = "/pay/add")
    public ResultData addPay(@RequestBody PayDTO payDTO);


    /**
    * @Description:   删除一条支付相关流水记录
    * @Param: id
    * @return:
    */
    @DeleteMapping(value = "/pay/delete/{id}")
    public ResultData delPay(@PathVariable("id") Integer id);

    /**
     * @Description:   修改一条支付相关流水记录
     * @Param: payDTO
     * @return:
     */
    @PutMapping(value = "/pay/update")
    public ResultData updatePay(@RequestBody PayDTO payDTO);




    /**
    * @Description:   按照主键记录查询支付流水信息
    * @Param:
    * @return:
    */
    @GetMapping(value = "/pay/get/{id}")
    public ResultData getPayById(@PathVariable("id") Integer id);



    /**
    * @Description:   //获取全部订单
    * @Param:
    * @return:
    */
    @GetMapping(value = "/pay/getAll")
    public ResultData getAll();

    /**
     * @Description:   //获取全部订单
     * @Param:
     * @return:
     */
    @GetMapping(value = "/pay/get/info")
    public String myLoadBalancer();

    /**
     * Resilience4j CircuitBreaker 的例子  (测试：服务熔断，服务降级)
     * @param id
     * @return
     */
    @GetMapping(value = "/pay/circuit/{id}")
    public String myCircuit(@PathVariable("id") Integer id);

    /**
     * Resilience4j Bulkhead 的例子(测试： 服务隔离)
     * @param id
     * @return
     */
    @GetMapping(value = "/pay/bulkhead/{id}")
    public String myBulkhead(@PathVariable("id") Integer id);



    //=========Resilience4j ratelimit 的例子
    @GetMapping(value = "/pay/ratelimit/{id}")
    public default String myRatelimit(@PathVariable("id") Integer id)
    {
        return "Hello, myRatelimit欢迎到来 inputId:  "+id+" \t " + IdUtil.simpleUUID();
    }



    /**
     * Micrometer(Sleuth)进行链路监控的例子
     * @param id
     * @return
     */
    @GetMapping(value = "/pay/micrometer/{id}")
    public String myMicrometer(@PathVariable("id") Integer id);

    /**
     * GateWay进行网关测试案例01
     * @param id
     * @return
     */
    @GetMapping(value = "/pay/gateway/get/{id}")
    public ResultData getById(@PathVariable("id") Integer id);

    /**
     * GateWay进行网关测试案例02
     * @return
     */
    @GetMapping(value = "/pay/gateway/info")
    public ResultData<String> getGatewayInfo();

}

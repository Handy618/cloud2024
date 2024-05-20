package com.handy.cloud.controller;

import cn.hutool.core.bean.BeanUtil;
import com.handy.cloud.entities.Pay;
import com.handy.cloud.entities.PayDTO;
import com.handy.cloud.resp.ResultData;
import com.handy.cloud.service.PayService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 类描述：
 *
 * @author Handy
 * @date 2024-05-07 15:59
 **/
@RestController
@Slf4j
@Tag(name = "支付微服务模块",description = "支付CRUD")
public class PayController {

    @Resource
    private PayService payService;

    //新增一个订单
    @PostMapping(value = "/pay/add")
    @Operation(summary = "新增",description = "新增支付流水方法,json串作参数")
    public ResultData<String> addPay(@RequestBody Pay pay){

        System.out.println(pay.toString());
        int addRes = payService.add(pay);

        return ResultData.success("成功插入记录，返回值"+addRes);


    }

    //删除一个订单
    @DeleteMapping(value = "/pay/delete/{id}")
    @Operation(summary = "删除",description = "删除支付流水方法,json串作参数")
    public ResultData<Integer> delPay(@PathVariable("id") Integer id){

        int delRes = payService.delete(id);
        return ResultData.success(delRes);
    }

    //修改一个订单
    @PutMapping(value = "/pay/update")
    @Operation(summary = "修改",description = "修改支付流水方法")
    public ResultData<String> updatePay(@RequestBody PayDTO payDTO){

        Pay pay = new Pay();

        BeanUtils.copyProperties(payDTO,pay);  //将payDTO的数据复制到pay对象中
        int updateRes = payService.update(pay);

        return  ResultData.success("成功修改记录，返回值"+updateRes);

    }
    //根据主键获得一个订单
    @GetMapping(value = "/pay/get/{id}")
    @Operation(summary = "按照ID查流水",description = "查询支付流水方法")
    public ResultData<Pay> getPayById(@PathVariable("id") Integer id){

        //if(id == -4) throw new RuntimeException("id不能为负数");  // 测试负数异常

        //休眠62秒，故意写个bug来测试出feign的默认调用超时时间
        try {
            TimeUnit.SECONDS.sleep(62);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        Pay payOne = payService.getPayById(id);


        return ResultData.success(payOne);

    }

    //获取全部订单
    @GetMapping(value = "/pay/getAll")
    @Operation(summary = "查询全部流水",description = "查询支付流水方法")
    public ResultData<List<Pay>> getAll(){

        List<Pay> allPay = payService.getAllPay();
        return ResultData.success(allPay);
    }

    //从consul中拉取端口号

    @Value("${server.port}")
    private String port;

    @GetMapping(value = "/pay/get/info")
    private String getInfoByConsul(@Value("${handy.info}") String handyInfo)
    {
        return "handyInfo: "+handyInfo+"\t"+"port: "+port;
    }
}

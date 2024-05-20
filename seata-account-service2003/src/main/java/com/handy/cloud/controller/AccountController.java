package com.handy.cloud.controller;

import com.handy.cloud.resp.ResultData;
import com.handy.cloud.service.AccountService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: cloud2024
 * @author: Handy
 * @create: 2024-05-20 15:31
 */
@RestController
public class AccountController {

    @Resource
    AccountService accountService;

    /**
     * 扣减账户余额
     */
    @RequestMapping("/account/decrease")
    public ResultData decrease(@RequestParam("userId") Long userId, @RequestParam("money") Long money){

        System.out.println("______________进入账户扣减");

        accountService.decrease(userId,money);

        return ResultData.success("扣减账户余额成功！");
    }
}
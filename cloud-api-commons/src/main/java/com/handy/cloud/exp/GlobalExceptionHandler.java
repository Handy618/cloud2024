package com.handy.cloud.exp;

import com.handy.cloud.resp.ResultData;
import com.handy.cloud.resp.ReturnCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 类描述： 全局异常处理类
 *
 * @author Handy
 * @date 2024-05-08 02:47
 **/

@Slf4j
@RestControllerAdvice  //
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)  //只要出现异常，就立即捕获
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)  //服务器错误
    public ResultData<String> exception(Exception e){
        System.out.println("----come in GlobalExceptionHandler");
        log.error("全局异常信息exception:{}",e.getMessage(),e);
        return ResultData.fail(ReturnCodeEnum.RC500.getCode(), e.getMessage());
    }

}

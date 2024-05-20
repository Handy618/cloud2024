package com.handy.cloud.resp;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 类描述：
 *
 * @author Handy
 * @date 2024-05-08 01:43
 **/
@Data
@Accessors(chain = true)
public class ResultData<T> {

    ///** 结果状态 ,具体状态码参见枚举类
    private String code;
    private String message;
    private T data;
    private long timestamp;

    public ResultData() {
        this.timestamp = System.currentTimeMillis();  //设置为当前系统时间戳;
    }


    //调用成功时，编码为200
    public static <T>   ResultData<T>  success(T data) {

        ResultData<T> resultData = new ResultData<>();

        resultData.setCode(ReturnCodeEnum.RC200.getCode());
        resultData.setMessage(ReturnCodeEnum.RC200.getMessage());
        resultData.setData(data);

        return resultData;

    }

    //调用失败
    public static <T>   ResultData<T>  fail(String code,String message) {

        ResultData<T> resultData = new ResultData<>();

        resultData.setCode(code);
        resultData.setMessage(message);
        resultData.setData(null);    //失败时不返回数据

        return resultData;

    }


}

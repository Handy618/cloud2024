package com.handy.cloud.mapper;

import com.handy.cloud.entities.Account;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

/**
 * @auther handy
 * @create 2024-05-18 18:16
 */
public interface AccountMapper extends Mapper<Account>
{

    /**
     * @param userId
     * @param money 本次消费金额
     */
    void decrease(@Param("userId") Long userId, @Param("money") Long money);
}

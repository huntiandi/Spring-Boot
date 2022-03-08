package com.yang.mapper;

import com.yang.bean.Account;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @ProjectName: com.yang.mapper
 * @author: ZhangBiBo
 * @description:
 * @data: 2022/3/8
 */
@Mapper
public interface AccountMapper {
    public Account getAccount(Integer id);

    @Select("select * from t_account where user_name = #{username}")
    public Account getAccountByUsername(String username);
}

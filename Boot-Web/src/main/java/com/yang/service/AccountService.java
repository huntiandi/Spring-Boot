package com.yang.service;

import com.yang.bean.Account;
import com.yang.mapper.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ProjectName: com.yang.service
 * @author: ZhangBiBo
 * @description:
 * @data: 2022/3/8
 */
@Service
public class AccountService {
    @Autowired
    AccountMapper accountMapper;
    public Account getAccount(Integer id){
        return accountMapper.getAccount(id);
    }

    public Account getAccountByUsername(String username){
        return accountMapper.getAccountByUsername(username);
    }
}

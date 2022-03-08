package com.yang.controller;

import com.yang.bean.Account;
import com.yang.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ProjectName: com.yang.controller
 * @author: ZhangBiBo
 * @description:
 * @data: 2022/3/1
 */
@RestController
public class UserController {
    @Autowired
    AccountService accountService;
    @GetMapping("/user")
    public String getUser(){
        return "GetUser!";
    }

    @PostMapping("/user")
    public String saveUser(){
        return "POST-张三";
    }

    @PutMapping("/user")
    public String putUser(){
        return "PUT-User";
    }

    @DeleteMapping("/user")
    public String deleteUser(){
        return "Delete-User";
    }

    @GetMapping("/account")
    public Account getAccount(@RequestParam("id") Integer id){
        return accountService.getAccount(id);
    }

    @GetMapping("/account2")
    public Account getAccountByUsername(@RequestParam("username") String username){
        return accountService.getAccountByUsername(username);
    }
}

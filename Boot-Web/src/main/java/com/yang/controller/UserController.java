package com.yang.controller;

import org.springframework.web.bind.annotation.*;

/**
 * @ProjectName: com.yang.controller
 * @author: ZhangBiBo
 * @description:
 * @data: 2022/3/1
 */
@RestController
public class UserController {
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
}

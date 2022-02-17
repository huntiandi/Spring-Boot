package com.yang.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ProjectName: com.yang
 * @author: ZhangBiBo
 * @description:
 * @data: 2022/2/16
 */
@RestController
public class HelloController {
    @RequestMapping("/hello")
    public String hello(){
        return "hello boot";
    }
}

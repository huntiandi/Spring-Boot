package com.yang.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ProjectName: com.yang
 * @author: ZhangBiBo
 * @description:
 * @data: 2022/2/16
 */
@RestController
@Slf4j
public class HelloController {
    @RequestMapping("/hello")
    public String hello(){
        log.info("hello方法被调用");
        return "hello boot";
    }
}

package com.yang.controller;

import com.yang.bean.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    Person person;
    @RequestMapping("/hello")
    public String hello(){
        log.info("hello方法被调用");
        return "hello boot";
    }

    @RequestMapping("/person")
    public Person helloPerson(){
        return person;
    }
}

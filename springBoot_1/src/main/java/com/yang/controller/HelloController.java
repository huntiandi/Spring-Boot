package com.yang.controller;

import com.yang.bean.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;

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

    @Value("${person.username}")
    private String username;

    @Value("${person.interests[0]}")
    public String interests;

    @Autowired
    private Environment environment;
    @RequestMapping("/hello")
    public String hello(){
        log.info("hello方法被调用");
        log.info(username+interests+environment.getProperty("person.interests[1]"));
        return "hello boot";
    }

    @RequestMapping("/person")
    public Person helloPerson(){
        System.out.println(person);
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(sdf.format(person.getBirth()));
        return person;
    }
}

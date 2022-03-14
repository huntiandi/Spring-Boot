package com.yang.yangspringbootstaterautoconfigure.service;

import com.yang.yangspringbootstaterautoconfigure.bean.HelloProperties;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @ProjectName: com.yang.yangspringbootstaterautoconfigure.service
 * @author: ZhangBiBo
 * @description: 会被经常调用的helloService逻辑；默认不放入容器
 * @data: 2022/3/14
 */
public class HelloService {
    //自动注入了实体类helloProperties,所以修改配置类的时候,属性就会跟着修改
    @Autowired
    HelloProperties helloProperties;
    public String hello(String username){
        return helloProperties.getPrefix()+username+helloProperties.getSuffix();
    }
}

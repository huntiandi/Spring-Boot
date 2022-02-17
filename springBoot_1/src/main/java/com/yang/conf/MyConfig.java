package com.yang.conf;

import com.yang.bean.Pet;
import com.yang.bean.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ProjectName: com.yang.conf
 * @author: ZhangBiBo
 * @description: 配置类，给容器注册组件，默认是单实例的
 * @data: 2022/2/17
 */
@Configuration(proxyBeanMethods = true)//这是一个配置类,proxyBeanMethods是否为bean的代理方法，默认是开启的会保证组件间依赖，
public class MyConfig {

    /**
     * 外部无论对配置类组件组测方法调用多少次，都是获取到第一次组测的单实例对象
     * @return
     */
    @Bean
    public User user01(){
        User user = new User("张三", 18);
        user.setPet(pet01());
        return user;
    }

    @Bean("tom")
    public Pet pet01(){
        return new Pet("当当");
    }
}

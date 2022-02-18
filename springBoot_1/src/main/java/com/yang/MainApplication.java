package com.yang;

import com.yang.bean.Pet;
import com.yang.bean.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * @ProjectName: com.yang
 * @author: ZhangBiBo
 * @description:
 * @data: 2022/2/16
 */
//@SpringBootApplication(scanBasePackages = "com")
@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan("com")
public class MainApplication {
    public static void main(String[] args) {
        //返回的是一个ioc容器
        ConfigurableApplicationContext run = SpringApplication.run(MainApplication.class, args);

        //获取容器中的所有组件名称
        String[] names = run.getBeanDefinitionNames();
        /*for (String name:names) {
            System.out.println(name);
        }*/

        //单例的
       /* User user01 = run.getBean("user01", User.class);
        User user02 = run.getBean("user01", User.class);
        System.out.println(user01 == user02);

        //proxyBeanMethods = false 取消了组件依赖
        User user03 = run.getBean("user01", User.class);
        Pet tom = run.getBean("tom", Pet.class);
        System.out.println(user03.getPet() == tom);*/

        //测试Conditional注解      containsBean是否包含该bean
        boolean b1 = run.containsBean("user01");
        System.out.println(b1);
    }
}

package com.yang.yangspringbootstaterautoconfigure.conf;

import com.yang.yangspringbootstaterautoconfigure.bean.HelloProperties;
import com.yang.yangspringbootstaterautoconfigure.service.HelloService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ProjectName: com.yang.yangspringbootstaterautoconfigure.conf
 * @author: ZhangBiBo
 * @description: 配置类，打开实体类和properties的关联,并将其注入容器，当容器中没有helloService实体类的时候自动配置，若有就按照用户的来
 * @data: 2022/3/14
 */
@EnableConfigurationProperties(HelloProperties.class)
@Configuration
public class YangAutoConfiguration {

    @ConditionalOnMissingBean(HelloService.class)
    @Bean
    public HelloService helloService(){
        HelloService helloService = new HelloService();
        return helloService;
    }
}

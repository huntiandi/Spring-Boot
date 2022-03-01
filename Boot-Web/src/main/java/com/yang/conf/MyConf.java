package com.yang.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.HiddenHttpMethodFilter;

/**
 * @ProjectName: com.yang.conf
 * @author: ZhangBiBo
 * @description: 自定义一个HiddenHttpMethodFilter
 * @data: 2022/3/1
 */
@Configuration(proxyBeanMethods = false)
public class MyConf {
    @Bean
    public HiddenHttpMethodFilter getHiddenHttpMethodFilter(){
        HiddenHttpMethodFilter methodFilter = new HiddenHttpMethodFilter();
        methodFilter.setMethodParam("_yang");
        return methodFilter;
    }
}

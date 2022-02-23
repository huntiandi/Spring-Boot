package com.yang.bean;

import lombok.*;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @ProjectName: com.yang.bean
 * @author: ZhangBiBo
 * @description: 测试自动绑定的bean
 * @data: 2022/2/18
 */
//@Component
@Data  //get,set方法
@ToString //toString方法
@AllArgsConstructor //全参构造器
@NoArgsConstructor //无参构造器
@EqualsAndHashCode //hash方法
@ConfigurationProperties(prefix = "mycar")
//@ConditionalOnProperty(prefix = "mycar")
public class Car {
    private String brand;
    private Integer price;
}

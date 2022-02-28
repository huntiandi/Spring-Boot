package com.yang.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @ProjectName: com.yang.bean
 * @author: ZhangBiBo
 * @description:  测试yaml文件
 * @data: 2022/2/27
 */
@Component
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@ConfigurationProperties(prefix = "person")
public class Person {
    private String username;
    private Integer age;
    private boolean boos;
    private Date birth;
}

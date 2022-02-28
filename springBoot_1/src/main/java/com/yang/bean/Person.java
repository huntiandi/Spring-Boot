package com.yang.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
    @DateTimeFormat(pattern = "yyyy/MM/dd")
//    @JsonFormat(pattern = "yyyy/MM/dd")
    private Date birth;
    private Pet pet;
    private String[] interests;
    private List<String> animal;
    private Map<String, Object> score;
    private Set<Double> salary;
    private Map<String, List<Pet>> allPets;
}

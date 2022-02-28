package com.yang.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @ProjectName: com.yang.bean
 * @author: ZhangBiBo
 * @description:
 * @data: 2022/2/17
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Pet {
    private String name;
    private Integer age;

    public Pet(String name) {
        this.name = name;
    }
}

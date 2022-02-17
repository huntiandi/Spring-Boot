package com.yang.bean;

/**
 * @ProjectName: com.yang.bean
 * @author: ZhangBiBo
 * @description:
 * @data: 2022/2/17
 */
public class Pet {
    private String name;

    public String getName() {
        return name;
    }

    public Pet() {
    }

    public Pet(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Pet{" +
                "name='" + name + '\'' +
                '}';
    }
}

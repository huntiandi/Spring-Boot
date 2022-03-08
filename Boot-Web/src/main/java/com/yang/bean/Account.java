package com.yang.bean;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Account {
    private String username;
    private Integer id;
    private Integer money;

}

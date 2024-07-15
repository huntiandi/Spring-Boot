package com.yang.entity;

import lombok.Data;

@Data
public class ParagraphEntity {
    private int id; // 段落ID
    private String fileName; // 文件名
    private String content; // 段落内容

}
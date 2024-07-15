package com.yang.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParagraphDto {
    private int id; // 段落ID
    private String fileName; // 文件名
    private String content; // 段落内容

}
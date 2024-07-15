package com.yang.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class FilterTextDto {
    public int id;

    @ApiModelProperty("筛选规则")
    public String filterRule;

    @ApiModelProperty("被筛选文件的名称")
    public String fileName;

    @ApiModelProperty("被命中的段落")
    public String fileText;
}

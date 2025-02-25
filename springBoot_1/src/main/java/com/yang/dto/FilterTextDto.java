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

    @ApiModelProperty("段落ID")
    public Integer textId;

    @ApiModelProperty("段落顺序")
    public Integer textNum;

    @ApiModelProperty("被命中的段落")
    public String fileText;

    @ApiModelProperty("备注")
    public String remark;
}

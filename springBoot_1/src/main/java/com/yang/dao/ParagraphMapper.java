package com.yang.dao;

import com.yang.entity.ParagraphEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

public interface ParagraphMapper {
    List<ParagraphEntity> select();

    void insertList(List<ParagraphEntity> entityList);
}
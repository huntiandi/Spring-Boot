package com.yang.dao;

import com.yang.entity.FilterTextEntity;

import java.util.List;

public interface FilterTextMapper {
    List<FilterTextEntity> selectAll(String rule);

    void insertList(List<FilterTextEntity> entityList);
}

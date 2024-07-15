package com.yang.service;

import com.yang.dto.FilterTextDto;
import com.yang.dto.ParagraphDto;

import java.util.List;

public interface FilterTextService {
    List<FilterTextDto> selectAll(String rule);

    void insertList(List<FilterTextDto> dtoList);

    void creatFilterText(List<ParagraphDto> dtoList, String rule);
}

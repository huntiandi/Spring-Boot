package com.yang.service;

import com.yang.dto.ParagraphDto;
import com.yang.entity.ParagraphEntity;

import java.util.List;

public interface ParagraphService {
    List<ParagraphDto> select();

    void insertList(List<ParagraphDto> dtoList);
}

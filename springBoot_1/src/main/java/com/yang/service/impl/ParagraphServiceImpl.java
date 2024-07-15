package com.yang.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.yang.dao.ParagraphMapper;
import com.yang.dto.ParagraphDto;
import com.yang.entity.ParagraphEntity;
import com.yang.service.ParagraphService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("paragraphService")
@Slf4j
public class ParagraphServiceImpl implements ParagraphService{

    @Autowired
    public ParagraphMapper paragraphMapper;

    @Override
    public List<ParagraphDto> select() {
        List<ParagraphEntity> entityList = paragraphMapper.select();
        return BeanUtil.copyToList(entityList, ParagraphDto.class);
    }

    @Override
    @Transactional
    public void insertList(List<ParagraphDto> dtoList) {
        paragraphMapper.insertList(BeanUtil.copyToList(dtoList, ParagraphEntity.class));
    }
}

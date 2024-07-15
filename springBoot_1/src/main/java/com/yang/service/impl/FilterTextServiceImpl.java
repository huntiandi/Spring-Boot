package com.yang.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import com.yang.dao.FilterTextMapper;
import com.yang.dto.FilterTextDto;
import com.yang.dto.ParagraphDto;
import com.yang.entity.FilterTextEntity;
import com.yang.service.FilterTextService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


@Service("filterTextService")
@Slf4j
@Transactional
public class FilterTextServiceImpl implements FilterTextService {
    @Resource
    public FilterTextMapper filterTextMapper;

    @Override
    public List<FilterTextDto> selectAll(String rule) {
        List<FilterTextEntity> entityList = filterTextMapper.selectAll(rule);
        return BeanUtil.copyToList(entityList, FilterTextDto.class);
    }

    @Override
    public void insertList(List<FilterTextDto> dtoList) {
        if (CollUtil.isNotEmpty(dtoList)) {
            List<FilterTextEntity> entityList = BeanUtil.copyToList(dtoList, FilterTextEntity.class);
            filterTextMapper.insertList(entityList);
        }
    }

    @Override
    public void creatFilterText(List<ParagraphDto> dtoList, String rule) {
        Pattern pattern = Pattern.compile(rule);
        List<FilterTextDto> resultList = new ArrayList<>();
        dtoList.forEach(i -> {
            String content = i.getContent();
            Matcher matcher = pattern.matcher(content.replaceAll("\\s",""));
            if (matcher.find()) {
                FilterTextDto textDto = new FilterTextDto();
                textDto.setFileText(content);
                textDto.setFilterRule(rule);
                textDto.setFileName(i.getFileName());
                resultList.add(textDto);
            }
        });
        insertList(resultList);
    }
}

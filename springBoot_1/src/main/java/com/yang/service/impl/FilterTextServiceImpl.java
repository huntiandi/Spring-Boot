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
import java.util.stream.IntStream;


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
    /*public void creatFilterText(List<ParagraphDto> dtoList, String rule) {
        Pattern pattern = Pattern.compile(rule);
        List<FilterTextDto> resultList = new ArrayList<>();
        dtoList.forEach(i -> {
            String content = i.getContent();
            //要去掉文本中的换行符\\s否则会漏掉
            Matcher matcher = pattern.matcher(content.replaceAll("\\s",""));
            if (matcher.find()) {
                FilterTextDto textDto = new FilterTextDto();
                textDto.setFileText(content.replaceAll("\\s",""));
                textDto.setFilterRule(rule);
                textDto.setFileName(i.getFileName());
                textDto.setTextId(i.getId());
                if (".*(倍.*保险金额|保险金额.*倍).*".equals(rule)) {
                    textDto.setRemark(getMultiple(content));
                }
                resultList.add(textDto);
            }
        });
        Map<String, List<FilterTextDto>> resultMap = resultList.stream().collect(Collectors.groupingBy(FilterTextDto::getFileName));
        resultMap.forEach((k,v)->{
            int[] num = new int[]{1};
            v.forEach(i->{
                i.setTextNum(num[0]);
                num[0]++;
            });
        });
        insertList(resultList);
    }*/

    public void creatFilterText(List<ParagraphDto> dtoList, String rule) {
        Pattern pattern = Pattern.compile(rule);
        List<FilterTextDto> resultList = dtoList.stream()
                .filter(i -> pattern.matcher(i.getContent().replaceAll("\\s", "")).find())
                .map(i -> {
                    FilterTextDto textDto = new FilterTextDto();
                    textDto.setFileText(i.getContent().replaceAll("\\s", ""));
                    textDto.setFilterRule(rule);
                    textDto.setFileName(i.getFileName());
                    textDto.setTextId(i.getId());
                    if (".*宽限期.*".equals(rule)) {
                        textDto.setRemark(getMultiple(i.getContent()));
                    }
                    return textDto;
                })
                .collect(Collectors.toList());

        Map<String, List<FilterTextDto>> resultMap = resultList.stream()
                .collect(Collectors.groupingBy(FilterTextDto::getFileName));

        resultMap.forEach((k, v) -> {
            IntStream.range(0, v.size()).forEach(i -> v.get(i).setTextNum(i + 1));
        });

        insertList(resultList);
    }


    /**
     * 根据文本获取相应的倍数
     * @param text
     * @return
     */
    private String getMultiple(String text) {
        String regex = "\\d+[日天]";
        String result = null;
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text.replaceAll("\\s",""));
        if (matcher.find()) {
            result = matcher.group();
        }
        return result;
    }
}

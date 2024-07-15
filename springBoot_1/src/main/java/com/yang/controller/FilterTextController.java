package com.yang.controller;

import com.yang.dto.FilterTextDto;
import com.yang.dto.ParagraphDto;
import com.yang.service.FilterTextService;
import com.yang.service.ParagraphService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/ruleText")
@Slf4j
public class FilterTextController {

    @Resource
    public FilterTextService filterTextService;

    @Resource
    public ParagraphService paragraphService;

    @GetMapping("/getAll/{rule}")
    public List<FilterTextDto> select(@PathVariable("rule") String rule) {
        return filterTextService.selectAll(rule);
    }

    @PostMapping("/add")
    public void insert() {
        List<ParagraphDto> paragraphList = paragraphService.select();
        String rule = ".*(被保险人.*首个保险费约定支付日起|首个保险费约定支付日起.*被保险人).*";
        filterTextService.creatFilterText(paragraphList, rule);
    }
}


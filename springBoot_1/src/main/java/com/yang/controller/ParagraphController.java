package com.yang.controller;

import com.yang.dto.ParagraphDto;
import com.yang.service.ParagraphService;
import com.yang.tool.WordDocumentProcessor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.File;
import java.util.List;

@RestController
@RequestMapping("/paragraph")
@Slf4j
public class ParagraphController {
    @Resource
    public ParagraphService paragraphService;

    @Resource
    public WordDocumentProcessor wordDocumentProcessor;

    @GetMapping("/get")
    public List<ParagraphDto> select(){
        return paragraphService.select();
    }

    @PostMapping(value = "/insert")
    public void insert() {
        File folder = new File("D:\\output");
        File[] files = folder.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    log.info("要保存的文件是-----》{}",file.getName());
                    List<ParagraphDto> dtoList = wordDocumentProcessor.extractParagraphs(file);
                    paragraphService.insertList(dtoList);
                }
            }
        }
    }
}

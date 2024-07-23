package com.yang.tool;

import com.yang.dto.ParagraphDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Paragraph;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.util.StringUtil;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 将word文档进行拆分处理的工具类
 */
@Slf4j
@Component
public class WordDocumentProcessor {
    public static void main(String[] args) {
//        processWordDocument("D:\\inputPath\\步步高增额终身寿险（1999年6月）.doc", "\\d+倍保险金额|保险金额的\\d+倍\n");
        WordDocumentProcessor processor = new WordDocumentProcessor();
        List<ParagraphDto> dtoList = processor.extractParagraphs(new File("D:\\inputPath\\6母子福利定期寿险条款.docx"));
    }

    /**
     * 从Word文档中根据规则筛选并保存符合条件的段落、文件名和规则
     *
     * @param filePath    Word文档的文件路径
     * @param rulePattern 规则的正则表达式
     */
    public static void processWordDocument(String filePath, String rulePattern) {
        try {
            // 读取Word文档
            FileInputStream fis = new FileInputStream(filePath);
            XWPFDocument document = new XWPFDocument(fis);
            List<XWPFParagraph> paragraphs = document.getParagraphs();

            // 编译规则的正则表达式
            Pattern pattern = Pattern.compile(rulePattern);
            // 创建输出文件的写入器
            FileWriter writer = new FileWriter("D:\\output3\\output.txt");

            // 遍历每个段落，进行规则匹配并保存符合条件的段落、文件名和规则
            for (XWPFParagraph paragraph : paragraphs) {
                String text = paragraph.getText();
                Matcher matcher = pattern.matcher(text);
                if (matcher.find()) {
                    writer.write("File Name: " + filePath + "\n");
                    writer.write("Paragraph: " + text + "\n");
                    writer.write("Rule: " + matcher.group() + "\n\n");
                }
            }

            // 关闭写入器和文件输入流
            writer.close();
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // 提取Word文档中的段落并返回段落实体列表

    /**
     * 将该文件的文本内容拆分返回
     *
     * @param file 文件
     * @return 拆分后的文件段落实体
     */
    public List<ParagraphDto> extractParagraphs(File file) {
        List<ParagraphDto> result = new ArrayList<>();
        try {
            if (file.getName().toLowerCase().endsWith(".docx")) {
                XWPFDocument document = new XWPFDocument(Files.newInputStream(file.toPath()));
                document.getParagraphs().forEach(i -> {
                    if (StringUtil.isNotBlank(i.getText())) {
                        String[] split = i.getText().split("。");
                        for (String s : split) {
                            ParagraphDto dto = new ParagraphDto();
                            dto.setContent(s);
                            dto.setFileName(file.getName());
                            result.add(dto);
                        }
                    }
                });
            } else if (file.getName().toLowerCase().endsWith(".doc")) {
                FileInputStream fis = new FileInputStream(file);
                HWPFDocument document = new HWPFDocument(fis);
                Range range = document.getRange();
                for (int i = 0; i < range.numParagraphs(); i++) {
                    Paragraph paragraph = range.getParagraph(i);
                    String text = paragraph.text();
                    if (StringUtil.isNotBlank(text)) {
                        String[] split = text.split("。");
                        for (String s : split) {
                            ParagraphDto dto = new ParagraphDto();
                            dto.setContent(s);
                            dto.setFileName(file.getName());
                            result.add(dto);
                        }
                    }
                }
                fis.close();
            } else {
                log.error("不支持的文件类型: {}", file.getName());
            }
            log.debug("得到的结果集--->{}", result);
        } catch (IOException e) {
            e.printStackTrace();
            log.error("读取文件段落出错: {}", e.getMessage());
        }
        return result;
    }


}

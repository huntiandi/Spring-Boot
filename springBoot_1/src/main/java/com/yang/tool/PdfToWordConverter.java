package com.yang.tool;

import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.util.StringUtil;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

/**
 * pdf文件转换为word文件，
 * 如果pdf中是图片或者为空则直接复制到备份目录
 */
@Slf4j
public class PdfToWordConverter {
    private String inputPath = "D:\\pdf转word工具\\【险种定义表条款集合2】\\养老金保险";

    public void convertAllPdfToWord() {
        File inputDir = new File(inputPath);
        File[] pdfFiles = inputDir.listFiles((dir, name) -> name.toLowerCase().endsWith(".pdf"));

        if (pdfFiles != null && pdfFiles.length > 0) {
            for (File pdfFile : pdfFiles) {
                convertPdfToWord(pdfFile);
            }
        } else {
            log.warn("No PDF files found in the input directory.");
        }
    }

    private void convertPdfToWord(File pdfFile) {
        try {
            PDDocument pdf = PDDocument.load(pdfFile);
            PDFTextStripper stripper = new PDFTextStripper();
            String text = stripper.getText(pdf);
            String outputPath = "D:\\output";
            if (StringUtil.isBlank(text)) {
                outputPath = "D:\\output2";
                Files.copy(pdfFile.toPath(), new File(outputPath, pdfFile.getName()).toPath(), StandardCopyOption.REPLACE_EXISTING);
                log.info("图片类型直接复制 {}", pdfFile.getName());
            } else {
                XWPFDocument doc = new XWPFDocument();
                XWPFParagraph para = doc.createParagraph();
                XWPFRun run = para.createRun();
                run.setText(text);

                //replace方法要区分大小写
                String outputFileName = pdfFile.getName().replaceAll("(?i).pdf", ".docx");
                File outputDir = new File(outputPath);
                if (!outputDir.exists()) {
                    outputDir.mkdirs();
                }
                FileOutputStream out = new FileOutputStream(new File(outputDir, outputFileName));

                doc.write(out);
                out.close();
                pdf.close();
                log.info("Converted {} to {}", pdfFile.getName(), outputFileName);
            }

        } catch (IOException e) {
            log.error("Error converting {} to Word: {}", pdfFile.getName(), e.getMessage());
        }
    }

    public static void main(String[] args) {
        PdfToWordConverter converter = new PdfToWordConverter();
        converter.convertAllPdfToWord();
    }
}

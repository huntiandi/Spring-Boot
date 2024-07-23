package com.yang.tool;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * java实现从excel中按照规则获取数据并重新生成excel的工具类
 */
public class ExcelFilter {
    public static void main(String[] args) {
        try {
            FileInputStream file = new FileInputStream("D:\\pdf转word工具\\classcode0618.XLSX");
            Workbook workbook = new XSSFWorkbook(file);
            Sheet sheet = workbook.getSheetAt(0);

            Workbook newWorkbook = new XSSFWorkbook();
            Sheet newSheet = newWorkbook.createSheet("Filtered Data");

            int newRowNum = 0;

            for (Row row : sheet) {
                Cell cellG = row.getCell(6); // G列对应的索引是6

                if (cellG != null && cellG.getCellType() == CellType.STRING) {
                    String value = cellG.getStringCellValue();
                    Pattern pattern = Pattern.compile(".*额外.*");
                    Matcher matcher = pattern.matcher(value);
                    if (matcher.find()) {
                        Row newRow = newSheet.createRow(newRowNum++);
                        for (int i = 0; i < row.getLastCellNum(); i++) {
                            Cell newCell = newRow.createCell(i);
                            Cell oldCell = row.getCell(i);
                            if (oldCell != null) {
                                switch (oldCell.getCellType()) {
                                    case STRING:
                                        newCell.setCellValue(oldCell.getStringCellValue());
                                        break;
                                    case NUMERIC:
                                        newCell.setCellValue(oldCell.getNumericCellValue());
                                        break;
                                    // 可以根据需要处理更多类型
                                }
                            }
                        }
                    }
                }
            }

            FileOutputStream outputStream = new FileOutputStream("D:\\output3\\output.xlsx");
            newWorkbook.write(outputStream);
            outputStream.close();

            workbook.close();
            newWorkbook.close();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

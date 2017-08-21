package com.adcc.poi.test;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;

public class TestProtectedCell {
    public static void main(String[] args) {
        try {
            String file = "D:\\poiProtectedCellTest.xlsx";
            FileOutputStream outputStream = new FileOutputStream(file);
            Workbook wb = new XSSFWorkbook();

            //可编辑的单元格样式
            CellStyle unlockedCellStyle = wb.createCellStyle();
            unlockedCellStyle.setLocked(true);

            Sheet sheet = wb.createSheet();
            sheet.protectSheet("123456");//password。

            Row row = sheet.createRow(0);
            Cell cell = row.createCell(0);
            cell.setCellValue("TEST");
            //设置单元格样式：可编辑
            cell.setCellStyle(unlockedCellStyle);

            wb.write(outputStream);
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

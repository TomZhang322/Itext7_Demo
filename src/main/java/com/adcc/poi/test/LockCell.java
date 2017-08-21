package com.adcc.poi.test;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;

/**
 * Created by ZP on 2017/8/3.
 */
public class LockCell {
    public static void WriteExcelByPoi(String fileData) throws IOException,
            InvalidFormatException {
        try {
            InputStream in = new FileInputStream(fileData);

            Workbook workbook = new XSSFWorkbook(in);
            org.apache.poi.ss.usermodel.Sheet sheet = (org.apache.poi.ss.usermodel.Sheet)workbook.getSheetAt(1);
            sheet.protectSheet(""); //设置表单保护密码


            org.apache.poi.ss.usermodel.Row row = null;
            org.apache.poi.ss.usermodel.Cell cell = null;

            String cellValue = "132700002800";
            XSSFCellStyle alterableStyle = (XSSFCellStyle)workbook.createCellStyle(); //获取当前单元格的样式对象
            alterableStyle.setLocked(true);    //设定此单元格为锁定状态
            XSSFCellStyle nolockedStyle = (XSSFCellStyle)workbook.createCellStyle(); //获取当前单元格的样式对象
            nolockedStyle.setLocked(false);    //设定此单元格为非锁定状态

            String value = "非锁定";

            for (int i = 0; i < 5; i++) {
                System.out.println(" i =" + i);
                row = sheet.createRow(i);
                cell = row.createCell(0);
                cell.setCellValue(cellValue);
                cell.setCellStyle(alterableStyle);
                cell = row.createCell(1);
                cell.setCellValue(value);
                cell.setCellStyle(nolockedStyle);
            }

            in.close();

            FileOutputStream out = null;
            try {
                out = new FileOutputStream(fileData);
                workbook.write(out);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException, InvalidFormatException {
        WriteExcelByPoi("D:\\poiProtectedCell.xls");
    }

}

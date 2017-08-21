package com.adcc.poi.template;

import com.adcc.utility.time.Time;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

/**
 * Created by ZP on 2017/7/31.
 */
public class ExportTempExcel {

    private static String filename = "";

    public static void createExcel() {
        try {
            //excel模板路径
            File fi=new File("src/main/resources/excelTemplate/bill2.xls");
            POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(fi));
            //读取excel模板
            HSSFWorkbook wb = new HSSFWorkbook(fs);
            //读取了模板内所有sheet内容
            HSSFSheet sheet = wb.getSheetAt(0);

            sheet.createRow(1);

            //在相应的单元格进行赋值
            HSSFCell cellName = sheet.getRow(1).createCell((short) 0);
            cellName.setCellValue("账单名");

            HSSFCell cellUser = sheet.getRow(1).createCell((short) 1);
            cellUser.setCellValue("CAN");

            HSSFCell cellBeginTime = sheet.getRow(1).createCell((short) 2);
            cellBeginTime.setCellValue("2017-01-01");

            HSSFCell cellEndTime = sheet.getRow(1).createCell((short) 3);
            cellEndTime.setCellValue("2017-09-30");

            HSSFCell cellCount = sheet.getRow(1).createCell((short) 4);
            cellCount.setCellValue("5000000");

            HSSFCell cellCost = sheet.getRow(1).createCell((short) 5);
            cellCost.setCellValue("5001");

            //修改模板内容导出新模板
            filename =filename+ Time.dateToStr(new Date(), "yyyyMMddHHmmss");
            FileOutputStream out = new FileOutputStream("e:/"+filename+".xls");
            wb.write(out);
            out.close();
            out.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        createExcel();
    }

}

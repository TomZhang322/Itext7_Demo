package com.adcc.poi.template;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * http://blog.csdn.net/fengxing_2/article/details/50606785
 */
public class ExportTempExcelXlsx {
    /**
     * 将读取到的文件重新写入新的文件
     *
     * @param output
     * @param request
     * @param list
     */
    public void createExcel(List<Map<String, String>> list, OutputStream output, HttpServletRequest request) {

        Workbook tempWorkBook = null;
        Sheet tempSheet = null;
        int rowIndex = 5;
        Row tempRow = null;
        Cell tempCell = null;
        InputStream inputstream = null;
        try {
            inputstream = request.getSession().getServletContext().getResourceAsStream("/templates/FZL-SUMMARY.xlsx");
            // 获取模板
            tempWorkBook = new XSSFWorkbook(inputstream);
            // 获取模板sheet页
            tempSheet = tempWorkBook.getSheetAt(0);

            // 用于统计的变量
            double preAmount = 0;
            double plusAmount = 0;
            double reduceAmount = 0;
            double afterAmount = 0;

            // 将数据写入excel
            for (int i = 0; i < list.size(); i++) {

                int cellNo = 1;
                // 获取指定行
                tempRow = tempSheet.getRow(rowIndex++);
                // 获取指定行的单元格
                tempCell = tempRow.getCell(cellNo++);
                // 给单元格设值
                tempCell.setCellValue(Integer.valueOf(list.get(i).get("accountsType")));

                double curpreAmount = Double.valueOf(list.get(i).get("preAmount"));
                tempCell = tempRow.getCell(cellNo++);
                tempCell.setCellValue(new BigDecimal(curpreAmount).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());

                double curplusAmount = Double.valueOf(list.get(i).get("plusAmount"));
                tempCell = tempRow.getCell(cellNo++);
                tempCell.setCellValue(new BigDecimal(curplusAmount).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());

                double curreduceAmount = Double.valueOf(list.get(i).get("reduceAmount"));
                tempCell = tempRow.getCell(cellNo++);
                tempCell.setCellValue(new BigDecimal(curreduceAmount).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());

                cellNo++;

                double curafterAmount = Double.valueOf(list.get(i).get("afterAmount"));
                tempCell = tempRow.getCell(cellNo++);
                tempCell.setCellValue(new BigDecimal(curafterAmount).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());

                plusAmount += Double.valueOf(list.get(i).get("preAmount"));
                reduceAmount += Double.valueOf(list.get(i).get("plusAmount"));
                afterAmount += Double.valueOf(list.get(i).get("reduceAmount"));
                preAmount += Double.valueOf(list.get(i).get("afterAmount"));

                if (i == (list.size() - 1)) {

                    // 创建一行 统计
                    tempRow = tempSheet.getRow(rowIndex);
                    int cellNo2 = 2;
                    // 获取单元格
                    tempCell = tempRow.getCell(cellNo2++);
                    // 给单元格设值
                    tempCell.setCellValue(new BigDecimal(plusAmount).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
                    tempCell = tempRow.getCell(cellNo2++);
                    tempCell.setCellValue(new BigDecimal(reduceAmount).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
                    tempCell = tempRow.getCell(cellNo2++);
                    tempCell.setCellValue(new BigDecimal(afterAmount).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
                    cellNo2++;
                    tempCell = tempRow.getCell(cellNo2++);
                    tempCell.setCellValue(new BigDecimal(preAmount).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
                }

            }
            // 将内容写入Excel
            tempWorkBook.write(output);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                output.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}

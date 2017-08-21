package com.adcc.poi.javax;

import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.web.servlet.view.document.AbstractExcelView;

/**
 * http://www.cnblogs.com/gx-java/p/6218118.html
 */
public class ExcelView extends AbstractExcelView {
    @Override
    protected void buildExcelDocument(Map<String, Object> model, HSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {
        @SuppressWarnings("unchecked")
        List<AuContract> list = (List<AuContract>) model.get("infoList");

        HSSFFont font= workbook.createFont();
        font.setFontHeightInPoints((short)12);            //设置字体的大小
        font.setFontName("微软雅黑");                        //设置字体的样式，如：宋体、微软雅黑等
        font.setItalic(false);                            //斜体true为斜体
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);    //对文中进行加粗
        font.setColor(HSSFColor.PINK.index);            //设置字体的颜色
        HSSFCellStyle style = workbook.createCellStyle();
        style.setFont(font);

        if (list != null && list.size() != 0) {
            int length = list.size();
            Sheet sheet = workbook.createSheet();

            // 第一行文字说明
            Row row = sheet.createRow(0);
            Cell cell = row.createCell(0, Cell.CELL_TYPE_STRING);
            cell.setCellStyle(style);
            cell.setCellValue("合同名称");

            cell = row.createCell(1, Cell.CELL_TYPE_STRING);
            cell.setCellStyle(style);
            cell.setCellValue("合同单位");

            cell = row.createCell(2, Cell.CELL_TYPE_STRING);
            cell.setCellStyle(style);
            cell.setCellValue("合同登记时间");

            cell = row.createCell(3, Cell.CELL_TYPE_STRING);
            cell.setCellStyle(style);
            cell.setCellValue("合同金额");

            cell = row.createCell(4, Cell.CELL_TYPE_STRING);
            cell.setCellStyle(style);
            cell.setCellValue("履行方式");

            cell = row.createCell(5, Cell.CELL_TYPE_STRING);
            cell.setCellStyle(style);
            cell.setCellValue("合同类型");

            cell = row.createCell(6, Cell.CELL_TYPE_STRING);
            cell.setCellStyle(style);
            cell.setCellValue("开始时间");

            cell = row.createCell(7, Cell.CELL_TYPE_STRING);
            cell.setCellStyle(style);
            cell.setCellValue("结束时间");

            cell = row.createCell(8, Cell.CELL_TYPE_STRING);
            cell.setCellStyle(style);
            cell.setCellValue("备注");

            // 下面是具体内容
            for (int i = 0; i < length; i++) {
                sheet.setColumnWidth((short) i, (short) (35.7 * 100));
                row = sheet.createRow(i + 1);
                // 合同名称
                cell = row.createCell(0, cell.CELL_TYPE_STRING);
                cell.setCellValue(list.get(i).getName());
                // 合同单位
                cell = row.createCell(1, cell.CELL_TYPE_STRING);
                cell.setCellValue(list.get(i).getUnit());

                // 合同登记时间
                cell = row.createCell(2, cell.CELL_TYPE_STRING);
                cell.setCellValue(new SimpleDateFormat("yyyy-mm-dd").format(list.get(i).getReg_time()));


                // 合同金额
                cell = row.createCell(3, cell.CELL_TYPE_STRING);
                //把float对象转换为String对象
                float con_money=list.get(i).getCon_money();
                String str =String.valueOf(con_money);
                cell.setCellValue(str);

                // 合同履行方式
                cell = row.createCell(4, cell.CELL_TYPE_STRING);
                cell.setCellValue(list.get(i).getPerform_style());

                // 合同类型
                cell = row.createCell(5, cell.CELL_TYPE_STRING);
                cell.setCellValue(list.get(i).getCon_type());

                // 开始时间
                cell = row.createCell(6, cell.CELL_TYPE_STRING);
                cell.setCellValue(new SimpleDateFormat("yyyy-mm-dd").format(list.get(i).getCon_start_time()));

                // 结束时间
                cell = row.createCell(7, cell.CELL_TYPE_STRING);
                cell.setCellValue(new SimpleDateFormat("yyyy-mm-dd").format(list.get(i).getCon_end_time()));

                // 备注
                cell = row.createCell(8, cell.CELL_TYPE_STRING);
                cell.setCellValue(list.get(i).getRemark());
            }

            //web浏览通过MIME类型判断文件是excel类型
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
            response.setCharacterEncoding("utf-8");

            // 对文件名进行处理。防止文件名乱码
//            String fileName = CharEncodingEdit.processFileName(request, "合同.xls");
            String fileName = "Contract.xml";
            // Content-disposition属性设置成以附件方式进行下载
            response.setHeader("Content-disposition", "attachment;filename=" + fileName);
            OutputStream os = response.getOutputStream();
            workbook.write(os);
            os.flush();
            os.close();
        }

    }
}

package com.adcc.poi;

import com.adcc.utility.time.Time;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.general.DefaultPieDataset;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Date;

/**
 * 生成账单excel，附带饼图
 */
public class CreateBillExcel {
    private static String filename = "Bill-";
    private static BufferedImage bufferImg = null;

    //图片生成的路径
    private static String pathOfPicture = "src/main/resources/excelTemplate/pic/bill.jpeg";
    //Excel生成的路径
    private static String pathOfExcel = "E:/test.xls";
    //excel模板路径
    private static String pathOfTemplate = "src/main/resources/excelTemplate/billTemplate.xls";

    public static void createExcel() {
        try {
            //excel模板路径
            File fi=new File(pathOfTemplate);
            POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(fi));
            //读取excel模板
            HSSFWorkbook wb = new HSSFWorkbook(fs);

            //--- sheet0总账单,读取了模板内所有sheet内容
            HSSFSheet sheet = wb.getSheetAt(0);
            //创建第一行
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
            cellCost.setCellValue("5000");


            //--- sheet1明细,读取了模板内所有sheet内容
            HSSFSheet sheetInfo = wb.getSheetAt(1);
            for (int i=0; i<5; i++) {
                //创建一行
                sheetInfo.createRow(i+1);
                //在相应的单元格进行赋值
                HSSFCell celType = sheetInfo.getRow(i+1).createCell((short) 0);
                celType.setCellValue("POS");

                HSSFCell cellNum = sheetInfo.getRow(i+1).createCell((short) 1);
                cellNum.setCellValue("100000");

                HSSFCell cellOneCost = sheetInfo.getRow(i+1).createCell((short) 2);
                cellOneCost.setCellValue("1000");
            }

            //--- sheet2图形分析,读取了模板内所有sheet内容
            HSSFSheet sheetPicture = wb.getSheetAt(2);
            HSSFPatriarch patriarch = sheetPicture.createDrawingPatriarch();
            HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0, 100, 50, (short) 1, 1, (short) 10, 20);
            ByteArrayOutputStream byteArrayOutputStream = createPicStream();
            if (byteArrayOutputStream != null) {
                //插入图片
                patriarch.createPicture(anchor, wb.addPicture(byteArrayOutputStream.toByteArray(), HSSFWorkbook.PICTURE_TYPE_JPEG));
            }

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

    private static ByteArrayOutputStream createPicStream() {
        try {
            //JFreeChart画图
            JFreeChart chart = ChartFactory.createPieChart("流量计费统计", getDataset(), true, false, false);
            chart.setTitle(new TextTitle("流量计费统计", new Font("仿宋", Font.BOLD, 20)));
            LegendTitle legend = chart.getLegend(0);
            legend.setItemFont(new Font("隶书", Font.TYPE1_FONT, 16));
            PiePlot plot = (PiePlot) chart.getPlot();
            plot.setLabelFont(new Font("宋体", Font.HANGING_BASELINE, 12));
            //设置分裂效果
//            plot.setExplodePercent("POS", 0.1f);
//            plot.setExplodePercent("OFF", 0.2f);

            //设置前景色透明度
            plot.setForegroundAlpha(0.7f);

            //设置标签生成器
            //{0}:名称
            //{1}:销量
            //{2}:百分比
            //{3}:总量
            //{4}:
            plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}({1}/{3}-{2})"));

            OutputStream os = new FileOutputStream(pathOfPicture);
            // 由ChartUtilities生成文件到一个体outputStream中去
            ChartUtilities.writeChartAsJPEG(os, chart, 500, 400);
            //处理图片文件，以便产生ByteArray
            ByteArrayOutputStream handlePicture = new ByteArrayOutputStream();
            handlePicture = handlePicture(pathOfPicture);
            return handlePicture;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static DefaultPieDataset getDataset() {
        DefaultPieDataset dpd = new DefaultPieDataset();
        dpd.setValue("POS", 25000);
        dpd.setValue("ON", 10000);
        dpd.setValue("CLS", 50000);
        dpd.setValue("OFF", 15000);
        return dpd;
    }

    private static ByteArrayOutputStream handlePicture(String pathOfPicture) {
        ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
        try {
            bufferImg = ImageIO.read(new File(pathOfPicture));
            ImageIO.write(bufferImg, "jpeg", byteArrayOut);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return byteArrayOut;
    }

    public static void main(String[] args) {
        createExcel();
    }
}

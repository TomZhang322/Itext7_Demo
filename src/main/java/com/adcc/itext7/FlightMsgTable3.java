package com.adcc.itext7;

import com.itextpdf.io.font.FontConstants;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.color.*;
import com.itextpdf.kernel.events.*;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.*;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.border.SolidBorder;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * 报文流量账单2
 */
public class FlightMsgTable3 {

    // 文件路径
    public static final String DEST2 = "C:\\Itext7_PDF\\FlightMsg3.pdf";
    // 饼图路径
    public static final String DESTPIE = "src/main/resources/jfreechar/msgRatePie.jpg";
    // 柱状图庐江
    public static final String DESTBAR = "src/main/resources/jfreechar/msgRateBar.jpg";

    public static void createTable(String dest) throws Exception {
        PdfDocument pdfDoc = new PdfDocument(new PdfWriter(dest));
        Document doc = new Document(pdfDoc);

        //添加水印
        pdfDoc.addEventHandler(PdfDocumentEvent.END_PAGE, new WatermarkingEventHandler());

        PdfFont sysFont = PdfFontFactory.createFont("STSongStd-Light", "UniGB-UCS2-H", false);

        //创建总表形式（一行5格）
        Table table = new Table(new float[] { 20, 20, 20, 20, 20 })
                .setWidthPercent(100);

        Cell cell = new Cell(1, 5)
                .add(new Paragraph("航班报文转发收费单"))
                .setFont(sysFont)
                .setFontSize(18)
                .setFontColor(DeviceGray.BLACK)
//                .setBackgroundColor(DeviceGray.GRAY)
                .setBorder(Border.NO_BORDER)
                .setTextAlignment(TextAlignment.CENTER);
        table.addHeaderCell(cell);

        Table table1 = new Table(2).setMarginTop(20).setWidthPercent(100);
        table1.addCell(new Cell().setBorder(Border.NO_BORDER).setFont(sysFont).add("用户:"));
        table1.addCell(new Cell().setBorder(Border.NO_BORDER).setFont(sysFont).add("CAN"));
        table1.addCell(new Cell().setBorder(Border.NO_BORDER).setFont(sysFont).add("开始时间:"));
        table1.addCell(new Cell().setBorder(Border.NO_BORDER).setFont(sysFont).add("2017-01-01"));
        table1.addCell(new Cell().setBorder(Border.NO_BORDER).setFont(sysFont).add("截止时间:"));
        table1.addCell(new Cell().setBorder(Border.NO_BORDER).setFont(sysFont).add("2017-09-30"));
        table1.addCell(new Cell().setBorder(Border.NO_BORDER).setFont(sysFont).add("总流量:"));
        table1.addCell(new Cell().setBorder(Border.NO_BORDER).setFont(sysFont).add("2000000"));
        table1.addCell(new Cell().setBorder(Border.NO_BORDER).setFont(sysFont).add("总费用:"));
        table1.addCell(new Cell().setBorder(Border.NO_BORDER).setFont(sysFont).add("5000"));

        // 添加季度流量柱状图
        Paragraph paragraphBarTitle = new Paragraph("航空报文季度流量分析柱状图：").setFont(sysFont).setMarginTop(25);
        // 本地生成柱状图
        createBar();
        // 柱状图嵌入PDF
        Table tabBar = new Table(new float[] { 6, 3 }).setWidthPercent(100);
        tabBar.setWidthPercent(80);
        tabBar.setHorizontalAlignment(HorizontalAlignment.CENTER);
        com.itextpdf.layout.element.Image ImgBar = new com.itextpdf.layout.element.Image(ImageDataFactory.create(DESTBAR));
        Cell cellimgBar = new Cell().add(ImgBar.setAutoScale(true))// 向第一个表格中添加图片
                .setBorder(new SolidBorder(new DeviceRgb(148, 0, 211), 3))
                .setBorder(Border.NO_BORDER);
        tabBar.addCell(cellimgBar);


        // 添加流量饼图
        Paragraph paragraphPieTitle = new Paragraph("航空报文流量分析饼图：").setFont(sysFont).setMarginTop(25);
        // 本地生成饼图
        createPie();
        // 饼图嵌入PDF
        Table tabPie = new Table(new float[] { 6, 3 }).setWidthPercent(100);
        tabPie.setWidthPercent(80);
        tabPie.setHorizontalAlignment(HorizontalAlignment.CENTER);
        com.itextpdf.layout.element.Image Img = new com.itextpdf.layout.element.Image(ImageDataFactory.create(DESTPIE));
        Cell cellimg = new Cell().add(Img.setAutoScale(true))// 向第一个表格中添加图片
                .setBorder(new SolidBorder(new DeviceRgb(148, 0, 211), 3))
                .setBorder(Border.NO_BORDER);
        tabPie.addCell(cellimg);


        // 添加明细
        Paragraph paragraph = new Paragraph("流量及费用明细：").setFont(sysFont).setMarginTop(25);

        //创建明细表格（一行3格）
        Table tableInfo = new Table(new float[] { 33, 33, 34 }).setWidthPercent(100).setMarginTop(10);

        //第二行定义列名
        Cell cellInfo=new Cell().add(new Paragraph("报文类型")).setFont(sysFont).setBackgroundColor(new DeviceGray(0.75f));
        tableInfo.addCell(cellInfo);
        tableInfo.addHeaderCell(cellInfo);
        cellInfo=new Cell().add(new Paragraph("报文数量")).setFont(sysFont).setBackgroundColor(new DeviceGray(0.75f));
        tableInfo.addCell(cellInfo);
        tableInfo.addHeaderCell(cellInfo);
        cellInfo=new Cell().add(new Paragraph("费用")).setFont(sysFont).setBackgroundColor(new DeviceGray(0.75f));
        tableInfo.addCell(cellInfo);
        tableInfo.addHeaderCell(cellInfo);

        cellInfo = new Cell(1, 3).add("Continue on next page");
        tableInfo.addFooterCell(cellInfo);

        for (int i = 0; i < 350; i++) {
            cellInfo=new Cell().add(new Paragraph("POS")).setFont(sysFont);
            tableInfo.addCell(cellInfo);
            cellInfo=new Cell().add(new Paragraph("150000")).setFont(sysFont);
            tableInfo.addCell(cellInfo);
            cellInfo=new Cell().add(new Paragraph("2000")).setFont(sysFont);
            tableInfo.addCell(cellInfo);
        }
        tableInfo.setSkipFirstHeader(true);
        tableInfo.setSkipLastFooter(true);

        doc.add(table);
        doc.add(table1);

        doc.add(paragraphBarTitle);
        doc.add(tabBar);

        doc.add(paragraphPieTitle);
        doc.add(tabPie);

        doc.add(paragraph);
        doc.add(tableInfo);
        doc.close();
    }

    /**
     * 生成水印
     */
    private static class WatermarkingEventHandler implements IEventHandler {
        @Override
        public void handleEvent(com.itextpdf.kernel.events.Event event) {
            try {
                PdfFont sysFont = PdfFontFactory.createFont("STSongStd-Light", "UniGB-UCS2-H", false);
                PdfDocumentEvent docEvent = (PdfDocumentEvent) event;
                PdfDocument pdfDoc = docEvent.getDocument();
                PdfPage page = docEvent.getPage();
                PdfFont font = null;
                try {
                    font = PdfFontFactory.createFont(FontConstants.HELVETICA_BOLD);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                PdfCanvas canvas = new PdfCanvas(page.newContentStreamBefore(), page.getResources(), pdfDoc);
                new com.itextpdf.layout.Canvas(canvas, pdfDoc, page.getPageSize())
                        .setFontColor(com.itextpdf.kernel.color.Color.LIGHT_GRAY)
                        .setFontSize(60)
                        .setFont(font)
                        .showTextAligned(new Paragraph("民航数据有限责任公司ADCC").setFont(sysFont).setFontSize(40), 298, 421, pdfDoc.getPageNumber(page),
                                TextAlignment.CENTER, VerticalAlignment.MIDDLE, 45);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 生成本地流量饼图
     */
    private static void createPie() {
        try {
            String title = "流量计费统计" ;
            DefaultPieDataset ds = new DefaultPieDataset();
            ds.setValue("POS", 2000);
            ds.setValue("CLS", 3500);
            ds.setValue("OFF", 2000);
            ds.setValue("ON", 3000);
            JFreeChart chart = ChartFactory.createPieChart3D(title, ds, true, false, false);

            //中文
            chart.getTitle().setFont(new Font("宋体", Font.BOLD, 15));//标题字体
            chart.getLegend().setItemFont(new Font("宋体", Font.PLAIN, 10));

            //绘图区
            PiePlot plot = (PiePlot) chart.getPlot();
            plot.setLabelFont(new Font("宋体", Font.PLAIN, 10));

            //设置分裂效果
            plot.setExplodePercent("POS", 0.1f);
            plot.setExplodePercent("OFF", 0.2f);

            //设置前景色透明度
            plot.setForegroundAlpha(0.7f);

            //设置标签生成器
            //{0}:名称
            //{1}:销量
            //{2}:百分比
            //{3}:总量
            //{4}:
            plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}({1}/{3}-{2})"));
            ChartUtilities.saveChartAsJPEG(new File(DESTPIE), chart, 500, 250);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 生成季度流量柱状图
     */
    private static void createBar() {
        try {
            DefaultCategoryDataset ds = new DefaultCategoryDataset();
            ds.addValue(200, "POS", "一季度");
            ds.addValue(230, "CLS", "一季度");
            ds.addValue(280, "OFF", "一季度");
            ds.addValue(330, "ON", "一季度");

            ds.addValue(480, "POS", "二季度");
            ds.addValue(430, "CLS", "二季度");
            ds.addValue(320, "OFF", "二季度");
            ds.addValue(180, "ON", "二季度");

            ds.addValue(150, "POS", "三季度");
            ds.addValue(260, "CLS", "三季度");
            ds.addValue(390, "OFF", "三季度");
            ds.addValue(210, "ON", "三季度");

            String title = "前三季度用户报文流量统计" ;
            JFreeChart chart = ChartFactory.createBarChart3D(title, "季度", "流量(单位:万份)", ds, PlotOrientation.VERTICAL, true, false, false);

            //中文
            chart.getTitle().setFont(new Font("宋体", Font.BOLD, 15));//大标题

            //提示条
            chart.getLegend().setItemFont(new Font("宋体", Font.BOLD, 10));

            CategoryPlot plot = (CategoryPlot) chart.getPlot();
            //域轴字体
            plot.getDomainAxis().setLabelFont(new Font("宋体", Font.BOLD, 12));
            plot.getDomainAxis().setTickLabelFont(new Font("宋体", Font.PLAIN, 10));//小标签字体

            //range
            plot.getRangeAxis().setLabelFont(new Font("宋体", Font.BOLD, 10));

            plot.setForegroundAlpha(0.6f);

            ChartUtilities.saveChartAsJPEG(new File(DESTBAR), chart, 600, 300);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        createTable(DEST2);
    }
}

package com.adcc.itext7;

import com.itextpdf.kernel.color.DeviceGray;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;

/**
 * 报文流量账单2
 */
public class FlightMsgTable2 {

    // 文件路径
    public static final String DEST2 = "C:\\Itext7_PDF\\FlightMsg2.pdf";

    public static void createTable(String dest) throws Exception {
        PdfDocument pdfDoc = new PdfDocument(new PdfWriter(dest));
        Document doc = new Document(pdfDoc, new PageSize(PageSize.A4).rotate());
        PdfFont sysFont = PdfFontFactory.createFont("STSongStd-Light", "UniGB-UCS2-H", false);

        //创建总表形式（一行5格）
        Table table = new Table(new float[] { 20, 20, 20, 20, 20 })
                .setWidthPercent(100);

        Cell cell = new Cell(1, 5)
                .add(new Paragraph("航班报文转发收费单"))
                .setFont(sysFont)
                .setFontSize(13)
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
        table1.addCell(new Cell().setBorder(Border.NO_BORDER).setFont(sysFont).add("2017-06-30"));
        table1.addCell(new Cell().setBorder(Border.NO_BORDER).setFont(sysFont).add("总流量:"));
        table1.addCell(new Cell().setBorder(Border.NO_BORDER).setFont(sysFont).add("2000000"));
        table1.addCell(new Cell().setBorder(Border.NO_BORDER).setFont(sysFont).add("总费用:"));
        table1.addCell(new Cell().setBorder(Border.NO_BORDER).setFont(sysFont).add("5000"));

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
        doc.add(paragraph);
        doc.add(tableInfo);
        doc.close();
    }

    public static void main(String[] args) throws Exception {
        createTable(DEST2);
    }
}

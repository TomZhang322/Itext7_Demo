package com.adcc.itext7.test;

import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.color.DeviceRgb;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.SolidBorder;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;

/**
 * Created by ZP on 2017/7/26.
 */
public class Test3 {
    public static final String DEST2 = "C:\\Itext7_PDF\\test6.pdf";//文件路径
    public static void test(String dest) throws Exception{
        PdfDocument pdfDoc = new PdfDocument(new PdfWriter(dest));
        Document doc = new Document(pdfDoc);
        PdfFont sysFont = PdfFontFactory.createFont("STSongStd-Light", "UniGB-UCS2-H", false);
        String image = "src/main/resources/img/ocean.png";// 图片路径
        Table tab = new Table(new float[] { 6, 3 });
        tab.setWidthPercent(80);
        tab.setHorizontalAlignment(HorizontalAlignment.CENTER);
        Image Img = new Image(ImageDataFactory.create(image));
        Cell cellimg = new Cell().add(Img.setAutoScale(true))// 向第一个表格中添加图片
                .setBorder(new SolidBorder(new DeviceRgb(148, 0, 211), 3));
        //.setBorder(Border.NO_BORDER);
        tab.addCell(cellimg);
        Cell cell = new Cell();
        // 文字样式
        Text text1 = new Text("人类的海洋").setFont(sysFont)
                .setFontSize((float) 7.41)
                .setFontColor(new DeviceRgb(46, 46, 46)).setBold();// setBold()字体为加粗
        Text text2 = new Text("保护地球是我们共同的责任,让我们一起努力，让我们的地球更美好")
                .setFont(sysFont).setFontSize((float) 7.41)
                .setFontColor(new DeviceRgb(46, 46, 46));

        cell.setTextAlignment(TextAlignment.LEFT)
                // 字体居左
                .add(new Paragraph().add(text1).add("\n").add(text2)
                        .setFixedLeading(15))// setFixedLeading为设置行间距
                .setBorder(new SolidBorder(new DeviceRgb(139, 0, 139), 3))//边界颜色，边界宽度
                .setBackgroundColor(new DeviceRgb(244, 248, 250));

        tab.addCell(cell);
        doc.add(tab.setHorizontalAlignment(HorizontalAlignment.LEFT));// 将表格添加入文档并页面居中
        doc.close();
    }
    public static void main(String[] args) throws Exception {
        test(DEST2);
    }
}

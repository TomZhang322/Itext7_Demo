package com.adcc.itext7.test;

import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.UnitValue;

/**
 * Created by ZP on 2017/7/26.
 */
public class Test {
    public static final String DEST2 = "C:\\Itext7_PDF\\test4.pdf";//文件路径
    public static void test(String dest) throws Exception{
        PdfDocument pdfDoc = new PdfDocument(new PdfWriter(dest));
        Document doc = new Document(pdfDoc);//构建文档对象
        PdfFont sysFont = PdfFontFactory.createFont("STSongStd-Light", "UniGB-UCS2-H", false);//中文字体
        Table table = new Table(new float[]{2,4,4}).setWidth(UnitValue.createPercentValue(100));//构建表格以100%的宽度
        Cell cell1=new Cell().add(new Paragraph("表格1")).setFont(sysFont);//向表格添加内容
        Cell cell2=new Cell().add(new Paragraph("表格2")).setFont(sysFont);
        Cell cell3=new Cell().add(new Paragraph("表格3")).setFont(sysFont);
        table.addCell(cell1);
        table.addCell(cell2);
        table.addCell(cell3);
        doc.add(table.setHorizontalAlignment(HorizontalAlignment.CENTER));//将表格添加入文档并页面居中
        doc.close();
    }
    public static void main(String[] args) throws Exception {
        test(DEST2);
    }
}

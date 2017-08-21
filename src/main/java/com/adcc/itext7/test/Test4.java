package com.adcc.itext7.test;

import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;

/**
 * Created by ZP on 2017/7/26.
 */
public class Test4 {
//    private static final String PICPersonF = "E:\\project\\BMIs1.png";

    public static final String DEST2 = "C:\\Itext7_PDF\\test7.pdf";// 文件路径

    public static void test(String dest) throws Exception {
        PdfDocument pdfDoc = new PdfDocument(new PdfWriter(dest));
        Document doc = new Document(pdfDoc);
        PdfFont sysFont = PdfFontFactory.createFont("STSongStd-Light", "UniGB-UCS2-H", false);
        //创建总表形式（一行四格）
        Table table = new Table(new float[] { 55, 13, 14, 13 })
                .setWidthPercent(95);
        //表头
        for(int i=0;i<4;i++){
            table.addCell(new Cell().add(new Paragraph(""+(i+1))));
        }
        //表格行合并"2"代表合并2行单元格
        Cell cell=new Cell(2,1).add(new Paragraph("one"));
        table.addCell(cell);
        //表格列合并"3"代表合并3列
        cell=new Cell(1,3).add(new Paragraph("two"));
        table.addCell(cell);
        //将剩余格补齐
        cell=new Cell().add(new Paragraph("three"));
        table.addCell(cell);
        cell=new Cell().add(new Paragraph("three"));
        table.addCell(cell);
        cell=new Cell().add(new Paragraph("three"));
        table.addCell(cell);
        doc.add(table);
        doc.close();
    }
    public static void main(String[] args) throws Exception {
        test(DEST2);
    }
}

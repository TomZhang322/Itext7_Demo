package com.adcc.itext7.test;

import com.itextpdf.kernel.color.DeviceRgb;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.border.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;

/**
 * Created by ZP on 2017/7/26.
 */
public class Test2 {
    public static final String DEST2 = "C:\\Itext7_PDF\\test5.pdf";//文件路径
    public static void test(String dest) throws Exception{
        PdfDocument pdfDoc = new PdfDocument(new PdfWriter(dest));
        Document doc = new Document(pdfDoc);
        PdfFont sysFont = PdfFontFactory.createFont("STSongStd-Light", "UniGB-UCS2-H", false);
        Table table = new Table(new float[]{2,4,4}).setWidth(UnitValue.createPercentValue(70));
        Cell cell1=new Cell().add(new Paragraph("表格1")).setFont(sysFont).setBackgroundColor(new DeviceRgb(221, 234, 238));
        Cell cell2=new Cell().add(new Paragraph("表格2")).setFont(sysFont).setFontSize(10).setTextAlignment(TextAlignment.CENTER);
        Cell cell3=new Cell().add(new Paragraph("表格3")).setFont(sysFont).setBorder(new SolidBorder(new DeviceRgb(221, 234, 238), 1));
        Cell cell4=new Cell().add(new Paragraph("表格4")).setFont(sysFont).setBorder(Border.NO_BORDER);
        table.addCell(cell1);
        table.addCell(cell2);
        table.addCell(cell3);
        table.addCell(cell4);
        doc.add(table);
        doc.close();
    }
    public static void main(String[] args) throws Exception {
        test(DEST2);
    }
}

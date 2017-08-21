package com.adcc.itext7.tables;

import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.AreaBreak;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;

import java.io.File;

/**
 * Created by ZP on 2017/7/27.
 */
public class SplittingNestedTable1 {
    public static final String DEST = "C:/Itext7_PDF/tableExampls/splitting_nested_table1.pdf";

    public static void main(String[] args) throws Exception {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        manipulatePdf(DEST);
    }

    protected static void manipulatePdf(String dest) throws Exception {
        PdfDocument pdfDoc = new PdfDocument(new PdfWriter(dest));
        Document doc = new Document(pdfDoc, new PageSize(300, 180));

        doc.add(new Paragraph("Table with setKeepTogether(false):"));
        Table table = new Table(2);
        table.setMarginTop(10);
        Cell cell = new Cell();
        cell.add("G");
        cell.add("R");
        cell.add("O");
        cell.add("U");
        cell.add("P");
        table.addCell(cell);
        Table inner = new Table(1);
        inner.addCell("row 1");
        inner.addCell("row 2");
        inner.addCell("row 3");
        inner.addCell("row 4");
        inner.addCell("row 5");
        cell = new Cell().add(inner);
        cell.setPadding(0);
        table.addCell(cell);
        doc.add(table);
        doc.add(new AreaBreak());

        doc.add(new Paragraph("Table with setKeepTogether(true):"));
        table.setKeepTogether(true);
        doc.add(table);

        doc.close();
    }
}

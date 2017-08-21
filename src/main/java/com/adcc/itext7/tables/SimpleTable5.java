package com.adcc.itext7.tables;

import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Table;

import java.io.File;

/**
 * Created by ZP on 2017/7/27.
 */
public class SimpleTable5 {
    public static final String DEST = "C:/Itext7_PDF/tableExampls/simple_table5.pdf";

    public static void main(String[] args) throws Exception {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        manipulatePdf(DEST);
    }

    protected static void manipulatePdf(String dest) throws Exception {
        PdfDocument pdfDoc = new PdfDocument(new PdfWriter(dest));
        // Note that it is not necessary to create new PageSize object,
        // but for testing reasons (connected to parallelization) we call constructor here
        Document doc = new Document(pdfDoc, new PageSize(PageSize.A4).rotate());

        Table table = new Table(5);
        Cell cell = new Cell(1, 5).add("Table XYZ (Continued)");
        table.addHeaderCell(cell);
        cell = new Cell(1, 5).add("Continue on next page");
        table.addFooterCell(cell);
        table.setSkipFirstHeader(true);
        table.setSkipLastFooter(true);
        for (int i = 0; i < 350; i++) {
            table.addCell(String.valueOf(i+1));
        }

        doc.add(table);

        doc.close();
    }
}

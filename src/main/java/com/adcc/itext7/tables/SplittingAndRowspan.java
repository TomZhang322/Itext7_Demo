package com.adcc.itext7.tables;

import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;

import java.io.File;
import java.io.IOException;


/**
 * Created by ZP on 2017/7/27.
 */
public class SplittingAndRowspan {
    public static final String DEST = "C:/Itext7_PDF/tableExampls/splitting_and_rowspan.pdf";

    public static void main(String[] args) throws IOException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        manipulatePdf(DEST);
    }

    protected static void manipulatePdf(String dest) throws IOException {
        PdfDocument pdfDoc = new PdfDocument(new PdfWriter(dest));
        Document doc = new Document(pdfDoc, new PageSize(300, 180));

        doc.add(new Paragraph("Table with setKeepTogether(false):"));
        Table table = new Table(2);
        table.setKeepTogether(true);
        table.setMarginTop(10);
        Cell cell = new Cell(3, 1);
        cell.add("G");
        cell.add("R");
        cell.add("P");
        table.addCell(cell);
        table.addCell("row 1");
        table.addCell("row 2");
        table.addCell("row 3");
        doc.add(table);

        doc.add(new Paragraph("Table with setKeepTogether(true):"));
        table = new Table(2);
        // table.setKeepTogether(false); // by default
        table.setMarginTop(10);
        cell = new Cell(3, 1);
        cell.add(new Paragraph("G").setFontColor(Color.BLUE));
        cell.add(new Paragraph("R").setFontColor(Color.BLUE));
        cell.add(new Paragraph("P").setFontColor(Color.BLUE));
        table.addCell(cell);
        table.addCell("row 1");
        table.addCell("row 2");
        table.addCell("row 3");
        doc.add(table);

        doc.add(new Paragraph("Table with setKeepTogether(true):"));
        table = new Table(2);
        table.setKeepTogether(true);
        table.setMarginTop(10);
        cell = new Cell(3, 1);
        cell.add(new Paragraph("G").setFontColor(Color.RED));
        cell.add(new Paragraph("R").setFontColor(Color.RED));
        cell.add(new Paragraph("P").setFontColor(Color.RED));
        table.addCell(cell);
        table.addCell("row 1");
        table.addCell("row 2");
        table.addCell("row 3");
        doc.add(table);

        doc.close();
    }
}

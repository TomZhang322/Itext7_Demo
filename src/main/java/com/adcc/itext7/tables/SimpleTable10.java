package com.adcc.itext7.tables;

import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Table;

import java.io.File;

/**
 * Created by ZP on 2017/7/26.
 */
public class SimpleTable10 {
    public static final String DEST = "C:/Itext7_PDF/tableExampls/simple_table10.pdf";

    public static void main(String[] args) throws Exception {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        manipulatePdf(DEST);
    }

    protected static void manipulatePdf(String dest) throws Exception {
        PdfDocument pdfDoc = new PdfDocument(new PdfWriter(dest));
        Document doc = new Document(pdfDoc);

        Table table = new Table(5);
        Cell sn = new Cell(2, 1).add("S/N");
        sn.setBackgroundColor(Color.YELLOW);
        table.addCell(sn);
        Cell name = new Cell(1, 3).add("Name");
        name.setBackgroundColor(Color.CYAN);
        table.addCell(name);
        Cell age = new Cell(2, 1).add("Age");
        age.setBackgroundColor(Color.GRAY);
        table.addCell(age);
        Cell surname = new Cell().add("SURNAME");
        surname.setBackgroundColor(Color.BLUE);
        table.addCell(surname);
        Cell firstname = new Cell().add("FIRST NAME");
        firstname.setBackgroundColor(Color.RED);
        table.addCell(firstname);
        Cell middlename = new Cell().add("MIDDLE NAME");
        middlename.setBackgroundColor(Color.GREEN);
        table.addCell(middlename);
        Cell f1 = new Cell().add("1");
        f1.setBackgroundColor(Color.PINK);
        table.addCell(f1);
        Cell f2 = new Cell().add("James");
        f2.setBackgroundColor(Color.MAGENTA);
        table.addCell(f2);
        Cell f3 = new Cell().add("Fish");
        f3.setBackgroundColor(Color.ORANGE);
        table.addCell(f3);
        Cell f4 = new Cell().add("Stone");
        f4.setBackgroundColor(Color.DARK_GRAY);
        table.addCell(f4);
        Cell f5 = new Cell().add("17");
        f5.setBackgroundColor(Color.LIGHT_GRAY);
        table.addCell(f5);
        doc.add(table);

        doc.close();
    }
}

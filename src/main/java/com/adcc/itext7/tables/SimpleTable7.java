package com.adcc.itext7.tables;

import com.itextpdf.io.font.FontConstants;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.UnitValue;

import java.io.File;

/**
 * Created by ZP on 2017/7/27.
 */
public class SimpleTable7 {
    public static final String DEST = "C:/Itext7_PDF/tableExampls/simple_table7.pdf";

    public static void main(String[] args) throws Exception {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        manipulatePdf(DEST);
    }

    protected static void manipulatePdf(String dest) throws Exception {
        PdfDocument pdfDoc = new PdfDocument(new PdfWriter(dest));
        Document doc = new Document(pdfDoc);

        PdfFont titleFont = PdfFontFactory.createFont(FontConstants.COURIER_BOLD);
        Paragraph docTitle = new Paragraph("UCSC Direct - Direct Payment Form").setMarginRight(1);
        docTitle.setFont(titleFont).setFontSize(11);
        doc.add(docTitle);

        PdfFont subtitleFont = PdfFontFactory.createFont(FontConstants.TIMES_ROMAN);
        Paragraph subTitle = new Paragraph("(not to be used for reimbursement of services)");
        subTitle.setFont(subtitleFont).setFontSize(9);
        doc.add(subTitle);

        PdfFont importantNoticeFont = PdfFontFactory.createFont(FontConstants.COURIER);
        Paragraph importantNotice = new Paragraph("Important: Form must be filled out in Adobe Reader or Acrobat Professional 8.1 or above. To save completed forms, Acrobat Professional is required. For technical and accessibility assistance, contact the Campus Controller's Office.");
        importantNotice.setFont(importantNoticeFont).setFontSize(9);
        importantNotice.setFontColor(Color.RED);
        doc.add(importantNotice);

        Table table = new Table(10).
                setWidth(UnitValue.createPercentValue(80));
        Cell cell = new Cell(1, 3).add(docTitle);
        cell.setBorder(Border.NO_BORDER);
        cell.setHorizontalAlignment(HorizontalAlignment.LEFT);
        table.addCell(cell);

        Cell cellCaveat = new Cell(1, 2).add(subTitle);
        cellCaveat.setBorder(null);
        table.addCell(cellCaveat);

        Cell cellImportantNote = new Cell(1, 5).add(importantNotice);
        cellImportantNote.setBorder(Border.NO_BORDER);
        table.addCell(cellImportantNote);

        doc.add(table.setHorizontalAlignment(HorizontalAlignment.CENTER));
        doc.add(new Paragraph("This is the same table, created differently").
                setFont(subtitleFont).setFontSize(9).setMarginBottom(10));

        table = new Table(new float[]{3, 2, 5}).
                setWidth(UnitValue.createPercentValue(80));
        table.addCell(new Cell().add(docTitle).setBorder(Border.NO_BORDER));
        table.addCell(new Cell().add(subTitle).setBorder(Border.NO_BORDER));
        table.addCell(new Cell().add(importantNotice).setBorder(Border.NO_BORDER));
        doc.add(table.setHorizontalAlignment(HorizontalAlignment.CENTER));

        doc.close();
    }
}

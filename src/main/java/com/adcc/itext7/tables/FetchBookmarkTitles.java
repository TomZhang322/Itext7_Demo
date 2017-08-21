package com.adcc.itext7.tables;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfOutline;
import com.itextpdf.kernel.pdf.PdfReader;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class FetchBookmarkTitles {
    public static final String SRC = "C:/Itext7_PDF/FlightMsg2.pdf";
    public static final String RESULT = "2011-10-12\n" +
            "2011-10-13\n" +
            "2011-10-14\n" +
            "2011-10-15\n" +
            "2011-10-16\n" +
            "2011-10-17\n" +
            "2011-10-18\n" +
            "2011-10-19\n";

    @Test
    public void manipulatePdf() throws IOException {
        PdfDocument pdfDoc = new PdfDocument(new PdfReader(SRC));

        PdfOutline outlines = pdfDoc.getOutlines(false);
        List<PdfOutline> bookmarks = outlines.getAllChildren().get(0).getAllChildren();
        StringBuffer stringBuffer = new StringBuffer();
        for (PdfOutline bookmark : bookmarks) {
            showTitle(bookmark, stringBuffer);
        }
        pdfDoc.close();

        System.out.println(stringBuffer.toString());

        Assert.assertArrayEquals(RESULT.getBytes(), stringBuffer.toString().getBytes());
    }

    public void showTitle(PdfOutline outline, StringBuffer stringBuffer) {
        System.out.println(outline.getTitle());
        stringBuffer.append(outline.getTitle() + "\n");
        List<PdfOutline> kids = outline.getAllChildren();
        if (kids != null) {
            for (PdfOutline kid : kids) {
                showTitle(kid, stringBuffer);
            }
        }
    }
}

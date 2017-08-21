package com.adcc.itext7.tables;

import com.itextpdf.io.font.FontConstants;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.color.DeviceGray;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.renderer.CellRenderer;
import com.itextpdf.layout.renderer.DrawContext;

import java.io.File;

/**
 * Created by ZP on 2017/7/26.
 */
public class ImageBackground {
    public static final String DEST = "C:/Itext7_PDF/tableExampls/image_background.pdf";
    public static final String IMG = "src/main/resources/img/bruno.jpg";

    public static void main(String[] args) throws Exception {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        manipulatePdf(DEST);
    }

    protected static void manipulatePdf(String dest) throws Exception {
        PdfDocument pdfDoc = new PdfDocument(new PdfWriter(dest));
        Document doc = new Document(pdfDoc);

        Table table = new Table(1);
        table.setWidth(400);
        Cell cell = new Cell();
        PdfFont font = PdfFontFactory.createFont(FontConstants.HELVETICA);
        Paragraph p = new Paragraph("A cell with an image as background color.")
                .setFont(font)
                .setFontColor(DeviceGray.WHITE);
        cell.add(p);
        Image img = new Image(ImageDataFactory.create(IMG));
        cell.setNextRenderer(new ImageBackgroundCellRenderer(cell, img));
        cell.setHeight(600 * img.getImageHeight() / img.getImageWidth());
        table.addCell(cell);
        doc.add(table);

        doc.close();
    }


    private static class ImageBackgroundCellRenderer extends CellRenderer {
        protected static Image img;

        public ImageBackgroundCellRenderer(Cell modelElement, Image img1) {
            super(modelElement);
            img = img1;
        }

        @Override
        public void draw(DrawContext drawContext) {
            img.scaleToFit(getOccupiedAreaBBox().getWidth(), getOccupiedAreaBBox().getHeight());
            drawContext.getCanvas().addXObject(img.getXObject(), getOccupiedAreaBBox());
            super.draw(drawContext);
        }
    }
}

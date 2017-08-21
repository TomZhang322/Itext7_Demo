package com.adcc.itext7.test;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.layout.LayoutArea;
import com.itextpdf.layout.layout.LayoutResult;
import com.itextpdf.layout.renderer.DocumentRenderer;

/**
 * Created by ZP on 2017/7/26.
 */
public class TEST5 {
    public static final String DEST2 = "C:\\Itext7_PDF\\test8.pdf";// 文件路径
    protected static void manipulatePdf(String dest) throws Exception {
        PdfDocument pdfDoc = new PdfDocument(new PdfWriter(dest));
        Document doc = new Document(pdfDoc);

//        PdfAcroForm form = PdfAcroForm.getAcroForm(pdfDoc, true);
//        Map<String, PdfFormField> fields = form.getFormFields();
//        fields.get("Name").setValue("Jeniffer");
//        fields.get("Company").setValue("iText's next customer");
//        fields.get("Country").setValue("No Man's Land");
//        form.flattenFields();

        Table table = new Table(new float[]{1, 15});
        table.setWidthPercent(80);
        table.addHeaderCell("#");
        table.addHeaderCell("description");
        for (int i = 1; i <= 150; i++) {
            table.addCell(String.valueOf(i));
            table.addCell("test " + i);
        }

        doc.setRenderer(new DocumentRenderer(doc) {
            @Override
            protected LayoutArea updateCurrentArea(LayoutResult overflowResult) {
                LayoutArea area = super.updateCurrentArea(overflowResult);
                if (area.getPageNumber() == 1) {
                    area.getBBox().decreaseHeight(266);
                }
                return area;
            }
        });

        doc.add(table);

        doc.close();
    }
    public static void main(String[] args) throws Exception {
        manipulatePdf(DEST2);
    }
}

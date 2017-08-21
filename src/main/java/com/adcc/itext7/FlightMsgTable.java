package com.adcc.itext7;

import com.itextpdf.io.font.FontConstants;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.color.DeviceGray;
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

/**
 * 报文流量账单1
 */
public class FlightMsgTable {

    // 文件路径
    public static final String DEST2 = "C:\\Itext7_PDF\\FlightMsg.pdf";

    public static void createTable(String dest) throws Exception {
        PdfDocument pdfDoc = new PdfDocument(new PdfWriter(dest));
        Document doc = new Document(pdfDoc);
        PdfFont sysFont = PdfFontFactory.createFont("STSongStd-Light", "UniGB-UCS2-H", false);

//        PdfFont titleFont = PdfFontFactory.createFont(FontConstants.COURIER_BOLD);
//        Paragraph docTitle = new Paragraph("航班报文转发收费单").setMarginRight(1);
//        docTitle.setFont(titleFont).setFontSize(11);
//        doc.add(docTitle);

        //创建总表形式（一行5格）
        Table table = new Table(new float[] { 20, 20, 20, 20, 20 })
                .setWidthPercent(100);

        Cell cell = new Cell(1, 5)
                .add(new Paragraph("航班报文转发收费单"))
                .setFont(sysFont)
                .setFontSize(13)
                .setFontColor(DeviceGray.BLACK)
//                .setBackgroundColor(DeviceGray.GRAY)
                .setBorder(Border.NO_BORDER)
                .setTextAlignment(TextAlignment.CENTER);
        table.addHeaderCell(cell);

        //表头合并5列
        cell=new Cell(1,5).add(new Paragraph("账单名：XXX")).setFont(sysFont).setBorder(Border.NO_BORDER);
        table.addCell(cell);
        //第二行定义列名
        cell=new Cell().add(new Paragraph("用户")).setFont(sysFont).setBackgroundColor(new DeviceGray(0.75f));
        table.addCell(cell);
        cell=new Cell().add(new Paragraph("开始时间")).setFont(sysFont).setBackgroundColor(new DeviceGray(0.75f));
        table.addCell(cell);
        cell=new Cell().add(new Paragraph("截止时间")).setFont(sysFont).setBackgroundColor(new DeviceGray(0.75f));
        table.addCell(cell);
        cell=new Cell().add(new Paragraph("总流量")).setFont(sysFont).setBackgroundColor(new DeviceGray(0.75f));
        table.addCell(cell);
        cell=new Cell().add(new Paragraph("总费用")).setFont(sysFont).setBackgroundColor(new DeviceGray(0.75f));
        table.addCell(cell);
        //第三行填写具体内容
        cell=new Cell().add(new Paragraph("CAN")).setFont(sysFont);
        table.addCell(cell);
        cell=new Cell().add(new Paragraph("2017-01-01")).setFont(sysFont);
        table.addCell(cell);
        cell=new Cell().add(new Paragraph("2017-06-30")).setFont(sysFont);
        table.addCell(cell);
        cell=new Cell().add(new Paragraph("2000000")).setFont(sysFont);
        table.addCell(cell);
        cell=new Cell().add(new Paragraph("5000")).setFont(sysFont);
        table.addCell(cell);

        //创建明细表格（一行3格）
        Table tableInfo = new Table(new float[] { 33, 33, 34 })
                .setWidthPercent(100);
        //表头合并3列
        Cell cellInfo=new Cell(1,3).add(new Paragraph("流量及费用明细：")).setFont(sysFont).setBorder(Border.NO_BORDER);
        tableInfo.addCell(cellInfo);
        //第二行定义列名
        cellInfo=new Cell().add(new Paragraph("报文类型")).setFont(sysFont).setBackgroundColor(new DeviceGray(0.75f));
        tableInfo.addCell(cellInfo);
        cellInfo=new Cell().add(new Paragraph("报文数量")).setFont(sysFont).setBackgroundColor(new DeviceGray(0.75f));
        tableInfo.addCell(cellInfo);
        cellInfo=new Cell().add(new Paragraph("费用")).setFont(sysFont).setBackgroundColor(new DeviceGray(0.75f));
        tableInfo.addCell(cellInfo);
        //第三行填写具体内容
        cellInfo=new Cell().add(new Paragraph("POS")).setFont(sysFont);
        tableInfo.addCell(cellInfo);
        cellInfo=new Cell().add(new Paragraph("150000")).setFont(sysFont);
        tableInfo.addCell(cellInfo);
        cellInfo=new Cell().add(new Paragraph("2000")).setFont(sysFont);
        tableInfo.addCell(cellInfo);

        cellInfo=new Cell().add(new Paragraph("CLS")).setFont(sysFont).setFontColor(Color.BLUE);
        tableInfo.addCell(cellInfo);
        cellInfo=new Cell().add(new Paragraph("100000")).setFont(sysFont).setFontColor(Color.BLUE);
        tableInfo.addCell(cellInfo);
        cellInfo=new Cell().add(new Paragraph("1000")).setFont(sysFont).setFontColor(Color.BLUE);
        tableInfo.addCell(cellInfo);

        doc.add(table);
        doc.add(tableInfo);
        doc.close();
    }

    /**
     *
     * @param content
     * @param borderWidth
     * @param colspan
     * @param alignment 如：TextAlignment.LEFT
     * @return
     */
    public static Cell createCell(String content, float borderWidth, int colspan, TextAlignment alignment) {
        Cell cell = new Cell(1, colspan).add(new Paragraph(content));
        cell.setTextAlignment(alignment);
        cell.setBorder(new SolidBorder(borderWidth));
        return cell;
    }

    public static void main(String[] args) throws Exception {
        createTable(DEST2);
    }
}

//package com.adcc.itext7.test;
//
//import com.itextpdf.kernel.color.Color;
//import com.itextpdf.kernel.events.PdfDocumentEvent;
//import com.itextpdf.kernel.font.PdfFont;
//import com.itextpdf.kernel.font.PdfFontFactory;
//import com.itextpdf.kernel.pdf.PdfDocument;
//import com.itextpdf.kernel.pdf.PdfWriter;
//import com.itextpdf.layout.Document;
//import com.itextpdf.layout.border.SolidBorder;
//import com.itextpdf.layout.element.Cell;
//import com.itextpdf.layout.element.Paragraph;
//import com.itextpdf.layout.element.Table;
//import com.itextpdf.layout.property.TextAlignment;
//
//import javax.servlet.http.HttpServletResponse;
//import java.text.DecimalFormat;
//import java.util.List;
//
///**
// * Created by ZP on 2017/7/26.
// */
//public class ExportReportPdfUtil {
//
//    static PdfFont helvetica = null;
//    static PdfFont helveticaBold = null;
//    static DecimalFormat df = new DecimalFormat("#0.00");
//
//    /**
//     * @Title: exportPdf
//     * @Description: 生成pdf，提示下载
//     * @param fileName 标婷
//     * @param spreadhead
//     * @param subhead
//     * @param tables
//     * @param response
//     */
//    public static void exportPdf(String fileName,String spreadhead, String subhead, List<Table> tables, HttpServletResponse response){
//        try{
//            // 设置response参数，可以打开下载页面
//            response.reset();
//            response.setContentType("application/pdf;charset=utf-8");
//            response.addHeader("Content-Disposition","attachment;filename="+ new String( fileName.getBytes("gb2312"), "ISO8859-1"));
//            //处理中文问题
//            helvetica = PdfFontFactory.createFont("STSongStd-Light", "UniGB-UCS2-H", false);
//            helveticaBold = PdfFontFactory.createFont("STSongStd-Light", "UniGB-UCS2-H", false);
//            PdfWriter writer = new PdfWriter(response.getOutputStream());
//            //Initialize PDF document
//            PdfDocument pdf = new PdfDocument(writer);
//            pdf.addEventHandler(PdfDocumentEvent.END_PAGE, new ReportEventHandler());
//            // Initialize document
//            Document document = new Document(pdf);
//            //加载报告内容
//            loadReport(spreadhead, subhead, tables, document);
//            document.close();
//            writer.close();
//            pdf.close();
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//
//    }
//
//    public static void loadReport(String spreadhead, String subhead, List<Table> tables, Document document){
//        //大标题
//        Paragraph p = new Paragraph(spreadhead).setTextAlignment(TextAlignment.CENTER).setFont(helveticaBold).setFontSize(14);
//        document.add(p);
//        //副标题
//        document.add(new Paragraph(subhead).setFont(helvetica).setFontSize(8));
//        //初始化详情内容
//        for (Table pdfTable : tables) {
//            loadTitle(pdfTable.getTitle() , document);
//            Table table = new Table(pdfTable.getNumColumns());
//            table.setWidthPercent(pdfTable.getWidthPercent());
//            List<Column> columns = pdfTable.getColumns();
//            for (PDFColumn pdfColumn : columns) {
//                if(pdfColumn.isBold){
//                    process(table, pdfColumn.getText() , helveticaBold, pdfColumn.isHeader());
//                }else{
//                    process(table, pdfColumn.getText() , helvetica, pdfColumn.isHeader());
//                }
//            }
//            document.add(table);
//        }
//
//    }
//
//    /***
//     * @Title: title
//     * @Description: 添加标题
//     * @param text
//     * @param document
//     */
//    public static void loadTitle(String text, Document document){
//        document.add(new Paragraph(text).setFont(helvetica).setBold().setFontSize(12));
//    }
//
//    /**
//     * @Title: process
//     * @Description: 处理table
//     * @param table
//     * @param text
//     * @param font
//     * @param isHeader
//     */
//    public static void process(Table table, String text, PdfFont font, boolean isHeader) {
//        if (isHeader) {
//            table.addHeaderCell(new Cell().add(new Paragraph(text).setFont(font)).setFontSize(9).setBorder(new SolidBorder(Color.BLACK, 0.5f)).setBold());
//        } else {
//            table.addCell(new Cell().add(new Paragraph(text).setFont(font)).setFontSize(9).setBorder(new SolidBorder(Color.BLACK, 0.5f)));
//        }
//    }
//}

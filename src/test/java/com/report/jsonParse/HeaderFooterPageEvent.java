package com.report.jsonParse;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import java.io.IOException;


public class HeaderFooterPageEvent extends PdfPageEventHelper {

    private static PdfTemplate template;
    private static Image total;

    public void onOpenDocument(PdfWriter writer, Document document) {
        template = writer.getDirectContent().createTemplate(30, 16);
        try {
            total = Image.getInstance(template);
            total.setRole(PdfName.ARTIFACT);
        } catch (DocumentException de) {
            throw new ExceptionConverter(de);
        }
    }

    @Override
    public void onEndPage(PdfWriter writer, Document document) {
        addHeader(writer);
        addFooter(writer);
    }

    public static void addHeader(PdfWriter writer){
        PdfPTable header = new PdfPTable(2);
        try {
            header.setWidths(new int[]{4, 15});
            header.setTotalWidth(527);
            header.setLockedWidth(true);
            header.getDefaultCell().setFixedHeight(40);
            header.getDefaultCell().setBorder(Rectangle.BOTTOM);
            header.getDefaultCell().setBorderColor(BaseColor.LIGHT_GRAY);

            Image logo = Image.getInstance("/Users/values/IdeaProjects/ReportDeneme/logo.png");
            header.addCell(logo);

            PdfPCell text = new PdfPCell();
            text.setPaddingBottom(15);
            text.setPaddingLeft(10);
            text.setBorder(Rectangle.BOTTOM);
            text.setBorderColor(BaseColor.LIGHT_GRAY);
            text.addElement(new Phrase("Virgosol Bilisim ve Yazilim Cozumleri", new Font(Font.FontFamily.HELVETICA, 12)));
            text.addElement(new Phrase("https://virgosol.com/", new Font(Font.FontFamily.HELVETICA, 8)));
            header.addCell(text);

            header.writeSelectedRows(0, -1, 35, 803, writer.getDirectContent());
        } catch(DocumentException | IOException de) {
            throw new ExceptionConverter(de);
        }
    }

    public static void addFooter(PdfWriter writer){

    PdfPTable footer = new PdfPTable(3);

        try {
            // set defaults
            footer.setWidths(new float[]{24,2,1});
            footer.setTotalWidth(527);
            footer.setLockedWidth(true);
            footer.getDefaultCell().setFixedHeight(40);
            footer.getDefaultCell().setBorder(Rectangle.TOP);
            footer.getDefaultCell().setBorderColor(BaseColor.DARK_GRAY);

            // add copyright
            footer.addCell(new Phrase("\u00A9 VIRGOSOL", new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD)));

            // add current page count
            footer.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
            footer.addCell(new Phrase(String.format("Page %d of", writer.getPageNumber()), new Font(Font.FontFamily.HELVETICA, 8)));

            // add placeholder for total page count

            PdfPCell totalPageCount = new PdfPCell();
            totalPageCount.setBorder(Rectangle.TOP);
            totalPageCount.setBorderColor(BaseColor.LIGHT_GRAY);
            totalPageCount.setHorizontalAlignment(Element.ALIGN_RIGHT);
            footer.addCell(new Phrase(String.format(String.valueOf(writer.getPageNumber()), writer.getPageNumber()), new Font(Font.FontFamily.HELVETICA, 8)));

            // write page
            PdfContentByte canvas = writer.getDirectContent();
            canvas.beginMarkedContentSequence(PdfName.ARTIFACT);
            footer.writeSelectedRows(0, -1, 34, 50, canvas);
            canvas.endMarkedContentSequence();
        } catch(DocumentException de) {
            throw new ExceptionConverter(de);
        }
    }

    public void onCloseDocument(PdfWriter writer, Document document) {
        int totalLength = String.valueOf(writer.getPageNumber()).length();
        int totalWidth = totalLength * 5;
        ColumnText.showTextAligned(template, Element.ALIGN_RIGHT,
                new Phrase(String.valueOf(writer.getPageNumber()), new Font(Font.FontFamily.HELVETICA, 8)),
                totalWidth, 6, 0);
    }
}

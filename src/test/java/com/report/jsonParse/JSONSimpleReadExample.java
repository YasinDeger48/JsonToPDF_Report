package com.report.jsonParse;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itextpdf.awt.DefaultFontMapper;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.report.model.*;
import com.report.model.Element;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;

public class JSONSimpleReadExample {

    public static void main(String[] args) throws IOException {

        PDDocument pdfdoc= new PDDocument();
        pdfdoc.addPage(new PDPage());
        pdfdoc.save("/Users/values/IdeaProjects/ReportDeneme/target/Project_Report.pdf");
        Document doc = new Document(PageSize.A4, 36, 36, 90, 36);

        try {

            PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream("/Users/values/IdeaProjects/ReportDeneme/target/Project_Report.pdf"));

            System.out.println("PDF created.");
            doc.open();

            HeaderFooterPageEvent.addHeader(writer);

            PDFTestCaseTable.pdfTestCaseWrite(doc);
            HeaderFooterPageEvent.addFooter(writer);

            doc.close();
            writer.close();

        }
        catch (DocumentException e)
        {
            e.printStackTrace();
        }



    }




}

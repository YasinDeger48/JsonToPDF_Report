package com.report.jsonParse;


import java.awt.*;
import java.awt.Font;
import java.io.*;

import com.itextpdf.kernel.pdf.xobject.PdfFormXObject;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.chart.ui.HorizontalAlignment;

import static com.report.jsonParse.ChartPie.generateBarChart;
import static com.report.jsonParse.ChartPie.generatePieChart;
import static com.report.jsonParse.PieChart.writeChartToPDF;

public class JSONSimpleReadExample {

    public static void main(String[] args) throws IOException {

        PDDocument pdfdoc = new PDDocument();
        pdfdoc.addPage(new PDPage());
        String fileName = "/Users/values/IdeaProjects/ReportDeneme/target/Project_Report.pdf";
        pdfdoc.save(fileName);
        Document doc = new Document(PageSize.A4, 36, 36, 90, 36);

        try {

            PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream(fileName));
            doc.open();

            HeaderFooterPageEvent.addHeader(writer); //header

            PDFTestCaseTable.pdfTestCaseWrite(doc); // table

            HeaderFooterPageEvent.addFooter(writer); //footer


            //PieChartDemo.writeChartToPDF(PieChartDemo.generatePieChart(),400,500,fileName);


        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }


        doc.close();

    }


}


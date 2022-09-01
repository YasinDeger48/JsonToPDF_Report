package com.report.jsonParse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.report.model.Element;
import com.report.model.Result;
import com.report.model.Root;
import com.report.model.Step;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PDFTestCaseTable {

    public static List<String> reportContent() throws IOException {
        List<String> allReports = new ArrayList<>();
        FileInputStream file = new FileInputStream("cucumberReport.json");
        ObjectMapper om = new ObjectMapper();
        Root[] root = om.readValue(file, Root[].class);

        List<ArrayList<Element>> elements = Arrays.stream(root).map(Root::getElements).collect(Collectors.toList());

        List<ArrayList<Step>> stepsList = elements.stream().map(p -> p.get(0).getSteps()).collect(Collectors.toList());

        // System.out.println(stepsList);
        // System.out.println("stepsList.get(0).get(0).getResult().getStatus() = " + stepsList.get(0).get(0).getResult().getStatus());

        int size = stepsList.get(0).size();


        //this method returns passed or fail

        for (int i = 0; i < size; i++) {

            int finalI = i;
            List<Result> getResults = stepsList.stream().map(a -> a.get(finalI).getResult()).collect(Collectors.toList());
            //List<Integer> getLines = stepsList.stream().map(a -> a.get(finalI).getLine()).collect(Collectors.toList());
            List<String> getNames = stepsList.stream().map(a -> a.get(finalI).getName()).collect(Collectors.toList());
            //List<Match> getMatches = stepsList.stream().map(a -> a.get(finalI).getMatch()).collect(Collectors.toList());
            //List<String> getKeywords = stepsList.stream().map(a -> a.get(finalI).getKeyword()).collect(Collectors.toList());
            allReports.add((i + 1) + " - " + getNames.get(0) + " - " + getResults.get(0).getStatus());


        }
        return allReports;
    }

    public static void pdfTestCaseWrite(Document doc){

        try{
            PdfPTable table = new PdfPTable(3);
            table.setWidthPercentage(105);
            float[] columnWidths = {10, 80, 20};
            table.setWidths(columnWidths);
            PdfPCell cell1 = new PdfPCell(new Paragraph("ID"));
            cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            PdfPCell cell2 = new PdfPCell(new Paragraph("Test Case"));
            cell2.setBackgroundColor(BaseColor.LIGHT_GRAY);
            PdfPCell cell3 = new PdfPCell(new Paragraph("Status"));
            cell3.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell3.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            table.addCell(cell1);
            table.addCell(cell2);
            table.addCell(cell3);

            List<String> allReport = PDFTestCaseTable.reportContent();
            for (int i = 0; i < allReport.size(); i++) {
                String[]  arguments = allReport.get(i).split(" - ");
                PdfPCell arg1 = new PdfPCell(new Paragraph(arguments[0]));
                PdfPCell arg2 = new PdfPCell(new Paragraph(arguments[1]));
                PdfPCell arg3 = new PdfPCell(new Paragraph(arguments[2]));
                if(arguments[2].equals("passed")){
                    arg3.setBackgroundColor(BaseColor.GREEN);
                } else if (arguments[2].equals("failed")) {
                    arg3.setBackgroundColor(BaseColor.RED);
                }else {
                    arg3.setBackgroundColor(BaseColor.YELLOW);
                }
                arg3.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                table.addCell(arg1);
                table.addCell(arg2);
                table.addCell(arg3);

            }
            doc.add(table);

        } catch (DocumentException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}

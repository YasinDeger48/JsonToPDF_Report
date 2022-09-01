package com.report.jsonParse;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.*;

import org.json.simple.JSONObject;
import org.json.simple.parser.*;

public class JSONParser {

    public static void main(String[] args) throws JsonProcessingException {

        org.json.simple.parser.JSONParser parser = new org.json.simple.parser.JSONParser();


       try (Reader reader = new FileReader("cucumberReport.json")){

           JSONObject jsonObject = parser.parse(reader);
           System.out.println(jsonObject);


       } catch (FileNotFoundException e) {
           throw new RuntimeException(e);
       } catch (IOException e) {
           throw new RuntimeException(e);
       } catch (ParseException e) {
           throw new RuntimeException(e);
       }

    }




}

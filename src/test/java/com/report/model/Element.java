package com.report.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Date;

@Getter
@Setter
@ToString
public class Element {
    public Date start_timestamp;
    public ArrayList<Before> before;
    public int line;
    public String name;
    public String description;
    public String id;
    public ArrayList<After> after;
    public String type;
    public String keyword;
    public ArrayList<Step> steps;
    public ArrayList<Tag> tags;
}

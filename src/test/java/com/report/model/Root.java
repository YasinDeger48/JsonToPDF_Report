package com.report.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;


@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class Root {
    public int line;
    public ArrayList<Element> elements;
    public String name;
    public String description;
    public String id;
    public String keyword;
    public String uri;
    public ArrayList<Tag> tags;
}

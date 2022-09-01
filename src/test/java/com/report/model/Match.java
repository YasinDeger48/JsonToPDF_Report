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
public class Match {
    public String location;
    public ArrayList<Argument> arguments;
}

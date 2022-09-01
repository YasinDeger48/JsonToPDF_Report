package com.report.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Step {
    public Result result;
    public int line;
    public String name;
    public Match match;
    public String keyword;
}

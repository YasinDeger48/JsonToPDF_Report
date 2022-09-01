package com.report.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Tag {
    public String name;
    public String type;
    public Location location;
}

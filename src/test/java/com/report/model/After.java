package com.report.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;

@Getter
@Setter
@ToString
public class After {
    public ArrayList<Embedding> embeddings;
    public Result result;
    public Match match;
}

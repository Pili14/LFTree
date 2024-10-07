package com.lf.grammar.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class DerivationTree {

    private String name; // aS //aaS // aaaaaaaS
    private List<DerivationTree> children;
    private int depth;

    public DerivationTree(String name, int depth) {
        this.name = name;
        this.children = new ArrayList<>();
        this.depth = depth;
    }


}

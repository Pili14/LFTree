package com.lf.grammar.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GrammarRequest {

    private List<String> terminals;
    private List<String> nonTerminals;
    private String startSymbol;

}

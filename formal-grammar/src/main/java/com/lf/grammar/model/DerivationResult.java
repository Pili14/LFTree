package com.lf.grammar.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
public class DerivationResult {

    private boolean canDerive;
    private List<String> derivationSteps;
    private List<Production> derivationProds;
    private String axiomaticSymbol;

}

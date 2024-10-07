package com.lf.grammar.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Production {

    private String lhs; // Lado izquierdo (no terminal)
    private String rhs; // Lado derecho (producción)


}

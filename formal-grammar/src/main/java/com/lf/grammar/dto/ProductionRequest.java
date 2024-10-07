package com.lf.grammar.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Anotación de Lombok que genera automáticamente los métodos getters, setters, equals, hashCode y toString.
@AllArgsConstructor // Genera un constructor que toma todos los atributos (lhs y rhs) como parámetros.
@NoArgsConstructor // Genera un constructor vacío sin parámetros.

public class ProductionRequest {

    // El lado izquierdo de la producción, que generalmente es un símbolo no terminal.
    private String lhs;

    // El lado derecho de la producción, que puede contener símbolos terminales y/o no terminales.
    private String rhs;
}


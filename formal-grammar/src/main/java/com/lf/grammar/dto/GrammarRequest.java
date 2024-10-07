package com.lf.grammar.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data // Anotación de Lombok que genera automáticamente los métodos getters, setters, equals, hashCode y toString.
@AllArgsConstructor // Genera un constructor con todos los parámetros (en este caso, los tres atributos de la clase).
@NoArgsConstructor // Genera un constructor vacío sin parámetros.

public class GrammarRequest {

    // Lista de símbolos terminales (los símbolos que no pueden derivarse más).
    private List<String> terminals;

    // Lista de símbolos no terminales (los símbolos que se pueden derivar a otros símbolos).
    private List<String> nonTerminals;

    // El símbolo inicial o axioma de la gramática, desde donde comienza la derivación.
    private String startSymbol;
}


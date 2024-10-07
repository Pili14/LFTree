package com.lf.grammar.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data // Lombok genera automáticamente los métodos toString(), equals(), hashCode(), y los getters y setters para todos los campos.
@Getter // Lombok genera automáticamente los métodos 'get' para los atributos de la clase.
@Setter // Lombok genera automáticamente los métodos 'set' para los atributos de la clase.
@AllArgsConstructor // Lombok genera un constructor que toma todos los atributos de la clase como parámetros.
public class DerivationResult {

    // Indica si es posible realizar una derivación con la gramática.
    private boolean canDerive;

    // Almacena los pasos de la derivación, representados como cadenas. 
    // Cada cadena puede representar un estado intermedio en el proceso de derivación.
    private List<String> derivationSteps;

    // Almacena las producciones utilizadas durante la derivación. 
    // Cada producción es un objeto de tipo 'Production', que podría representar una regla de producción en una gramática.
    private List<Production> derivationProds;

    // Representa el símbolo axiomático o inicial de la gramática. 
    // Este es el símbolo desde el cual comienza la derivación.
    private String axiomaticSymbol;

}


package com.lf.grammar.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data // Lombok genera automáticamente los métodos toString(), equals(), hashCode(), getters y setters para todos los campos.
public class DerivationTree {

    // Almacena el nombre del nodo en el árbol de derivación.
    private String name;

    // Almacena la lista de hijos del nodo actual. Cada hijo es otro nodo del tipo DerivationTree.
    private List<DerivationTree> children;

    // Indica la profundidad del nodo en el árbol de derivación, es decir, cuántos niveles hay desde la raíz hasta este nodo.
    private int depth;

    // Constructor que inicializa el nombre del nodo y su profundidad.
    // También inicializa la lista de hijos como una lista vacía.
    public DerivationTree(String name, int depth) {
        this.name = name; // Asigna el nombre del nodo.
        this.children = new ArrayList<>(); // Inicializa la lista de hijos vacía.
        this.depth = depth; // Asigna la profundidad del nodo.
    }

}


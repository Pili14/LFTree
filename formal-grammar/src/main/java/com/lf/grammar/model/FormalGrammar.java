package com.lf.grammar.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FormalGrammar {

    private List<String> terminals = new ArrayList<>(); // Alfabeto o símbolos terminales
    private List<String> nonTerminals = new ArrayList<>(); // Símbolos no terminales
    private List<Production> productionRules = new ArrayList<>(); // Lista de producciones
    private String startSymbol = ""; // Símbolo inicial de la gramática

    // Verifica si la gramática es válida asegurando que ningún símbolo terminal sea no terminal
    public boolean isGrammarValid() {
        if(!nonTerminals.contains(startSymbol)) {
            return false;
        }
        for (String terminal : terminals) {
            if (nonTerminals.contains(terminal)) {
                return false; // Si un símbolo terminal aparece como no terminal, es inválido
            }
        }
        return true;
    }

    // Verifica si una producción es válida asegurando que la producción contiene como máximo un no terminal
    public boolean isValidProduction(Production production) {
        if (!nonTerminals.contains(production.getLhs())) return false; // El lado izquierdo debe ser no terminal
        int nonTerminalCount = 0;
        for (char symbol : production.getRhs().toCharArray()) {
            String symStr = String.valueOf(symbol);
            if (!terminals.contains(symStr) && !nonTerminals.contains(symStr)) {
                return false; // Los símbolos deben ser válidos (en terminales o no terminales)
            }
            if (nonTerminals.contains(symStr)) {
                nonTerminalCount++;
            }
        }
        return nonTerminalCount <= 1; // La producción puede tener como máximo un símbolo no terminal
    }

    // Encuentra el primer no terminal en una cadena dada
    public String findFirstNonTerminal(String str) {
        for (char ch : str.toCharArray()) {
            if (nonTerminals.contains(String.valueOf(ch))) {
                return String.valueOf(ch);
            }
        }
        return null; // No se encontró ningún no terminal
    }

    // Filtra las producciones por clave (lado izquierdo)
    public List<Production> getProductionsForNonTerminal(String nonTerminal) {
        List<Production> filteredProductions = new ArrayList<>();
        for (Production production : productionRules) {
            if (production.getLhs().equals(nonTerminal)) {
                filteredProductions.add(production);
            }
        }
        return filteredProductions;
    }

    // Genera el árbol de derivación general a partir del símbolo inicial
    public DerivationTree createGeneralDerivationTree() {
        DerivationTree rootNode = new DerivationTree(startSymbol, 0);
        generateChildrenNodes(rootNode, 0);
        return rootNode;
    }

    // Genera los hijos de un nodo en el árbol
    private void generateChildrenNodes(DerivationTree node, int depth) {
        if (depth >= 4) return; // Limita la profundidad del árbol

        String nonTerminal = findFirstNonTerminal(node.getName());
        if (nonTerminal != null) {
            List<Production> applicableProductions = getProductionsForNonTerminal(nonTerminal);

            for (Production prod : applicableProductions) {
                String derivedString = node.getName().replaceFirst(nonTerminal, prod.getRhs());
                DerivationTree childNode = new DerivationTree(derivedString, depth + 1);
                node.getChildren().add(childNode);

                if (containsNonTerminals(derivedString)) {
                    generateChildrenNodes(childNode, depth + 1);
                }
            }
        }
    }

    // Determina si una cadena contiene no terminales
    private boolean containsNonTerminals(String str) {
        for (char ch : str.toCharArray()) {
            if (nonTerminals.contains(String.valueOf(ch))) {
                return true;
            }
        }
        return false;
    }

    // Verifica si una palabra puede derivarse a partir del símbolo inicial
    public DerivationResult checkWord(String word) {
        List<String> steps = new ArrayList<>();
        List<Production> productions = new ArrayList<>();
        boolean canDerive = searchWordDerivation(startSymbol, word, steps, productions);
        return new DerivationResult(canDerive, steps, productions, startSymbol);
    }

    // Busca si la palabra puede derivarse mediante producción
    private boolean searchWordDerivation(String current, String word, List<String> steps, List<Production> productions) {
        if (current.equals(word)) return true;

        String nonTerminal = findFirstNonTerminal(current);
        if (nonTerminal == null) return false;

        for (Production prod : getProductionsForNonTerminal(nonTerminal)) {
            String newString = current.replaceFirst(nonTerminal, prod.getRhs());
            productions.add(prod);
            steps.add(newString);

            if (newString.length() > word.length()) {
                steps.remove(steps.size() - 1); // Remueve pasos incorrectos
                productions.remove(productions.size() - 1); // Remueve las producciones incorrectas
                continue;
            }
            if (searchWordDerivation(newString, word, steps, productions)) {
                return true;
            }
            steps.remove(steps.size() - 1); // Si no es correcta la derivación, remover
            productions.remove(productions.size() - 1);
        }
        return false;
    }

    public void addProduction(Production production) {
        productionRules.add(production);
    }

    public boolean deleteProduction(Production production){
        return productionRules.remove(production);
    }

}

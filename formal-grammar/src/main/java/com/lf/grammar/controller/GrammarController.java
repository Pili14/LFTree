package com.lf.grammar.controller;

import com.lf.grammar.dto.GrammarRequest;
import com.lf.grammar.dto.ProductionRequest;
import com.lf.grammar.model.DerivationResult;
import com.lf.grammar.model.DerivationTree;
import com.lf.grammar.model.FormalGrammar;
import com.lf.grammar.model.Production;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController // Anotación de Spring que indica que esta clase será un controlador REST, manejando solicitudes HTTP.
@RequestMapping("/grammar") // Define el punto de entrada para todas las rutas de esta clase, todas comenzarán con "/grammar".
public class GrammarController {

    // Se crea una instancia de FormalGrammar para almacenar la gramática actual.
    private FormalGrammar grammar = new FormalGrammar();

    // Método POST para crear una nueva gramática.
    @PostMapping("/create")
    public ResponseEntity<Map<String, String>> createGrammar(@RequestBody GrammarRequest request) {
        // Establece los terminales, no terminales y símbolo inicial a partir de los datos de la solicitud.
        grammar.setTerminals(request.getTerminals());
        grammar.setNonTerminals(request.getNonTerminals());
        grammar.setStartSymbol(request.getStartSymbol());
        grammar.setProductionRules(new ArrayList<>()); // Inicializa las reglas de producción como una lista vacía.

        // Verifica si la gramática es válida.
        if (!grammar.isGrammarValid()) {
            // Si la gramática no es válida, restablece los valores y devuelve un mensaje de error.
            grammar.setTerminals(List.of());
            grammar.setNonTerminals(List.of());
            grammar.setStartSymbol("");
            return ResponseEntity.badRequest().body(Map.of("message", "Gramatica invalida."));
        }

        // Si la gramática es válida, devuelve un mensaje de éxito.
        return ResponseEntity.ok(Map.of("message", "Grammar created and validated successfully."));
    }

    // Método POST para agregar una producción a la gramática.
    @PostMapping("/addProduction")
    public ResponseEntity<Map<String, String>> addProduction(@RequestBody ProductionRequest productionRequest) {
        // Crea una nueva producción con los valores del cuerpo de la solicitud (LHS y RHS).
        Production production = new Production(
                productionRequest.getLhs(),
                productionRequest.getRhs()
        );
        // Muestra los lados izquierdo y derecho de la producción para depuración.
        System.out.println(productionRequest.getLhs());
        System.out.println(productionRequest.getRhs());

        // Verifica si la producción es válida.
        if (!grammar.isValidProduction(production)) {
            return ResponseEntity.badRequest().body(Map.of("message", "Production invalida"));
        }

        // Si es válida, agrega la producción a la gramática y devuelve un mensaje de éxito.
        grammar.addProduction(production);
        return ResponseEntity.ok(Map.of("message", "Produccion agregada correctamente."));
    }

    // Método DELETE para eliminar una producción de la gramática.
    @DeleteMapping("/deleteProduction")
    public ResponseEntity<Map<String, String>> deleteProduction(@RequestBody ProductionRequest productionRequest) {
        // Crea una producción con los valores de la solicitud (LHS y RHS).
        Production production = new Production(
                productionRequest.getLhs(),
                productionRequest.getRhs()
        );
        // Si la producción es eliminada, muestra las reglas de producción y devuelve un mensaje de éxito.
        if (grammar.deleteProduction(production)) {
            System.out.println(grammar.getProductionRules());
            return ResponseEntity.ok(Map.of("message", "Production eliminado correctamente."));
        }
        // Si no se pudo eliminar, devuelve un mensaje de error.
        return ResponseEntity.ok(Map.of("message", "Produccion no se pudo borrar."));
    }

    // Método GET para obtener todas las producciones actuales de la gramática.
    @GetMapping("/productions")
    public ResponseEntity<List<Production>> getProductions() {
        // Muestra el tamaño de la lista de reglas de producción y devuelve la lista.
        System.out.println(grammar.getProductionRules().size());
        return ResponseEntity.ok(grammar.getProductionRules());
    }

    // Método POST para verificar si una palabra puede ser derivada en la gramática.
    @PostMapping("/checkWord")
    public ResponseEntity<DerivationResult> checkWord(@RequestBody Map<String, String> payload) {
        String word = payload.get("word"); // Obtiene la palabra desde la solicitud.

        // Comprueba si la palabra puede ser derivada según la gramática.
        DerivationResult result = grammar.checkWord(word);

        // Si la palabra puede derivarse, devuelve el resultado; si no, devuelve un error.
        if (result.isCanDerive()) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.badRequest().body(result);
        }
    }

    // Método GET para obtener el árbol de derivación general de la gramática.
    @GetMapping("/generalTree")
    public ResponseEntity<DerivationTree> getGeneralDerivationTree() {
        // Crea el árbol de derivación general de la gramática y lo devuelve.
        DerivationTree tree = grammar.createGeneralDerivationTree();
        return ResponseEntity.ok(tree);
    }
}

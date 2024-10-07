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

@RestController
@RequestMapping("/grammar")
public class GrammarController {

    private FormalGrammar grammar = new FormalGrammar();

    @PostMapping("/create")
    public ResponseEntity<Map<String, String>> createGrammar(@RequestBody GrammarRequest request) {
        grammar.setTerminals(request.getTerminals());
        grammar.setNonTerminals(request.getNonTerminals());
        grammar.setStartSymbol(request.getStartSymbol());
        grammar.setProductionRules(new ArrayList<>());

        if (!grammar.isGrammarValid()) {
            grammar.setTerminals(List.of());
            grammar.setNonTerminals(List.of());
            grammar.setStartSymbol("");
            return ResponseEntity.badRequest().body(Map.of("message", "Gramatica invalida."));
        }

        return ResponseEntity.ok(Map.of("message", "Grammar created and validated successfully."));
    }

    @PostMapping("/addProduction")
    public ResponseEntity<Map<String, String>> addProduction(@RequestBody ProductionRequest productionRequest) {
        Production production = new Production(
                productionRequest.getLhs(),
                productionRequest.getRhs()
        );
        System.out.println(productionRequest.getLhs());
        System.out.println(productionRequest.getRhs());

        if (!grammar.isValidProduction(production)) {
            return ResponseEntity.badRequest().body(Map.of("message", "Production invalida"));
        }

        grammar.addProduction(production);
        return ResponseEntity.ok(Map.of("message", "Produccion agregada correctamente."));
    }

    @DeleteMapping("/deleteProduction")
    public ResponseEntity<Map<String, String>> deleteProduction(@RequestBody ProductionRequest productionRequest) {
        Production production = new Production(
                productionRequest.getLhs(),
                productionRequest.getRhs()
        );
        if(grammar.deleteProduction(production)) {
            return ResponseEntity.ok(Map.of("message", "Production eliminado correctamente."));
        }
        return ResponseEntity.ok(Map.of("message", "Produccion no se pudo borrar."));
    }

    @GetMapping("/productions")
    public ResponseEntity<List<Production>> getProductions() {
        return ResponseEntity.ok(grammar.getProductionRules());
    }

    @PostMapping("/checkWord")
    public ResponseEntity<DerivationResult> checkWord(@RequestBody Map<String, String> payload) {
        String word = payload.get("word");

        DerivationResult result = grammar.checkWord(word);

        if (result.isCanDerive()) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.badRequest().body(result);
        }
    }

    @GetMapping("/generalTree")
    public ResponseEntity<DerivationTree> getGeneralDerivationTree() {
        DerivationTree tree = grammar.createGeneralDerivationTree();
        return ResponseEntity.ok(tree);
    }
}

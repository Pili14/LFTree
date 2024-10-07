package com.lf.grammar;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest // Anotación que indica que esta clase es una prueba que cargará el contexto completo de Spring Boot para realizar los tests.
class FormalGrammarApplicationTests {

    @Test // Indica que este es un método de prueba (unit test) que será ejecutado por el framework de pruebas (JUnit en este caso).
    void contextLoads() {
        // Este método verifica si el contexto de la aplicación Spring se carga correctamente.
        // Si el contexto no se carga, la prueba fallará. No tiene aserciones porque simplemente valida la carga del contexto.
    }

}


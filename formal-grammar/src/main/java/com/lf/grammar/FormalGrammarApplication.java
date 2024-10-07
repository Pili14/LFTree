package com.lf.grammar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // Anotación que combina @Configuration, @EnableAutoConfiguration y @ComponentScan.
// Configura esta clase como el punto de entrada de una aplicación Spring Boot.
public class FormalGrammarApplication {

    // Método principal que inicia la aplicación Spring Boot.
    public static void main(String[] args) {
        // 'SpringApplication.run' arranca el contexto de Spring Boot y la aplicación, tomando esta clase como referencia.
        SpringApplication.run(FormalGrammarApplication.class, args);
    }

}


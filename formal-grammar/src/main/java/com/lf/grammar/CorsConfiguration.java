package com.lf.grammar;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration // Indica que esta clase contiene la configuración de Spring, específicamente para CORS en este caso.
public class CorsConfiguration implements WebMvcConfigurer {

    // Sobrescribe el método 'addCorsMappings' de la interfaz 'WebMvcConfigurer' para configurar las reglas CORS.
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // Configura el mapeo de CORS para todas las rutas (/**) en la aplicación.
        registry.addMapping("/**")
                // Permite solicitudes desde cualquier origen (dominio).
                .allowedOrigins("*")
                // Permite todos los métodos HTTP (GET, POST, PUT, DELETE, etc.).
                .allowedMethods("*")
                // Permite todos los encabezados en las solicitudes.
                .allowedHeaders("*");
    }
}



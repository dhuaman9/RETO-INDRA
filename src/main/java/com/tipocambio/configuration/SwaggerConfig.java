package com.tipocambio.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

	
	 	@Bean
	    public OpenAPI apiInfo() {
	        return new OpenAPI()
	                .info(new Info()
	                        .title("API de Tipo de Cambio")
	                        .description("Documentación de la API para el Tipo de Cambio")
	                        .version("1.0.0"));
	    }
	
}

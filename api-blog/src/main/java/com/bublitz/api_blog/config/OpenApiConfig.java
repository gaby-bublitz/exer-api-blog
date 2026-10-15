package com.bublitz.api_blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

/**
 * CLASSE DE CONFIGURAÇÃO DO OPENAPI (SWAGGER).
 * DEFINE AS INFORMAÇÕES GLOBAIS DA DOCUMENTAÇÃO DA API DA APLICAÇÃO.
 */

@Configuration
public class OpenApiConfig {
	
    /**
     * CRIA E CONFIGURA A INSTÂNCIA DO OPENAPI PARA O ECOSSISTEMA DO SPRING.
     * ESTE BEAN É RESPONSÁVEL POR EXPOR OS METADADOS DA API NOS ENDPOINTS DO SWAGGER UI.
     *
     * @return UM OBJETO OPENAPI CONFIGURADO COM O TÍTULO, VERSÃO E DESCRIÇÃO DA API.
     */
	
	@Bean
	public OpenAPI blogOpenAPI() {
		
		return new OpenAPI()
				.info(new Info()
				.title("Blog API - API Corporativa")
				.version("1.0.0")
				.description("API didática para posts e comentários."));
	}
}

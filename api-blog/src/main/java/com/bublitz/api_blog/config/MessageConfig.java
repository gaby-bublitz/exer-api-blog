package com.bublitz.api_blog.config;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

@Configuration
public class MessageConfig {
	
	@Bean
	public MessageSource messageSource() {

	    // CRIA OBJETO RESPONSÁVEL POR CARREGAR AS MENSAGENS DA APLICAÇÃO
	    var source = new ReloadableResourceBundleMessageSource();

	    source.setBasename("classpath:messages");
	    source.setDefaultEncoding(StandardCharsets.UTF_8.name()); // CODIFICAÇÃO DOS ARQUIVOS
	    source.setFallbackToSystemLocale(false);

	    // RETORNA O MessageSource CONFIGURADO PARA QUE O SPRING POSSA USÁ-LO NA APLICAÇÃO
	    return source;
	}
	
	//
	
	@Bean
	public LocaleResolver localeResolver() {

		// CRIA COMPONENTE RESPONSÁVEL POR IDENTIFICAR QUAL IDIOMA/LOCALIDADE DEVE SER USADA
	    var resolver = new AcceptHeaderLocaleResolver();

	    // DEFINE O IDIOMA PADRÃO
	    resolver.setDefaultLocale(new Locale("pt", "BR"));

	    // DEFINE AS LOCALIDADES ACEITÁVEIS
	    resolver.setSupportedLocales(List.of(
	        new Locale("pt", "BR"),
	        new Locale("en", "US"),
	        new Locale("es", "ES")
	    ));

	 // RETORNA O LocaleResolver CONFIGURADO PARA QUE O SPRING POSSA USÁ-LO NAS REQUISIÇÕES
	    return resolver;
	}
}

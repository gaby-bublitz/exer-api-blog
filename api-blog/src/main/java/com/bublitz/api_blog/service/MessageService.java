package com.bublitz.api_blog.service;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

// CENTRALIZA A LEITURA DAS MENSAGENS EXTERNALIZADAS. O LOCALE VEM AUTOMATICAMENTE DA REQUISIÇÃO HTTP.

@Service
public class MessageService {
		private final MessageSource messageSource;
		
		public MessageService(MessageSource messageSource) {
			this.messageSource = messageSource;
		}
		
		public String getMessage(String key, Object... args) {
			return messageSource.getMessage(key, args, LocaleContextHolder.getLocale());
		}
}


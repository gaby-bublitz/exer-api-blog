package com.bublitz.api_blog.teste.context;

public record ContextoResponseDto(
		
		String locale,
		String timezone,
		String data,
		String moeda,
		String numero
) {}

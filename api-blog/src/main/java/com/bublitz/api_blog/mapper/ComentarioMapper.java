package com.bublitz.api_blog.mapper;

import org.springframework.stereotype.Component;

import com.bublitz.api_blog.dto.request.ComentarioRequestDto;
import com.bublitz.api_blog.dto.response.ComentarioResponseDto;
import com.bublitz.api_blog.model.ComentarioModel;
import com.bublitz.api_blog.model.PostModel;

@Component
public class ComentarioMapper {
	
	public ComentarioModel toEntity(ComentarioRequestDto dto, PostModel entity) {
		
		if (dto == null) {
			return null;
		}
		
		
		return new ComentarioModel (dto.comentario().toString(), entity);
		
	}
	
	public ComentarioResponseDto toDto (ComentarioModel entity) {
		if (entity == null) {
			return null;
		}
		
		return new ComentarioResponseDto (entity.getId(), entity.getData(), entity.getComentario());
	}
}

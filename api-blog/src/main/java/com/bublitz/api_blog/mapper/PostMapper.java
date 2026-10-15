package com.bublitz.api_blog.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.bublitz.api_blog.dto.request.PostRequestDto;
import com.bublitz.api_blog.dto.response.ComentarioResponseDto;
import com.bublitz.api_blog.dto.response.PostResponseDto;
import com.bublitz.api_blog.model.ComentarioModel;
import com.bublitz.api_blog.model.PostModel;

@Component
public class PostMapper {
	
	private final ComentarioMapper comentarioMapper;
	
	public PostMapper(ComentarioMapper comentarioMapper) {
		this.comentarioMapper = comentarioMapper;
	}

	public PostModel toEntity(PostRequestDto dto) {
		if (dto == null) {
			return null;
		}
		
		return new PostModel(dto.autor(), dto.titulo(), dto.texto());
	}
	
	public PostResponseDto toDto(PostModel entity) {
		if (entity == null) {
			return null;
		} 
		
		List<ComentarioResponseDto> comentariosDto = new ArrayList<>();
		
		if (entity.getComentarios() != null) {
			for (ComentarioModel comentario : entity.getComentarios()) {
				ComentarioResponseDto dto = comentarioMapper.toDto(comentario);
				comentariosDto.add(dto);
			}
		}
		
		return new PostResponseDto(entity.getId(), entity.getAutor(), entity.getData(), entity.getTitulo(), entity.getTexto(), comentariosDto);
	}
}

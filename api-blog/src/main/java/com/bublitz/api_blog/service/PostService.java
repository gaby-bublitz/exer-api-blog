package com.bublitz.api_blog.service;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.bublitz.api_blog.dto.request.ComentarioRequestDto;
import com.bublitz.api_blog.dto.request.PostRequestDto;
import com.bublitz.api_blog.dto.response.ComentarioResponseDto;
import com.bublitz.api_blog.dto.response.PostResponseDto;

public interface PostService {
	
	// RETORNA UMA LISTA DE POSTS
	//List<PostResponseDto> findAll();
	
	Page<PostResponseDto> findAll (Pageable pageable);
	Page<PostResponseDto> findAll (Pageable pageable, String titulo);

	
	// RETORNA UM UNICO POST PASSANDO O ID
	PostResponseDto findById(UUID id);
	
	// SALVA UM POST NO BANCO
	PostResponseDto createPost(PostRequestDto dto);
	
	// TRATA COMENTÁRIOS DOS POSTS
	ComentarioResponseDto addComentario(UUID postId, ComentarioRequestDto dto);
	
	// ATUALIZA COMENTÁRIO PELA ID
	ComentarioResponseDto updateComentario(UUID comentarioId, ComentarioRequestDto dto);
	
	// DELETA COMENTARIO PELA ID
	void deleteComentario(UUID comentarioId);
}

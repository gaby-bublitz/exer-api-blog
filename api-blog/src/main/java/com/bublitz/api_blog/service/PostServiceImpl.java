package com.bublitz.api_blog.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bublitz.api_blog.dto.request.ComentarioRequestDto;
import com.bublitz.api_blog.dto.request.PostRequestDto;
import com.bublitz.api_blog.dto.response.ComentarioResponseDto;
import com.bublitz.api_blog.dto.response.PostResponseDto;
import com.bublitz.api_blog.exception.ComentarioNotFoundException;
import com.bublitz.api_blog.exception.PostNotFoundException;
import com.bublitz.api_blog.mapper.ComentarioMapper;
import com.bublitz.api_blog.mapper.PostMapper;
import com.bublitz.api_blog.model.ComentarioModel;
import com.bublitz.api_blog.model.PostModel;
import com.bublitz.api_blog.repository.ComentarioRepository;
import com.bublitz.api_blog.repository.PostRepository;

import jakarta.persistence.EntityNotFoundException;


@Service // PODEM CONTER REGRAS DE NEGOCIO
public class PostServiceImpl implements PostService {
	
	private final PostRepository postRepository;
	private final PostMapper postMapper;
	
	private final ComentarioRepository comentarioRepository;
	private final ComentarioMapper comentarioMapper;
	
	private final MessageService messageService;
	
	// PONTO DE INJEÇÃO
	public PostServiceImpl(PostRepository postRepository, PostMapper postMapper, ComentarioRepository comentarioRepository, ComentarioMapper comentarioMapper, MessageService messageService) {
		
		this.postRepository = postRepository;
		this.postMapper = postMapper;
		this.comentarioRepository = comentarioRepository;
		this.comentarioMapper = comentarioMapper;
		// MESSAGE SERVICE:
		this.messageService = messageService;
		
	}
	
	@Override
	@Transactional(readOnly = true)
	public Page<PostResponseDto> findAll(Pageable pageable) { // !!!
		
		Page<PostModel> posts = postRepository.findAll(pageable);
		
		return posts.map(post -> postMapper.toDto(post));
		
		/*
			return posts.map(post -> {
				PostResponseDto dto = postMapper.toDto(post);
				return dto;

		
		List<PostModel> posts = postRepository.findAll();
		List<PostResponseDto> dtos = new ArrayList<>();
		
		for (PostModel post : posts) {
			dtos.add(postMapper.toDto(post));}
			
		*/
	};
	
	//
	
	@Override
	@Transactional(readOnly = true)
	public Page <PostResponseDto> findAll(Pageable pageable, String titulo) { // !!!
		
		Page <PostModel> posts;
		
		if (titulo == null || titulo.isBlank()) {
			posts = postRepository.findAll(pageable);
			
		} else {
			posts = postRepository.findByTituloContainingIgnoreCase(titulo, pageable);
		}
		
		List<PostResponseDto> response = new ArrayList<>();
		
		for (PostModel post : posts.getContent()) {
			PostResponseDto dto = postMapper.toDto(post);
			response.add(dto);
		}
		
		return posts.map(postMapper::toDto);
		
		// return new PageImpl<>(response, pageable, posts.getTotalElements());
	}
	
	
	//
	
	@Override
	@Transactional(readOnly = true)
	public PostResponseDto findById(UUID id) { // !!!
		
		/*
		
		Optional<PostModel> optionalPost = postRepository.findById(id);
		
		if (optionalPost.isEmpty()) {
			throw new RuntimeException("Post não encontrado com o ID: " + id);
		}
		
		PostModel post = optionalPost.get();
		
		*/
		
		// BUSCA COM OPTIONAL E LANÇA A EXCEÇÃO USANDO LAMBDA SE ESTIVER VAZIO 
		
		PostModel post = postRepository.findById(id)
				.orElseThrow(() -> new PostNotFoundException(messageService.getMessage("post.nao.encontrado")));
		
		// RETORNA CONVERTIDO PELO MAPPER
		return postMapper.toDto(post);
	};
	
	//
	
	@Override
	@Transactional
	public PostResponseDto createPost(PostRequestDto dto) {
		
		PostModel post = postMapper.toEntity(dto);
		PostModel saved = postRepository.save(post);
		
		return postMapper.toDto(saved);
	}
	
	// COMENTÁRIOS
	
	
	// ADICIONA COMENTARIO
	
	@Override
	@Transactional
	
	public ComentarioResponseDto addComentario (UUID postId, ComentarioRequestDto dto) {
		
		Optional<PostModel> optionalPost = postRepository.findById(postId);
		
		
		PostModel post = optionalPost.get();
		
		ComentarioModel comentario = new ComentarioModel(dto.comentario(), post);
		
		post.adicionarComentario(comentario);
		ComentarioModel saved = comentarioRepository.save(comentario);
		
		return comentarioMapper.toDto(saved);
				
				
	}
	
	// ATUALIZA COMENTARIO PELO ID
	
	@Override
	@Transactional
	
	public ComentarioResponseDto updateComentario (UUID comentarioId, ComentarioRequestDto dto) {
	
		
		// BUSCA OU LANÇA EXCECAO SOBRE EXISTÊNCIA DO COMENTARIO
		ComentarioModel comentario = comentarioRepository.findById(comentarioId)
		        .orElseThrow(() -> new ComentarioNotFoundException(messageService.getMessage("comentario.nao.encontrado")));
			
		// ATUALIZA TEXTO
			
		comentario.setComentario(dto.comentario());
		
		// SALVA NO BANCO
		
		ComentarioModel comentarioAtualizado = comentarioRepository.save(comentario);
		
		// RETORNA CONVERTENDO COM O MAPPER DO SEU PROJETO
        return comentarioMapper.toDto(comentarioAtualizado);
		
	}
	
	
	// DELETA COMENTARIO PELO ID
    @Override
    @Transactional
    public void deleteComentario(UUID comentarioId) {
        
        // VERIFICA SE EXISTE ANTES DE DELETAR
        if (!comentarioRepository.existsById(comentarioId)) {
            throw new ComentarioNotFoundException(messageService.getMessage("comentario.nao.encontrado"));
        }
        
        // DELETA DO BANCO
        comentarioRepository.deleteById(comentarioId);
    }
	
}

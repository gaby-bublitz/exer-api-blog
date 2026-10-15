package com.bublitz.api_blog.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_comentario")
public class ComentarioModel implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	
	@Column(nullable = false)
	private LocalDate data;
	
	@Lob // CRIA UM teXTO GRANDE
	@Column(columnDefinition = "TEXT", nullable = false)
	private String comentario;
	
	@ManyToOne(fetch = FetchType.LAZY) // CARREGAMENTO PREGUIÇOSO
	@JoinColumn(name = "post_id", nullable = false) // FOREIGN KEY
	private PostModel post;
	
	public ComentarioModel() {}

	public ComentarioModel(String comentario, PostModel post) {
		this.comentario = comentario;
		this.post = post;
		this.data = LocalDate.now();
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public PostModel getPost() {
		return post;
	}

	public void setPost(PostModel post) {
		this.post = post;
	}	

}
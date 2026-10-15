package com.bublitz.api_blog.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="TB_POST")
public class PostModel implements Serializable {
	
	// Preservar estado do objeto
	private static final long serialVersionUID = 1L;
	
	// ATRIBUTOS
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) // anotação para geração do id no banco automático
	private UUID id; // UUID: Universally Unique Identifier
	
	@Column(nullable = false, length = 70)
	private String autor;
	
	@Column(nullable = false)
	private LocalDate data;
	
	@Column(nullable = false, length = 100)
	private String titulo;
	
	@Lob // Para o banco aceitar texto grande e ter boa perfomance
	@Column(nullable = false, columnDefinition = "text")
	private String texto;
	
	//
	
	@OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true) // MappedBy: PROCURA O DONO DA CHAVE | Cascade: REPLICA OPERAÇÕES
	private List<ComentarioModel> comentarios = new ArrayList<>();
	
	// CONSTRUTOR
	public PostModel() {}
	

	public PostModel( String autor, String titulo, String texto) {
		this.autor = autor;
		this.titulo = titulo;
		this.texto = texto;
		this.data = LocalDate.now();
	}
	
	public void adicionarComentario(ComentarioModel comentario) {
		comentarios.add(comentario);
		comentario.setPost(this);

	}

	
	// GETTERS & SETTERS
	
	public UUID getId() {
		return id;
	}

	public List<ComentarioModel> getComentarios() {
		return comentarios;
	}

	public void setComentarios(List<ComentarioModel> comentarios) {
		this.comentarios = comentarios;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}
	
	
}

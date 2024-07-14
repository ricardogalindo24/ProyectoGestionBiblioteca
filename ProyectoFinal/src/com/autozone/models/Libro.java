package com.autozone.models;


public class Libro {
	
	
	private Integer id;
	private String ISBN;
	private String titulo;
	private String autor;
	private String genero;

	public Libro(Integer id, String iSBN, String titulo, String autor, String genero) {
		super();
		this.id = id;
		ISBN = iSBN;
		this.titulo = titulo;
		this.autor = autor;
		this.genero = genero;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}
}

package com.autozone.models;

import com.autozone.annotations.NotNull;

public class Libro {
	
	@NotNull
	private String ISBN;
	@NotNull
	private String titulo;
	private String autor;
	private String genero;






	public Libro(String iSBN, String titulo, String autor, String genero) {
		super();
		ISBN = iSBN;
		this.titulo = titulo;
		this.autor = autor;
		this.genero = genero;
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

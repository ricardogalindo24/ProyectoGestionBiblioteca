package com.autozone.models;

public class LibroAccion {
	private Libro libro;
	private String action;
	
	public LibroAccion(Libro libro, String action) {
		super();
		this.libro = libro;
		this.action = action;
	}

	public Libro getLibro() {
		return libro;
	}

	public void setLibro(Libro libro) {
		this.libro = libro;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}
	
	
}

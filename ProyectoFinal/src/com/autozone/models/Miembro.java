package com.autozone.models;


public class Miembro {
	

	private String nombre;
	private Integer id_miembro;

	public Miembro(String nombre, Integer id_miembro) {
		super();
		this.nombre = nombre;
		this.id_miembro = id_miembro;
	}

	public int getId_miembro() {
		return id_miembro;
	}

	public void setId_miembro(Integer id_miembro) {
		this.id_miembro = id_miembro;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
}

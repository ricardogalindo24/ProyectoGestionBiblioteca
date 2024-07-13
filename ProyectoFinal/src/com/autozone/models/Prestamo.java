package com.autozone.models;

import java.sql.Date;

import com.autozone.annotations.NotNull;

public class Prestamo {
	
	@NotNull
	private Integer id_miembro;
	@NotNull
	private Boolean disponible;
	@NotNull
	private String ISBN;
	private Date fecha_prestamo;
	private Date fecha_devolucion;
	private Integer id_prestamo;

	

	
	
	public Prestamo(Integer id_miembro, Boolean disponible, String iSBN, Date fecha_prestamo, Date fecha_devolucion, Integer id_prestamo) {
		super();
		this.id_miembro = id_miembro;
		this.disponible = disponible;
		ISBN = iSBN;
		this.fecha_prestamo = fecha_prestamo;
		this.fecha_devolucion = fecha_devolucion;
		this.id_prestamo = id_prestamo;
	}

	
	
	public int getId_prestamo() {
		return id_prestamo;
	}



	public void setId_prestamo(Integer id_prestamo) {
		this.id_prestamo = id_prestamo;
	}



	
	
	
	public Integer getId_miembro() {
		return id_miembro;
	}



	public void setId_miembro(Integer id_miembro) {
		this.id_miembro = id_miembro;
	}



	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	

	public Boolean getDisponible() {
		return disponible;
	}

	public void setDisponible(Boolean disponible) {
		this.disponible = disponible;
	}

	public Date getFecha_prestamo() {
		return fecha_prestamo;
	}

	public void setFecha_prestamo(Date fecha_prestamo) {
		this.fecha_prestamo = fecha_prestamo;
	}

	public Date getFecha_devolucion() {
		return fecha_devolucion;
	}

	public void setFecha_devolucion(Date fecha_devolucion) {
		this.fecha_devolucion = fecha_devolucion;
	}


}

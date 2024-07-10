package com.autozone.interfaces;

import java.util.List;
import java.util.Scanner;


/**
 * *
 * @param <T> Clase que corresponde al tipo de modelo que usaremos (Ej. Libro, Miembro, Prestamo)
 * @param <V> DAO que corresponde al tipo de modelo que usaremos (Ej. LibroDAO, MiembroDAO, PrestamoDAO)
 */


public interface DatabaseManager<T, V> {
	

	public Boolean getLoopList();
	public void setLoopList(Boolean loopList);
	public String getAction();
	public void setAction(Scanner scanner) throws IllegalArgumentException, IllegalAccessException;
	public T build(Scanner scanner) throws IllegalArgumentException, IllegalAccessException;
	public void addtoBatchList(List<T> ts, T t, Scanner scanner);
	public void executeAction(V v, T t, String action);
	public void searchQueryBuilder(V v, Scanner scanner);
		
		
	}
	




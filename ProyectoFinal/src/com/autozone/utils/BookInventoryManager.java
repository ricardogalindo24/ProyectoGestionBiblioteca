package com.autozone.utils;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.autozone.annotations.NotNull;
import com.autozone.annotations.ValidAction;
import com.autozone.dao.LibroDAO;
import com.autozone.interfaces.DatabaseManager;
import com.autozone.models.Libro;

public class BookInventoryManager implements DatabaseManager<Libro, LibroDAO> {
	@NotNull
	@ValidAction
	String action;
	//variable para determinar si se desean agregar mas libros a la lista de trabajo
	Boolean loopList;
	//Libro libro = new Libro(null,null,null,null);



	
	List<Libro> libros = new ArrayList<>();
	LibroDAO libroDAO = new LibroDAO();
	Scanner scanner;
	
	
	
	public void setScanner(Scanner scanner) {
		this.scanner = scanner;
	}



	public LibroDAO getLibroDAO() {
		return libroDAO;
	}



	public List<Libro> getLibros() {
		return libros;
	}

	
	
	public Scanner getScanner() {
		return scanner;
	}

	@Override
	public Boolean getLoopList() {
		return loopList;
	}
	
	@Override
	public void setLoopList(Boolean loopList) {
		this.loopList = loopList;
	}
	
	@Override
	public String getAction() {
		return action;
	}
	
	@Override
	public void setAction(Scanner scanner) throws IllegalArgumentException, IllegalAccessException{
		
		
		try{ 
		
		//info
	
		System.out.println("Type A to add books, D to delete a book, U to update an existing book, S to search, I to display inventory. Then press enter to continue.");
		System.out.print("Action: ");
		
		//Capturamos los linea
		this.action = scanner.next().toUpperCase();
		
		if (action.equals("S") || action.equals("I")) {
			loopList = false;
			
		} else {
			loopList = true;
		}
		
		} catch (Exception exception) {
			System.out.println("Error");
			exception.printStackTrace();
		}
		
		
			
	}
	
	@Override
	public Libro build(Scanner scanner) throws IllegalArgumentException, IllegalAccessException {
		String ISBN = null;
		String titulo = null;
		String autor = null;
		String genero = null;
		
		try{ 
		if (action.equals("D")) {
			
			System.out.print("ISBN: ");
			ISBN = scanner.next().trim().toUpperCase();
			titulo = ("");
		} else {
			System.out.print("ISBN: ");
			ISBN = scanner.next().trim().toUpperCase();
		
			System.out.print("Título: ");
			titulo = scanner.next().trim();
		
			System.out.print("Autor: ");
			autor = scanner.next().trim();
		
			System.out.print("Género: ");
			genero= scanner.next().trim();
			
		
		}
		} catch (Exception exception) {
			System.out.println("Error");
			exception.printStackTrace();
		}
		Libro libro = new Libro(ISBN,titulo,autor,genero);
		Validator.validate(libro);
		return libro;
		
		}
	@Override
	public void addtoBatchList(List<Libro> libros, Libro libro, Scanner scanner) {
		
		try{ 
			
			switch(action){
			
			case "D":
				System.out.println("Delete book with ISBN: "+ libro.getISBN()  + " ----- Add to batch <y/n> [Default: No]");
				break;
			
			case "A":
				System.out.println("Add " + libro.getISBN() + " | " + libro.getTitulo() + " | " + libro.getAutor() + " | " + libro.getGenero() + " | "  + " ------ Add to batch <y/n> [Default: No]");
				break;
			case "U":
				System.out.println("Update " + libro.getISBN() + " | " + libro.getTitulo() + " | " + libro.getAutor() + " | " + libro.getGenero() + " | "  + " ------ Add to batch <y/n> [Default: No]");
				break;
			}
			
			String option = scanner.next().trim();
				
			if(option.toUpperCase().equals("Y")) {
				libros.add(libro);
				System.out.println("Added to batch.");
		
				} else { System.out.println("Not added to batch."); }
		
		} catch (Exception exception) {
			System.out.println("Error");
			exception.printStackTrace();
		}
		}
		
	
	
	@Override
	public void executeAction(LibroDAO libroDAO, Libro libro, String action) {
		
		try {
		switch(action){
		
		case "A":
			libroDAO.add(libro);
			break;
			
		case "D":
			libroDAO.delete(libro.getISBN());
			break;
			
		case "U":
			libroDAO.update(libro);
			break;
			
		case "S":
		    
			searchQueryBuilder(libroDAO, scanner);
			
			break;
			
		case "I":
			try {
				libros = libroDAO.fetchRecords();
				System.out.println("Inventory:");
				System.out.println("ISBN" + " | " + "Titulo" + " | " + "Autor" + " | " + "Genero" );
				for (Libro l : libros) {
					System.out.println(l.getISBN() + " | " + l.getTitulo() + " | " + l.getAutor() + " | " + l.getGenero() );
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			break;
			
			
		default:
			System.out.println(libro.getISBN() + " | " + libro.getTitulo() + " | " + libro.getAutor() + " | " + libro.getGenero() + " | " + action + " Acción Inválida o no Definida");
			break;
			}
		
		} catch (Exception exception) {
				System.out.println("Error");
				exception.printStackTrace();
			}
	}
	
	@Override
	public void searchQueryBuilder(LibroDAO libroDAO, Scanner scanner) {
		
		try {
		System.out.println("Search by (1) ISBN [Default], (2) title, (3) author or (4) genre?");
		String field = scanner.next().trim().toUpperCase();
		String criteria;
		List<Libro> libros = null;
		
		switch(field){
		
		case "1":
			System.out.print("ISBN: ");
			criteria = scanner.next().trim();
			libros = libroDAO.search("ISBN", criteria);
			break;
			
		case "2":
			System.out.print("titulo: ");
			criteria = scanner.next().trim();
			libros =  libroDAO.search("titulo", criteria);
			break;
			
		case "3":
			System.out.print("autor: ");
			criteria = scanner.next().trim();
			libros =  libroDAO.search("autor", criteria);
			break;
			
		case "4":
			System.out.print("genero: ");
			criteria = scanner.next().trim();
			libros =  libroDAO.search("genero",criteria);
			break;
			

			
		default:
			System.out.print("ISBN: ");
			criteria = scanner.next().trim();
			libros = libroDAO.search("ISBN", criteria);
			break;
			}
		System.out.println("Search results:");
		System.out.println("ISBN" + " | " + "Titulo" + " | " + "Autor" + " | " + "Genero" );
		
		for (Libro l : libros) { 
			
			System.out.println(l.getISBN() + " | " + l.getTitulo() + " | " + l.getAutor() + " | " + l.getGenero() );
		}
		
		} catch (Exception exception) {
				System.out.println("Error");
				exception.printStackTrace();
			}
	}
		
		
	}
	


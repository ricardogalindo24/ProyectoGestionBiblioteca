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
		System.out.println("-----------------------------------------------------------------------------------");
		System.out.println("Please select an option then press enter to continue: \n"); 
		System.out.println("[A] to add books,"); 
		System.out.println("[D] to delete a book,"); 
		System.out.println("[U] to update an existing book,"); 
		System.out.println("[S] to search,");
		System.out.println("[I] to display inventory,"); 
		System.out.println("[E] to exit this sub menu \n");
		System.out.println("-----------------------------------------------------------------------------------\n");
		
		System.out.println("Action: ");
		
		//Capturamos los linea
		this.action = scanner.nextLine().toUpperCase();
		
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
		Integer id = null;
		String ISBN = null;
		String titulo = null;
		String autor = null;
		String genero = null;
		
		try{ 
		if (action.equals("D")) {
			
			System.out.println("ID: ");
			id = Integer.valueOf(scanner.nextLine().trim());
			titulo = ("");
			
		} else {
			System.out.println("id: ");
			id = Integer.valueOf(scanner.nextLine().trim());
			
			System.out.println("ISBN: ");
			ISBN = scanner.nextLine().trim().toUpperCase();
		
			System.out.println("Título: ");
			titulo = scanner.nextLine().trim();
		
			System.out.println("Autor: ");
			autor = scanner.nextLine().trim();
		
			System.out.println("Género: ");
			genero= scanner.nextLine().trim();
			
		
		}
		} catch (Exception exception) {
			System.out.println("Error");
			exception.printStackTrace();
		}
		Libro libro = new Libro(id, ISBN, titulo, autor, genero);
		Validator.validate(libro);
		return libro;
		
		}
	@Override
	public void addtoBatchList(List<Libro> libros, Libro libro, Scanner scanner) {
		
		try{ 
			
			switch(action){
			
			case "D":
				System.out.println("Delete book with ID: "+ libro.getId()  + " ----- Add to batch <y/n> [Default: No]");
				break;
			
			case "A":
				System.out.printf("%-20s%-20s%-20s%-20s%-20s%-20s\n", "id",  "ISBN", "title", "author", "Genre", "Action");
				System.out.printf("%-20s%-20s%-20s%-20s%-20s%-20s%-20s\n", libro.getId(),  libro.getISBN(), libro.getTitulo(), libro.getAutor(), libro.getGenero(), "Add", "------ Add to batch <y/n> [Default: No]");
				//System.out.println("Add " + libro.getId() + " | " + libro.getISBN() + " | " + libro.getTitulo() + " | " + libro.getAutor() + " | " + libro.getGenero() + " | "  + " ------ Add to batch <y/n> [Default: No]");
				break;
			case "U":
				System.out.printf("%-20s%-20s%-20s%-20s%-20s%-20s\n", "id",  "ISBN", "title", "author", "Genre", "Action");
				System.out.printf("%-20s%-20s%-20s%-20s%-20s%-20s%-20s\n", libro.getId(),  libro.getISBN(), libro.getTitulo(), libro.getAutor(), libro.getGenero(),"Update", "------ Update to batch <y/n> [Default: No]");
				//System.out.println("Update " + libro.getId() + " | " + libro.getISBN() + " | " + libro.getTitulo() + " | " + libro.getAutor() + " | " + libro.getGenero() + " | "  + " ------ Add to batch <y/n> [Default: No]");
				break;
			}
			
			String option = scanner.nextLine().trim();
				
			if(option.toUpperCase().equals("Y")) {
				libros.add(libro);
				System.out.println("☑ Added to batch.");
		
				} else { System.out.println("❌ Not added to batch."); }
		
		} catch (Exception exception) {
			System.out.println("Error");
			exception.printStackTrace();
		}
		}
		
	
	
	@Override
	public void executeAction(LibroDAO libroDAO, Libro libro, String action) {
		
		try {
		switch(action){
		
		default:
			System.out.println("Not a valid option! \n  ");
			action = "E";
			loopList = false;
			break;
			
		case "A":
			libroDAO.add(libro);
			break;
			
		case "D":
			libroDAO.delete(libro.getId());
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
				System.out.printf("%-20s%-20s%-20s%-20s%-20s\n", "id",  "ISBN", "title", "author", "Genre");
				for (Libro l : libros) {
					System.out.printf("%-20s%-20s%-20s%-20s%-20s\n", l.getId(),  l.getISBN(), l.getTitulo(), l.getAutor(), l.getGenero());
					//System.out.println(l.getId() + " | " + l.getISBN() + " | " + l.getTitulo() + " | " + l.getAutor() + " | " + l.getGenero() );
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			break;
			
		case "E":
			loopList = false;
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
		System.out.println("-----------------------------------------------------------------------------------\n");
		System.out.println("Search by: \n");
		System.out.println("[1] id [Default]");
		System.out.println("[2] ISBN");
		System.out.println("[3] title");
		System.out.println("[4] author");
		System.out.println("[5] genre");
		System.out.println("-----------------------------------------------------------------------------------\n");
		
		System.out.println("Selection: \n");
		String field = scanner.nextLine().trim().toUpperCase();
		String criteria;
		List<Libro> libros = null;
		
		switch(field){
		
		case "1":
			System.out.println("id: ");
			criteria = scanner.nextLine().trim();
			libros = libroDAO.search("id", criteria);
			break;
			
		case "2":
			System.out.println("ISBN: ");
			criteria = scanner.nextLine().trim();
			libros =  libroDAO.search("ISBN", criteria);
			break;
			
		case "3":
			System.out.println("titulo: ");
			criteria = scanner.nextLine().trim();
			libros =  libroDAO.search("titulo", criteria);
			break;
			
		case "4":
			System.out.println("autor: ");
			criteria = scanner.nextLine().trim();
			libros =  libroDAO.search("autor",criteria);
			break;
		
		case "5":
			System.out.println("genero: ");
			criteria = scanner.nextLine().trim();
			libros =  libroDAO.search("genero",criteria);
			break;

			
		default:
			System.out.println("id: ");
			criteria = scanner.nextLine().trim();
			libros = libroDAO.search("id", criteria);
			break;
			}
		System.out.println("Search results:");
		System.out.printf("%-20s%-20s%-20s%-20s%-20s\n", "id",  "ISBN", "title", "author", "Genre");
		//System.out.println("id" + " | " + "ISBN" + " | " + "Titulo" + " | " + "Autor" + " | " + "Genero" );
		
		for (Libro l : libros) { 
			System.out.printf("%-20s%-20s%-20s%-20s%-20s\n", l.getId(),  l.getISBN(), l.getTitulo(), l.getAutor(), l.getGenero());
			//System.out.println(l.getId() + " | " + l.getISBN() + " | " + l.getTitulo() + " | " + l.getAutor() + " | " + l.getGenero() );
		}
		
		} catch (Exception exception) {
				System.out.println("Error");
				exception.printStackTrace();
			}
	}
		
		
	}
	


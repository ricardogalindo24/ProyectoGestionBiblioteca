package com.autozone.utils;


import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.autozone.annotations.NotNull;
import com.autozone.annotations.ValidAction;
import com.autozone.dao.PrestamoDAO;
import com.autozone.interfaces.DatabaseManager;
import com.autozone.models.Prestamo;

public class BookLoanManager implements DatabaseManager<Prestamo, PrestamoDAO> {
	
	@NotNull
	@ValidAction
	String action;
	//variable para determinar si se desean agregar mas libros a la lista de trabajo
	Boolean loopList;
	
	Scanner scanner;
	List<Prestamo> prestamos = new ArrayList<>();
	PrestamoDAO prestamoDAO = new PrestamoDAO();
	
	public PrestamoDAO getPrestamoDAO() {
		return prestamoDAO;
	}



	public List<Prestamo> getPrestamos() {
		return prestamos;
	}

	


	
	public Scanner getScanner() {
		return scanner;
	}



	public void setScanner(Scanner scanner) {
		this.scanner = scanner;
	}



	@Override
	public void setLoopList(Boolean loopList) {
		this.loopList = loopList;
	}
	
	
	
	public Boolean getLoopList() {
			return loopList;}
	
	
	@Override
	public String getAction() {
		return action;
	}
	public void setAction(Scanner scanner) throws IllegalArgumentException, IllegalAccessException{

		
		
		try{ 
		
		//info
		System.out.println("Type A to register a loan, D to delete a loan, U to update an existing loan, S to search, I to display loan list. Then press enter to continue.");
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
	public Prestamo build(Scanner scanner) throws IllegalArgumentException, IllegalAccessException{
		

		String nombre = null;
		Boolean disponible = null;
		String ISBN = null;
		java.sql.Date fecha_prestamo = null;
		java.sql.Date fecha_devolucion = null;
		Integer id_prestamo = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");  

		
		try{ 
		if (action.equals("D")) {
			
			System.out.print("id_prestamo: ");
			id_prestamo = Integer.valueOf(scanner.next().trim());
			nombre = "";
			disponible = false;
			ISBN = "";
		} else {
			System.out.print("ISBN: ");
			ISBN = scanner.next().trim().toUpperCase();
		
			System.out.print("nombre: ");
			nombre = scanner.next().trim();
		
			System.out.print("disponible (true/false): ");
			disponible = Boolean.valueOf(scanner.next().trim());
		
			System.out.print("fecha_prestamo(dd/mm/yyyy): ");
			fecha_prestamo= new java.sql.Date(sdf.parse(scanner.next().trim()).getTime());
			
			System.out.print("fecha_devolucion(dd/mm/yyyy): ");
			fecha_devolucion= new java.sql.Date(sdf.parse(scanner.next().trim()).getTime());
			
		
		}
		} catch (Exception exception) {
			System.out.println("Error");
			exception.printStackTrace();
		}
		Prestamo prestamo = new Prestamo(nombre, disponible, ISBN, fecha_prestamo, fecha_devolucion, id_prestamo);
		Validator.validate(prestamo);
		return prestamo;
	}
	public void addtoBatchList(List<Prestamo> prestamos, Prestamo prestamo, Scanner scanner) {

		
		try{ 
			
			switch(action){
			
			case "D":
				System.out.println("Delete book with id_prestamo: "+ prestamo.getId_prestamo()  + " ------ Add to batch <y/n> [Default: No]");
				break;
			
			case "A":
				System.out.println("Add " + prestamo.getNombre() + " | " + prestamo.getISBN() + " | " + prestamo.getDisponible() + " | " + prestamo.getFecha_prestamo() + " | "  + prestamo.getFecha_prestamo() + " | "  + " ------ Add to batch <y/n> [Default: No]");
				break;
			case "U":
				System.out.println("Update " + prestamo.getId_prestamo() + " | " + prestamo.getNombre() + " | " + prestamo.getISBN() + " | " + prestamo.getDisponible() + " | " + prestamo.getFecha_prestamo() + " | "  + prestamo.getFecha_prestamo() + " | "  + " ------ Add to batch <y/n> [Default: No]");
				break;
			}
			
			String option = scanner.next().trim();
				
			if(option.toUpperCase().equals("Y")) {
				prestamos.add(prestamo);
				System.out.println("Added to batch. ");
		
				} else { System.out.println("Not added to batch. "); }
		
		} catch (Exception exception) {
			System.out.println("Error");
			exception.printStackTrace();
		}
	}
	public void executeAction(PrestamoDAO prestamoDAO, Prestamo prestamo, String action) {
		try {
			switch(action){
			
			case "A":
				prestamoDAO.add(prestamo);
				break;
				
			case "D":
				prestamoDAO.delete(prestamo.getId_prestamo());
				break;
				
			case "U":
				prestamoDAO.update(prestamo);
				break;
				
			case "S":
			    
				searchQueryBuilder(prestamoDAO, scanner);
				
				break;
				
			case "I":
				try {
					prestamos = prestamoDAO.fetchRecords();
					System.out.println("Inventory:");
					System.out.println("id_prestamo" + " | " + "nombre" + " | " + "ISBN" + " | " + "disponible" + "fecha_prestamo" + " | " + "fecha_devolucion" );
					for (Prestamo p : prestamos) {
						System.out.println(p.getId_prestamo() + " | " + p.getNombre() + " | " + p.getISBN() + " | " + p.getDisponible() + " | " + p.getFecha_prestamo() + " | "  + p.getFecha_prestamo() );
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				break;
				
				
			default:
				System.out.println(action + " Acción Inválida o no Definida");
				break;
				}
			
			} catch (Exception exception) {
					System.out.println("Error");
					exception.printStackTrace();
				}
	}
	public void searchQueryBuilder(PrestamoDAO prestamoDAO, Scanner scanner) {
		

		
		try {
		System.out.println("Search by (1) id_prestamo [Default], (2) nombre del miembro, (3) ISBN, or (4) disponibilidad");
		String field = scanner.next().trim().toUpperCase();
		String criteria;
		List<Prestamo> prestamos = null;
		
		switch(field){
		
		case "1":
			System.out.print("id_prestamo: ");
			criteria = scanner.next().trim();
			prestamos = prestamoDAO.search("id_prestamo", criteria);
			break;
			
		case "2":
			System.out.print("nombre: ");
			criteria = scanner.next().trim();
			prestamos =  prestamoDAO.search("nombre", criteria);
			break;
			
		case "3":
			System.out.print("ISBN: ");
			criteria = scanner.next().trim();
			prestamos =  prestamoDAO.search("ISBN", criteria);
			break;
			
		case "4":
			System.out.print("disponible?: ");
			criteria = scanner.next().trim();
			prestamos =  prestamoDAO.search("disponible",criteria);
			break;
			
	
		default:
			System.out.print("id_prestamo: ");
			criteria = scanner.next().trim();
			prestamos = prestamoDAO.search("id_prestamo", criteria);
			break;
			}
		System.out.println("Search results:");
		System.out.println("id_prestamo" + " | " + "nombre" + " | " + "ISBN" + " | " + "disponible" + " | " + "fecha_prestamo" + " | " + "fecha_devolucion" );
		
		for (Prestamo p : prestamos) { 
			
			System.out.println(p.getId_prestamo() + " | " + p.getNombre() + " | " + p.getISBN() + " | " + p.getDisponible() + " | " + p.getFecha_prestamo() + " | "  + p.getFecha_prestamo() );
		}
		
		} catch (Exception exception) {
				System.out.println("Error");
				exception.printStackTrace();
			}
	}

}

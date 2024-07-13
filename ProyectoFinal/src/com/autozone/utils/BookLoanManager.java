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

		
		System.out.println("-----------------------------------------------------------------------------------");
		System.out.println("Please select an option then press enter to continue: \n"); 
		System.out.println("[A] to register a loan,");
		System.out.println("[R] to register a return,");
		System.out.println("[D] to delete a loan,"); 
		System.out.println("[U] to modify an existing loan,"); 
		System.out.println("[S] to search,");
		System.out.println("[I] to display loan list,"); 
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
	public Prestamo build(Scanner scanner) throws IllegalArgumentException, IllegalAccessException{
		

		Integer id_miembro = null;
		Boolean disponible = null;
		String ISBN = null;
		java.sql.Date fecha_prestamo = null;
		java.sql.Date fecha_devolucion = null;
		Integer id_prestamo = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");  

		
		try{
		
		switch(action) {
		case "D":
			
			System.out.println("id_prestamo: ");
			id_prestamo = Integer.valueOf(scanner.nextLine().trim());
			id_miembro = 0;
			disponible = false;
			ISBN = "";
			break;
			
		case "A":
			
			System.out.println("id_prestamo: ");
			id_prestamo = Integer.valueOf(scanner.nextLine().trim());
			
			System.out.println("ISBN: ");
			ISBN = scanner.nextLine().trim().toUpperCase();
		
			System.out.println("id_miembro: ");
			id_miembro = Integer.valueOf(scanner.nextLine().trim());
		
			disponible = false;
			fecha_prestamo=  java.sql.Date.valueOf(prestamoDAO.getLocalDate());
			fecha_devolucion= null;
			break;
			
		case "U":
			
			System.out.println("id_prestamo: ");
			id_prestamo = Integer.valueOf(scanner.nextLine().trim());
			
			System.out.println("ISBN: ");
			ISBN = scanner.nextLine().trim().toUpperCase();
		
			System.out.println("id_miembro: ");
			id_miembro = Integer.valueOf(scanner.nextLine().trim());
		
			System.out.println("disponible (true/false): ");
			disponible = Boolean.valueOf(scanner.nextLine().trim());
		
			System.out.println("fecha_prestamo(dd/mm/yyyy): ");
			fecha_prestamo= new java.sql.Date(sdf.parse(scanner.nextLine().trim()).getTime());
			
			System.out.println("fecha_devolucion(dd/mm/yyyy): ");
			fecha_devolucion= new java.sql.Date(sdf.parse(scanner.nextLine().trim()).getTime());
			break;
			
		case "R":
			
			System.out.println("id_prestamo: ");
			id_prestamo = Integer.valueOf(scanner.nextLine().trim());
			id_miembro = 0;
			disponible = true;
			ISBN = "";
			fecha_devolucion= java.sql.Date.valueOf(prestamoDAO.getLocalDate());
			break;
			
		default:
		
			System.out.println("id_prestamo: ");
			id_prestamo = Integer.valueOf(scanner.nextLine().trim());
			
			System.out.println("ISBN: ");
			ISBN = scanner.nextLine().trim().toUpperCase();
		
			System.out.println("id_miembro: ");
			id_miembro = Integer.valueOf(scanner.nextLine().trim());
		
			System.out.println("disponible (true/false): ");
			disponible = Boolean.valueOf(scanner.nextLine().trim());
		
			System.out.println("fecha_prestamo(dd/mm/yyyy): ");
			fecha_prestamo= new java.sql.Date(sdf.parse(scanner.nextLine().trim()).getTime());
			
			System.out.println("fecha_devolucion(dd/mm/yyyy): ");
			fecha_devolucion= new java.sql.Date(sdf.parse(scanner.nextLine().trim()).getTime());
			break;
		
		
		}} catch (Exception exception) {
			System.out.println("Error");
			exception.printStackTrace();
		}
		Prestamo prestamo = new Prestamo(id_miembro, disponible, ISBN, fecha_prestamo, fecha_devolucion, id_prestamo);
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
				System.out.printf("%-20s%-20s%-20s%-20s%-20s%-20s%-20s\n", "Loan id",  "Member id", "ISBN", "Availability", "Loan date", "Return date", "Action");
				System.out.printf("%-20s%-20s%-20s%-20s%-20s%-20s%-20s%-20s\n", prestamo.getId_prestamo(),  prestamo.getId_miembro(), prestamo.getISBN(), prestamo.getDisponible(), prestamo.getFecha_prestamo(), prestamo.getFecha_devolucion(), "Add", "------ Add to batch <y/n> [Default: No]");
				//System.out.println("Add " + prestamo.getId_prestamo() + " | " + prestamo.getId_miembro() + " | " + prestamo.getISBN() + " | " + prestamo.getDisponible() + " | " + prestamo.getFecha_prestamo() + " | "  + prestamo.getFecha_devolucion() + " | "  + " ------ Add to batch <y/n> [Default: No]");
				break;
			case "U":
				System.out.printf("%-20s%-20s%-20s%-20s%-20s%-20s%-20s\n", "Loan id",  "Member id", "ISBN", "Availability", "Loan date", "Return date", "Action");
				System.out.printf("%-20s%-20s%-20s%-20s%-20s%-20s%-20s%-20s\n", prestamo.getId_prestamo(),  prestamo.getId_miembro(), prestamo.getISBN(), prestamo.getDisponible(), prestamo.getFecha_prestamo(), prestamo.getFecha_devolucion(), "Update", "------ Add to batch <y/n> [Default: No]");
				//System.out.println("Update " + prestamo.getId_prestamo() + " | " + prestamo.getId_miembro() + " | " + prestamo.getISBN() + " | " + prestamo.getDisponible() + " | " + prestamo.getFecha_prestamo() + " | "  + prestamo.getFecha_devolucion() + " | "  + " ------ Add to batch <y/n> [Default: No]");
				break;
				
			case "R":
				System.out.printf("%-20s%-20s%-20s%-20s\n", "Loan id", "Availability", "Return date",  "Action");
				System.out.printf("%-20s%-20s%-20s%-20s%-20s\n", prestamo.getId_prestamo(), prestamo.getDisponible(), prestamo.getFecha_devolucion(), "Register return", "------ Add to batch <y/n> [Default: No]");
				//System.out.println("Register return for id_prestamo: " + prestamo.getId_prestamo()   + " ------ Add to batch <y/n> [Default: No]");
				break;
			}
			
			String option = scanner.nextLine().trim();
				
			if(option.toUpperCase().equals("Y")) {
				prestamos.add(prestamo);
				System.out.println("☑ Added to batch. ");
		
				} else { System.out.println("❌ Not added to batch. "); }
		
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
				
			case "R":
				prestamoDAO.registerReturn(prestamo);
				break;
				
			case "S":
			    
				searchQueryBuilder(prestamoDAO, scanner);
				
				break;
				
			case "I":
				try {
					prestamos = prestamoDAO.fetchRecords();
					System.out.println("Inventory:");
					System.out.printf("%-20s%-20s%-20s%-20s%-20s%-20s\n", "Loan id",  "Member id", "ISBN", "Availability", "Loan date", "Return date");
					//System.out.println("id_prestamo" + " | " + "Id_miembro" + " | " + "ISBN" + " | " + "disponible" + "fecha_prestamo" + " | " + "fecha_devolucion" );
					for (Prestamo p : prestamos) {
						System.out.printf("%-20s%-20s%-20s%-20s%-20s%-20s\n", p.getId_prestamo(), p.getId_miembro(), p.getISBN(), p.getDisponible(), p.getFecha_prestamo(), p.getFecha_devolucion() );
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				break;
				
			case "E":
				loopList = false;
				break;
				
				
			default:
				System.out.println("Not a valid option! /n ");
				action = "E";
				loopList = false;
				break;
				}
			
			} catch (Exception exception) {
					System.out.println("Error");
					exception.printStackTrace();
				}
	}
	public void searchQueryBuilder(PrestamoDAO prestamoDAO, Scanner scanner) {
		

		
		try {
		
		System.out.println("-----------------------------------------------------------------------------------\n");
		System.out.println("Search by: \n");
		System.out.println("[1] id_prestamo [Default]");
		System.out.println("[2] Member id");
		System.out.println("[3] ISBN");
		System.out.println("[4] availability");
		System.out.println("-----------------------------------------------------------------------------------\n");
		
		System.out.println("Selection: \n");
		
		
		String field = scanner.nextLine().trim().toUpperCase();
		String criteria;
		List<Prestamo> prestamos = null;
		
		switch(field){
		
		case "1":
			System.out.println("id_prestamo: ");
			criteria = scanner.nextLine().trim();
			prestamos = prestamoDAO.search("id_prestamo", criteria);
			break;
			
		case "2":
			System.out.println("id_miembro: ");
			criteria = scanner.nextLine().trim();
			prestamos =  prestamoDAO.search("id_miembro", criteria);
			break;
			
		case "3":
			System.out.println("ISBN: ");
			criteria = scanner.nextLine().trim();
			prestamos =  prestamoDAO.search("ISBN", criteria);
			break;
			
		case "4":
			System.out.println("disponible?: ");
			criteria = scanner.nextLine().trim();
			prestamos =  prestamoDAO.search("disponible",criteria);
			break;
			
			
	
		default:
			System.out.println("id_prestamo: ");
			criteria = scanner.nextLine().trim();
			prestamos = prestamoDAO.search("id_prestamo", criteria);
			break;
			}
		System.out.println("Search results:");
		System.out.printf("%-20s%-20s%-20s%-20s%-20s%-20s\n", "Loan id",  "Member id", "ISBN", "Availability", "Loan date", "Return date");
		
		for (Prestamo p : prestamos) { 
			
			System.out.printf("%-20s%-20s%-20s%-20s%-20s%-20s\n", p.getId_prestamo(), p.getId_miembro(), p.getISBN(), p.getDisponible(), p.getFecha_prestamo(), p.getFecha_devolucion() );
		}
		
		} catch (Exception exception) {
				System.out.println("Error");
				exception.printStackTrace();
			}
	}

}

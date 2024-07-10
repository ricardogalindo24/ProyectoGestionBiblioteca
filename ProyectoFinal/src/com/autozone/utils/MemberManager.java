package com.autozone.utils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.autozone.annotations.NotNull;
import com.autozone.annotations.ValidAction;
import com.autozone.dao.MiembroDAO;
import com.autozone.interfaces.DatabaseManager;
import com.autozone.models.Miembro;


public class MemberManager implements DatabaseManager<Miembro, MiembroDAO> {
	
	@NotNull
	@ValidAction
	String action;
	//variable para determinar si se desean agregar mas libros a la lista de trabajo
	Boolean loopList;
	
	Scanner scanner;
	List<Miembro> miembros = new ArrayList<>();
	MiembroDAO miembroDAO = new MiembroDAO();
	
	
	public MiembroDAO getMiembroDAO() {
		return miembroDAO;
	}



	public List<Miembro> getmiembros() {
		return miembros;
	}

	
	


	public Scanner getScanner() {
		return scanner;
	}



	public void setScanner(Scanner scanner) {
		this.scanner = scanner;
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
	

	public void setAction(Scanner scanner) throws IllegalArgumentException, IllegalAccessException{

		
		
		try{ 
		
		//info

		System.out.println("Type A to add members, D to delete a member, U to update an existing member, S to search, I to display members. Then press enter to continue.");
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
	public Miembro build(Scanner scanner) throws IllegalArgumentException, IllegalAccessException{

		String nombre = null;
		Integer id_miembro = null;
	
		try{ 
		if (action.equals("D")) {
			
			System.out.print("id_miembro: ");
			id_miembro = Integer.valueOf(scanner.next().trim());
			nombre = "Deleted";
		} else {
			System.out.print("id_miembro: ");
			id_miembro = Integer.valueOf(scanner.next().trim());
		
			System.out.println("Nombre:");
			nombre = scanner.next().toUpperCase();
			
		
			
		
		}
		} catch (Exception exception) {
			System.out.println("Error");
			exception.printStackTrace();
		}
		Miembro miembro = new Miembro(nombre,id_miembro);
		Validator.validate(miembro);
		return miembro;
		
	}
	public void addtoBatchList(List<Miembro> miembros, Miembro miembro, Scanner scanner) {

		
		try{ 
			
			switch(action){
			
			case "D":
				System.out.println("Delete book with id_miembro: "+ miembro.getId_miembro()  + "  -----  Add to batch <y/n> [Default: No]");
				break;
			
			case "A":
				System.out.println("Add " +  miembro.getNombre() + " Add to batch <y/n> [Default: No]");
				break;
			case "U":
				System.out.println("Update " + miembro.getId_miembro() + " | " + miembro.getNombre() + " | "  + "  -----  Add to batch <y/n> [Default: No]");
				break;
			}
			
			String option = scanner.next().trim();
				
			if(option.toUpperCase().equals("Y")) {
				miembros.add(miembro);
				System.out.println("Added to batch. ");
		
				} else { System.out.println("Not added to batch. "); }
		
		} catch (Exception exception) {
			System.out.println("Error");
			exception.printStackTrace();
		}
	}
	
	public void executeAction( MiembroDAO miembroDAO, Miembro miembro, String action) {

		try {
			switch(action){
			
			case "A":
				miembroDAO.add(miembro);
				break;
				
			case "D":
				miembroDAO.delete(miembro.getId_miembro());
				break;
				
			case "U":
				miembroDAO.update(miembro);
				break;
				
			case "S":
			    
				searchQueryBuilder(miembroDAO, scanner);
				
				break;
				
			case "I":
				try {
					miembros = miembroDAO.fetchRecords();
					System.out.println("Inventory:");
					System.out.println("id_miembro" + " | " + "nombre"  );
					for (Miembro m : miembros) {
						System.out.println(m.getId_miembro() + " | " + m.getNombre()  );
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
	public void searchQueryBuilder(MiembroDAO miembroDAO, Scanner scanner) {

		
		try {
		System.out.println("Search by (1) id_miembro [Default] or (2) nombre del miembro.");
		String field = scanner.next().trim().toUpperCase();
		String criteria;
		List<Miembro> miembros = null;
		
		switch(field){
		
		case "1":
			System.out.print("id_miembro: ");
			criteria = scanner.next().trim();
			miembros = miembroDAO.search("id_miembro", criteria);
			break;
			
		case "2":
			System.out.print("nombre: ");
			criteria = scanner.next().trim();
			miembros =  miembroDAO.search("nombre", criteria);
			break;
			
	
		default:
			System.out.print("id_miembro: ");
			criteria = scanner.next().trim();
			miembros = miembroDAO.search("id_miembro", criteria);
			break;
			}
		System.out.println("Search results:");
		System.out.println("id_miembro" + " | " + "nombre" );
		
		for (Miembro m : miembros) { 
			
			System.out.println(m.getId_miembro() + " | " + m.getNombre()  );
		}
		
		} catch (Exception exception) {
				System.out.println("Error");
				exception.printStackTrace();
			}
	}

}

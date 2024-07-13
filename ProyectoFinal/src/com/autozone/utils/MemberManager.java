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

		
		System.out.println("-----------------------------------------------------------------------------------");
		System.out.println("Please select an option then press enter to continue: \n"); 
		System.out.println("[A] to add members,"); 
		System.out.println("[D] to delete a member,"); 
		System.out.println("[U] to update an existing member,"); 
		System.out.println("[S] to search,");
		System.out.println("[I] to display all members,"); 
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
	public Miembro build(Scanner scanner) throws IllegalArgumentException, IllegalAccessException{

		String nombre = null;
		Integer id_miembro = null;
	
		try{ 
		if (action.equals("D")) {
			
			System.out.println("id_miembro: ");
			id_miembro = Integer.valueOf(scanner.nextLine().trim());
			nombre = "Deleted";
		} else {
			System.out.println("id_miembro: ");
			id_miembro = Integer.valueOf(scanner.nextLine().trim());
		
			System.out.println("Nombre:");
			nombre = scanner.nextLine().toUpperCase();
			
		
			
		
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
				System.out.printf("%-20s%-20s%-20s\n","Member id", "Name", "Action");
				System.out.printf("%-20s%-20s%-20s%-20s\n",miembro.getId_miembro() , miembro.getNombre(), "Add", "----- Add to batch <y/n> [Default: No]");
				break;
			case "U":
				System.out.printf("%-20s%-20s%-20s\n","Member id", "Name", "Action");
				System.out.printf("%-20s%-20s%-20s%-20s\n",miembro.getId_miembro() , miembro.getNombre(), "Update", "----- Add to batch <y/n> [Default: No]");
				break;
			}
			
			String option = scanner.nextLine().trim();
				
			if(option.toUpperCase().equals("Y")) {
				miembros.add(miembro);
				System.out.println("☑ Added to batch. ");
		
				} else { System.out.println("❌ Not added to batch. "); }
		
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
					System.out.printf("%-20s%-20s\n", "Member id",  "Name");
					for (Miembro m : miembros) {
						System.out.printf("%-20s%-20s\n",m.getId_miembro(), m.getNombre());
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				break;
				
			case "E":
				loopList = false;
				break;
				
			default:
				System.out.println("Not a valid option! \n  ");
				action = "E";
				loopList = false;
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
		
		System.out.println("-----------------------------------------------------------------------------------\n");
		System.out.println("Search by: \n");
		System.out.println("[1] id_miembro [Default]");
		System.out.println("[2] Member's name");
		System.out.println("-----------------------------------------------------------------------------------\n");
		
		System.out.println("Selection: \n");
		
		
		String field = scanner.nextLine().trim().toUpperCase();
		String criteria;
		List<Miembro> miembros = null;
		
		switch(field){
		
		case "1":
			System.out.println("id_miembro: ");
			criteria = scanner.nextLine().trim();
			miembros = miembroDAO.search("id_miembro", criteria);
			break;
			
		case "2":
			System.out.println("nombre: ");
			criteria = scanner.nextLine().trim();
			miembros =  miembroDAO.search("nombre", criteria);
			break;
			
	
		default:
			System.out.println("id_miembro: ");
			criteria = scanner.nextLine().trim();
			miembros = miembroDAO.search("id_miembro", criteria);
			break;
			}
		System.out.println("Search results:");
		System.out.printf("%-20s%-20s\n", "Member id",  "Name");
		
		for (Miembro m : miembros) { 
			
			System.out.printf("%-20s%-20s\n",m.getId_miembro(), m.getNombre());
		}
		
		} catch (Exception exception) {
				System.out.println("Error");
				exception.printStackTrace();
			}
	}

}

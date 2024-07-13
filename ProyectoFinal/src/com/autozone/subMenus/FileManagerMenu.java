package com.autozone.subMenus;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import com.autozone.dao.LibroDAO;
import com.autozone.dao.MiembroDAO;
import com.autozone.dao.PrestamoDAO;
import com.autozone.exceptions.IsClosedException;
import com.autozone.interfaces.Inventory;
import com.autozone.models.Libro;
import com.autozone.models.Miembro;
import com.autozone.models.ObjectAccion;
import com.autozone.models.Prestamo;
import com.autozone.utils.FileManager;
import com.autozone.utils.InputReader;

public class FileManagerMenu extends FileManager implements Inventory {

	@Override
	public void run(Scanner scanner) throws IllegalArgumentException, IllegalAccessException, IsClosedException {
		
		List<String> validOptions = Arrays.asList("B", "M", "L", "E");
		
		System.out.println("-----------------------------------------------------------------------------------");
		System.out.println("Please select an option then press enter to continue: \n" ); 
		
		System.out.println("[B] to upload books from csv file");
		System.out.println("[M] to upload members from csv file");
		System.out.println("[L] to upload loans from csv file");
		System.out.println("[E] to exit. \n");
		System.out.println("-----------------------------------------------------------------------------------\n");
		
		System.out.println("Selection: ");
		
		String selection = scanner.nextLine().trim().toUpperCase();

		Boolean valid = validOptions.contains(selection);
		Object dao;
		List<ObjectAccion> worklist = new ArrayList<>();
		
		
		switch(selection) {
		
		 case "B":
			 System.out.println("Input file path (csv) with columns as follows: ");
			 System.out.printf("%-20s%-20s%-20s%-20s%-20s\n", "id",  "ISBN", "title", "author", "Genre", "Action");
			 BookInventory bookInventory = new BookInventory();
			 setFilename(scanner);
			 worklist = booksFromCSV(getFilename());
			 dao = new LibroDAO();
			 
			 for (ObjectAccion o : worklist) { 
					
				 bookInventory.executeAction((LibroDAO) dao, (Libro) o.getObj(), o.getAction());
				}
			 System.out.println("File processed :) \n \n \n");
				InputReader.getInstance().promptEnterKey(scanner);
			 break;
			 
		 case "M":
			 System.out.println("Input file path (csv) with columns as follows: ");
			 System.out.printf("%-20s%-20s%-20s\n", "id_member",  "name", "action");
			 MemberList memberList = new MemberList();
			 setFilename(scanner);
			 worklist = membersFromCSV(getFilename());
			 dao = new MiembroDAO();
			 for (ObjectAccion o : worklist) { 
					
				 memberList.executeAction((MiembroDAO) dao, (Miembro) o.getObj(), o.getAction());
				}
			 System.out.println("File processed :) \n \n \n");
				InputReader.getInstance().promptEnterKey(scanner);
			 break;
			 
		 case "L":
			 try {
				System.out.println("Input file path (csv) with columns as follows: ");
				System.out.printf("%-20s%-20s%-20s%-20s%-20s%-20s%-20s\n", "id_loan", "id_member", "Availability", "ISBN", "loan_date", "return_date", "Action");
				LoanRecords loanRecords = new LoanRecords();
				setFilename(scanner);
				worklist = loansFromCSV(getFilename());
				dao = (PrestamoDAO) new PrestamoDAO();
				for (ObjectAccion o : worklist) { 
					
					loanRecords.executeAction((PrestamoDAO) dao, (Prestamo) o.getObj(), o.getAction());
					}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 System.out.println("File processed :) \n \n \n");
				InputReader.getInstance().promptEnterKey(scanner);
			 break;
			 
		 case "E":
			break;
		
		 default:
			System.out.println("Not a valid option! \n \n \n");
			InputReader.getInstance().promptEnterKey(scanner);
			break;
		
	}
	}

}

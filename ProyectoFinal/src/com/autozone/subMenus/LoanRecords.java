package com.autozone.subMenus;




import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import com.autozone.exceptions.IsClosedException;
import com.autozone.interfaces.Inventory;
import com.autozone.models.Prestamo;
import com.autozone.utils.BookLoanManager;
import com.autozone.utils.InputReader;



public class LoanRecords extends BookLoanManager implements Inventory{
	
	
		
	public void run(Scanner scanner) throws IllegalArgumentException, IllegalAccessException, IsClosedException {
	
		
		setScanner(scanner);
		setAction(getScanner());
		
		List<String> validOptions = Arrays.asList("A", "U", "D", "S", "I", "R");
		Boolean valid = validOptions.contains(getAction());
		
	
		if(getLoopList() == true && valid) {
		while (getLoopList() == true) {
			Prestamo prestamo = build(getScanner());
			addtoBatchList(getPrestamos(),prestamo,getScanner());
			System.out.println("Add another record to batch?  <y/n> [Default: No]:");
			String option = getScanner().nextLine().trim().toUpperCase();
		
			if (option.equals("Y")) {
			
				setLoopList(true);

			} else {setLoopList(false);
			//getScanner().close();
			}
		}
		
		System.out.println("Current Batch:");
		System.out.printf("%-20s%-20s%-20s%-20s%-20s%-20s%-20s\n", "Loan id",  "Member id", "ISBN", "availability", "Loan date", "Return date","INFO");
		for (Prestamo p : getPrestamos()) {
			System.out.printf("%-20s%-20s%-20s%-20s%-20s%-20s%-20s\n", "Pending",  p.getId_miembro(), p.getISBN(), p.getDisponible(), p.getFecha_prestamo(), p.getFecha_devolucion(), getAction());
		}
		System.out.println("Do you wish to execute this batch? <y/n> [Default: No]");
		String execute = getScanner().nextLine().trim().toUpperCase();
		
		switch(execute) {
			
		case "Y":
			System.out.printf("%-20s%-20s%-20s%-20s%-20s%-20s%-20s\n", "Loan id",  "Member id", "ISBN", "availability", "Loan date", "Return date","INFO");
			for (Prestamo p : getPrestamos()) { 
			
				executeAction(getPrestamoDAO(), p, getAction());
		}
			break;
			
		
		}} else {executeAction(getPrestamoDAO(), null, getAction());
		}
		
		if(!getAction().equals("E")) {
			InputReader.getInstance().promptEnterKey(getScanner());
			
			}}
			
		}
		
		
	
	








 
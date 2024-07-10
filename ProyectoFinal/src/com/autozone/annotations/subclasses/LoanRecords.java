package com.autozone.annotations.subclasses;




import java.util.Scanner;

import com.autozone.exceptions.IsClosedException;
import com.autozone.interfaces.Inventory;
import com.autozone.models.Prestamo;
import com.autozone.utils.BookLoanManager;



public class LoanRecords extends BookLoanManager implements Inventory{
		
	public void run(Scanner scanner) throws IllegalArgumentException, IllegalAccessException, IsClosedException {
	
		
		setScanner(scanner);
		setAction(getScanner());
		
	
		if(getLoopList() == true) {
		while (getLoopList() == true) {
			Prestamo prestamo = build(getScanner());
			addtoBatchList(getPrestamos(),prestamo,getScanner());
			System.out.println("Add another record to batch?  <y/n> [Default: No]:");
			String option = getScanner().next().trim().toUpperCase();
		
			if (option.equals("Y")) {
			
				setLoopList(true);

			} else {setLoopList(false);
			//getScanner().close();
			}
		}
		
		for (Prestamo p : getPrestamos()) { 
			
			executeAction(getPrestamoDAO(), p, getAction());
		}} else {executeAction(getPrestamoDAO(), null, getAction());
		}
		
			
		}
		
		
		}
	
	








 
package com.autozone.annotations.subclasses;



import java.util.Scanner;

import com.autozone.exceptions.IsClosedException;
import com.autozone.interfaces.Inventory;
import com.autozone.models.Libro;
import com.autozone.utils.BookInventoryManager;




public class BookInventory extends BookInventoryManager implements Inventory{
		
	public void run(Scanner scanner) throws IllegalArgumentException, IllegalAccessException, IsClosedException {
	
		setScanner(scanner);
		setAction(getScanner());
	
		if(getLoopList() == true) {
		while (getLoopList() == true) {
			Libro libro = build(getScanner());
			addtoBatchList(getLibros(),libro,getScanner());
			System.out.println("Add another record to batch?  <y/n> [Default: No]:");
			String option = getScanner().next().trim().toUpperCase();
		
			if (option.equals("Y")) {
			
				setLoopList(true);
			
			} else {setLoopList(false);
			//getScanner().close();
			}
		}
		
		for (Libro l : getLibros()) { 
			
			executeAction(getLibroDAO(), l, getAction());
		}} else {executeAction(getLibroDAO(), null, getAction());
		}
		
			
		}
		
		
		}
	
	








 
package com.autozone.subMenus;



import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import com.autozone.exceptions.IsClosedException;
import com.autozone.interfaces.Inventory;
import com.autozone.models.Libro;
import com.autozone.utils.BookInventoryManager;
import com.autozone.utils.InputReader;



public class BookInventory extends BookInventoryManager implements Inventory{
	
	
	
	public void run(Scanner scanner) throws IllegalArgumentException, IllegalAccessException, IsClosedException {
	
		setScanner(scanner);
		setAction(getScanner());
		
		List<String> validOptions = Arrays.asList("A", "U", "D", "S", "I");
		Boolean valid = validOptions.contains(getAction());
		
		if(getLoopList() == true && valid) {
		while (getLoopList() == true) {
			Libro libro = build(getScanner());
			addtoBatchList(getLibros(),libro,getScanner());
			System.out.println("Add another record to batch?  <y/n> [Default: No]:");
			String option = getScanner().nextLine().trim().toUpperCase();
		
			if (option.equals("Y")) {
			
				setLoopList(true);
			
			} else {setLoopList(false);
			//getScanner().close();
			}
		}
		System.out.printf("%-20s%-20s%-20s%-20s%-20s%-20s\n", "id",  "ISBN", "Title", "Author", "Genre","INFO");
		for (Libro l : getLibros()) { 
			
			executeAction(getLibroDAO(), l, getAction());
		}} else {executeAction(getLibroDAO(), null, getAction());
		}
		
		if(!getAction().equals("E")) {
		InputReader.getInstance().promptEnterKey(getScanner());
		
									}
					}
	}
	
	








 
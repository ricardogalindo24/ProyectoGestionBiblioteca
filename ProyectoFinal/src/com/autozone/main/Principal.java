package com.autozone.main;

import java.util.Scanner;

import com.autozone.annotations.subclasses.BookInventory;
import com.autozone.annotations.subclasses.LoanRecords;
import com.autozone.annotations.subclasses.MemberList;
import com.autozone.exceptions.IsClosedException;
import com.autozone.utils.InputReader;

public class Principal {
		
	public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException, IsClosedException {
		
	
		
		InputReader inputReader = InputReader.getInstance();
		Scanner scanner = inputReader.getScanner();
		Boolean loop = true;
				
		System.out.println(" _________________________________________________________");
		System.out.println("|                                                         |");
		System.out.println("|                   LibraryManager v0.2                   |");
		System.out.println("|                     Ricardo Galindo                     |");
		System.out.println("|                     Gear Up for IT                      |");
		System.out.println("|_________________________________________________________| \n \n \n");
		while (loop == true) {
		
		System.out.println("-----------------------------------------------------------------------------------");
		System.out.println("Please select an option then press enter to continue: \n" ); 
		
		System.out.println("[B] to manage book inventory");
		System.out.println("[M] to manage members");
		System.out.println("[L] to manage loans");
		System.out.println("[E] to exit. \n");
		System.out.println("-----------------------------------------------------------------------------------\n");
		
		System.out.println("Selection: ");
		
		String selection = scanner.nextLine().trim().toUpperCase();
		
		
			
		switch(selection) {
		
		 case "B":
			 BookInventory bookInventory = new BookInventory();
			 bookInventory.run(scanner);
			 break;
			 
		 case "M":
			MemberList memberList = new MemberList();
			memberList.run(scanner);
			 break;
			 
		 case "L":
		 
			LoanRecords loanRecords = new LoanRecords();
			loanRecords.run(scanner);
			 break;
			 
		 case "E":
			 
			loop = false;
			break;
		
		 default:
			System.out.println("Not a valid option! \n \n \n");
			InputReader.getInstance().promptEnterKey(scanner);
		
			 
		}}
		scanner.close();
		System.out.println("Exiting... Good Bye!");
		
		}
	}
	









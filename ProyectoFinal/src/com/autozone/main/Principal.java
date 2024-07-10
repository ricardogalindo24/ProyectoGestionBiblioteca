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
				
		
		while (loop == true) {
		System.out.println("LibraryManager v0.1");
		System.out.println("Do you want to (1) manage book inventory, (2) manage members, (3) manage loans? Any other key to exit.");
		System.out.print("Type selection:");
		
		String selection = scanner.next().trim();
		
		
			
		switch(selection) {
		
		 case "1":
			 BookInventory bookInventory = new BookInventory();
			 bookInventory.run(scanner);
			 break;
			 
		 case "2":
			MemberList memberList = new MemberList();
			memberList.run(scanner);
			 break;
			 
		 case "3":
		 
			LoanRecords loanRecords = new LoanRecords();
			loanRecords.run(scanner);
			 break;
		
		default:
			loop = false;
		
			 
		}}
		scanner.close();
		System.out.println("Exiting... Good Bye!");
		
		}
	}
	









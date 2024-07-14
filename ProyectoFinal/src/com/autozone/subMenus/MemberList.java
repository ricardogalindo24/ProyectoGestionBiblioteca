package com.autozone.subMenus;


import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import com.autozone.exceptions.IsClosedException;
import com.autozone.interfaces.Inventory;
import com.autozone.models.Miembro;

import com.autozone.utils.InputReader;
import com.autozone.utils.MemberManager;



public class MemberList extends MemberManager implements Inventory{
		
	public void run(Scanner scanner) throws IllegalArgumentException, IllegalAccessException, IsClosedException {
	
		
		
		setScanner(scanner);
		setAction(getScanner());

		List<String> validOptions = Arrays.asList("A", "U", "D", "S", "I");
		Boolean valid = validOptions.contains(getAction());
		
		if(getLoopList() == true && valid) {
		while (getLoopList() == true) {
			Miembro miembro = build(getScanner());
			addtoBatchList(getmiembros(),miembro,getScanner());
			System.out.println("Add another record to batch?  <y/n> [Default: No]: ");
			String option = getScanner().nextLine().trim().toUpperCase();
		
			if (option.equals("Y")) {
			
				setLoopList(true);
			
			} else {setLoopList(false);
			//getScanner().close();
			}
			
		}
		System.out.println("Current Batch:");
		System.out.printf("%-20s%-20s%-20s\n", "Member id", "Name","INFO");
		for (Miembro m : getmiembros()) {
			System.out.printf("%-20s%-20s%-20s\n", "Pending",  m.getNombre(), getAction());
		}
		System.out.println("Do you wish to execute this batch? <y/n> [Default: No]");
		String execute = getScanner().nextLine().trim().toUpperCase();
		
		switch (execute) {
		case "Y":
			System.out.printf("%-20s%-20s%-20s\n", "Member id", "Name","INFO");
			for (Miembro m : getmiembros()) { 
			
			executeAction(getMiembroDAO(), m, getAction());
		}
			break;
		}} else {executeAction(getMiembroDAO(), null, getAction());
		}
		
		if(!getAction().equals("E")) {
			InputReader.getInstance().promptEnterKey(getScanner());
			
			}}
			
		}
		
		
	
	








 
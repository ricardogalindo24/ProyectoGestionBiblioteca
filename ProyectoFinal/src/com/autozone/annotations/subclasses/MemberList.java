package com.autozone.annotations.subclasses;


import java.util.Scanner;

import com.autozone.exceptions.IsClosedException;
import com.autozone.interfaces.Inventory;
import com.autozone.models.Miembro;
import com.autozone.utils.MemberManager;



public class MemberList extends MemberManager implements Inventory{
		
	public void run(Scanner scanner) throws IllegalArgumentException, IllegalAccessException, IsClosedException {
	
		
		
		setScanner(scanner);
		setAction(getScanner());
		
	
		if(getLoopList() == true) {
		while (getLoopList() == true) {
			Miembro miembro = build(getScanner());
			addtoBatchList(getmiembros(),miembro,getScanner());
			System.out.println("Add another record to batch?  <y/n> [Default: No]: ");
			String option = getScanner().next().trim().toUpperCase();
		
			if (option.equals("Y")) {
			
				setLoopList(true);
			
			} else {setLoopList(false);
			//getScanner().close();
			}
		}
		
		for (Miembro m : getmiembros()) { 
			
			executeAction(getMiembroDAO(), m, getAction());
		}} else {executeAction(getMiembroDAO(), null, getAction());
		}
		
			
		}
		
		
		}
	
	








 
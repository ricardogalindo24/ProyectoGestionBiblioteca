package com.autozone.utils;

import java.util.Scanner;


public class InputReader {

	private Scanner scanner;
    private static InputReader instance;
    
    private InputReader() {
    	scanner = new Scanner(System.in);
    }
    
    public static  InputReader getInstance() {
        if(instance == null) {
        	instance = new InputReader();
        }
        return instance;
    }
    
    
    public Scanner getScanner() {
		return scanner;
	}





}



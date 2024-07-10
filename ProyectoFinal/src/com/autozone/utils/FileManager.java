package com.autozone.utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.autozone.models.Libro;
import com.autozone.models.LibroAccion;

public class FileManager {

	public void writeToCSV(List<Libro> libros) {
		
		String filepath = "C:\\GearUpforIT\\tests\\";
		String current_date_time = LocalDateTime.now().toString().replace(":", "_");
	
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(filepath + current_date_time + ".csv"))) {
			
			writer.write("ISBN, titulo, autor, genero");
			writer.newLine();
			
			for (Libro libro : libros) {
				writer.write(libro.getISBN() + "," + libro.getTitulo() + "," + libro.getAutor() + "," + libro.getGenero());
				writer.newLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public List<Libro> readFromCSV(String Filename) {
		List<Libro> libros = new ArrayList<>();
		
		try {List<String> lines = Files.readAllLines(Paths.get(Filename));
		
			for(int i = 1; i < lines.size(); i++) {
				String line = lines.get(i);
				String[] fields = line.split(",");
				
				String ISBN = fields[0];
				String titulo = fields[1];
				String autor = fields[2];
				String genero = fields[3];
				
				Libro libro = new Libro(ISBN, titulo, autor, genero);
				libros.add(libro);}
			
			} catch (IOException e) {
				e.printStackTrace();
			}
		
			return libros;
		}
	
	public List<LibroAccion> executeFromCSV(String Filename) {
		List<LibroAccion> librosAccion = new ArrayList<>();
		
		try {List<String> lines = Files.readAllLines(Paths.get(Filename));
		
			for(int i = 1; i < lines.size(); i++) {
				String line = lines.get(i);
				String[] fields = line.split(",");
				
				String ISBN = fields[0];
				String titulo = fields[1];
				String autor = fields[2];
				String genero= fields[3];
				String action = fields[4];
				
				Libro libro = new Libro(ISBN, titulo, autor, genero);
				LibroAccion libroAccion = new LibroAccion(libro, action);
				librosAccion.add(libroAccion);
				}
			
			} catch (IOException e) {
				e.printStackTrace();
			}
		
			return librosAccion;
		}
	}



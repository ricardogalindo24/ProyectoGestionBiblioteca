package com.autozone.utils;

//import java.io.BufferedWriter;
//import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
//import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import com.autozone.models.Libro;
import com.autozone.models.Miembro;
import com.autozone.models.ObjectAccion;
import com.autozone.models.Prestamo;

public class FileManager {
	String filename;
	String filepath = "C:\\GearUpforIT\\tests\\";
	
	/*public void writeToCSV(List<Libro> libros) {
		
		String filepath = "C:\\GearUpforIT\\tests\\";
		String current_date_time = LocalDateTime.now().toString().replace(":", "_");
	
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(filepath + current_date_time + ".csv"))) {
			
			writer.write("id, ISBN, titulo, autor, genero");
			writer.newLine();
			
			for (Libro libro : libros) {
				writer.write(libro.getId() + "," + libro.getISBN() + "," + libro.getTitulo() + "," + libro.getAutor() + "," + libro.getGenero());
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
				
				String id = fields[0];
				String ISBN = fields[1];
				String titulo = fields[2];
				String autor = fields[3];
				String genero = fields[4];
				
				Libro libro = new Libro(Integer.valueOf(id), ISBN, titulo, autor, genero);
				libros.add(libro);}
			
			} catch (IOException e) {
				e.printStackTrace();
			}
		
			return libros;
		}*/
	
	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public List<ObjectAccion> booksFromCSV(String filename) {
		List<ObjectAccion> librosAccion = new ArrayList<>();
		
		try {List<String> lines = Files.readAllLines(Paths.get(filepath+filename));
		
			for(int i = 1; i < lines.size(); i++) {
				String line = lines.get(i);
				String[] fields = line.split(",");
				
				String id = fields[0];
				String ISBN = fields[1];
				String titulo = fields[2];
				String autor = fields[3];
				String genero = fields[4];
				String action = fields[5];
				
				Libro libro = new Libro(Integer.valueOf(id), ISBN, titulo, autor, genero);
				ObjectAccion libroAccion = new ObjectAccion(libro, action);
				librosAccion.add(libroAccion);
				}
			
			} catch (IOException e) {
				e.printStackTrace();
			}
		
			return librosAccion;
		}
	
	public List<ObjectAccion> membersFromCSV(String filename) {
		List<ObjectAccion> miembrosAccion = new ArrayList<>();
		
		try {List<String> lines = Files.readAllLines(Paths.get(filepath+filename));
		
			for(int i = 1; i < lines.size(); i++) {
				String line = lines.get(i);
				String[] fields = line.split(",");
				
				String id_miembro = fields[0];
				String nombre = fields[1];
				String action = fields[2];
				
				Miembro miembro = new Miembro(nombre, Integer.valueOf(id_miembro));
				ObjectAccion miembroAccion = new ObjectAccion(miembro, action);
				miembrosAccion.add(miembroAccion);
				}
			
			} catch (IOException e) {
				e.printStackTrace();
			}
		
			return miembrosAccion;
		}
	
	public List<ObjectAccion> loansFromCSV(String filename) throws ParseException {
		List<ObjectAccion> prestamosAccion = new ArrayList<>();
		
		LocalDate localDate = LocalDate.now( ZoneId.of( "America/Chihuahua" ));
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		try {List<String> lines = Files.readAllLines(Paths.get(filepath+filename));
		
			for(int i = 1; i < lines.size(); i++) {
				String line = lines.get(i);
				String[] fields = line.split(",");
				
				Integer id_prestamo = Integer.valueOf(fields[0]);
				Integer id_miembro = Integer.valueOf(fields[1]);
				Boolean disponible = Boolean.valueOf(fields[2]);
				String ISBN = fields[3];
				java.sql.Date fecha_prestamo = new java.sql.Date(sdf.parse(fields[4]).getTime());
				java.sql.Date fecha_devolucion = new java.sql.Date(sdf.parse(fields[5]).getTime());
				String action = fields[6];
				
				Prestamo prestamo = new Prestamo(id_miembro, disponible, ISBN, fecha_prestamo, fecha_devolucion, id_prestamo);
				ObjectAccion prestamoAccion = new ObjectAccion(prestamo, action);
				prestamosAccion.add(prestamoAccion);
				}
			
			} catch (IOException e) {
				e.printStackTrace();
			}
		
			return prestamosAccion;
		}
	}




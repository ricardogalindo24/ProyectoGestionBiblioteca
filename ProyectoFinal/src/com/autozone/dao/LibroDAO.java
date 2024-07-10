package com.autozone.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.autozone.database.DatabaseConnection;
import com.autozone.interfaces.DAO;
import com.autozone.models.Libro;
//import com.autozone.utils.FileManager;
import com.autozone.utils.Validator;

public class LibroDAO implements DAO<Libro, String> {
	
		//FileManager fileManager = new FileManager();
		
		@Override
		public void add(Libro libro) throws SQLException, IllegalArgumentException, IllegalAccessException {
			Validator.validate(libro);
			
			String sql = "insert into tbl_inventario(ISBN, titulo, autor, genero) values (?,?,?,?)";
			
			try(Connection conn = DatabaseConnection.getInstance().getConnection();
					PreparedStatement pstmt = conn.prepareStatement(sql)){
				
				pstmt.setString(1, libro.getISBN());
				pstmt.setString(2, libro.getTitulo());
				pstmt.setString(3, libro.getAutor());
				pstmt.setString(4, libro.getGenero());
				
				pstmt.executeUpdate();
				System.out.println(libro.getISBN() + " | " + libro.getTitulo() + " | " + libro.getAutor() + " | " + libro.getGenero() + " Importado");
				//fileManager.writeToCSV(fetchRecords());
				
			}
		}
		
		@Override
		public void update(Libro libro) throws SQLException, IllegalArgumentException, IllegalAccessException {
			Validator.validate(libro);
			
			String sql = "update tbl_inventario set titulo = ?, autor = ?, genero = ?"
					+ " where ISBN = ?";
			
			try(Connection conn = DatabaseConnection.getInstance().getConnection();
					PreparedStatement pstmt = conn.prepareStatement(sql)){
				
				pstmt.setString(1, libro.getTitulo());
				pstmt.setString(2, libro.getAutor());
				pstmt.setString(3, libro.getGenero());
				pstmt.setString(4, libro.getISBN());
			
				
				pstmt.executeUpdate();
				System.out.println(libro.getISBN() + " | " + libro.getTitulo() + " | " + libro.getAutor() + " | " + libro.getGenero() + " Actualizado");
				//fileManager.writeToCSV(fetchRecords());
			}
		}
		
		@Override
		public void delete(String ISBN) throws SQLException {
			
			String sql = "delete from tbl_inventario where ISBN = ?";
			
			try(Connection conn = DatabaseConnection.getInstance().getConnection();
					PreparedStatement pstmt = conn.prepareStatement(sql)){
				
						pstmt.setString(1, ISBN);
						pstmt.executeUpdate();
						System.out.println("Libro con ISBN: " + ISBN + " Borrado");
						//fileManager.writeToCSV(fetchRecords());
				
			}
		}
		
		@Override
		public List<Libro> fetchRecords() throws SQLException {
			String sql = "select * from tbl_inventario";
			List<Libro> libros = new ArrayList<Libro>();
			
			try(Connection conn = DatabaseConnection.getInstance().getConnection();
					Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery(sql)) {
					
					Libro libro = null;
					
					while (rs.next()) {
						libro = new Libro(
								rs.getString("ISBN"),
								rs.getString("titulo"),
								rs.getString("autor"),
								rs.getString("genero")
						);
					
						libro.setISBN(rs.getString("ISBN"));
						libros.add(libro);
						
						
						
					}
			}
			return libros;
		}
		
		@Override
		public List<Libro> search(String field, String criteria) throws SQLException {
			String sql = "select * from tbl_inventario where " + field + " = ?";
			List<Libro> libros = new ArrayList<Libro>();
			
			try(Connection conn = DatabaseConnection.getInstance().getConnection();
					PreparedStatement pstmt = conn.prepareStatement(sql)){
					pstmt.setString(1, criteria);
					
					ResultSet rs = pstmt.executeQuery();
					
					Libro libro = null;
					
					while (rs.next()) {
						libro = new Libro(
								rs.getString("ISBN"),
								rs.getString("titulo"),
								rs.getString("autor"),
								rs.getString("genero")
						);
					
						libro.setISBN(rs.getString("ISBN"));
						libros.add(libro);
						
						
						
					}
			}
			return libros;
		}
}

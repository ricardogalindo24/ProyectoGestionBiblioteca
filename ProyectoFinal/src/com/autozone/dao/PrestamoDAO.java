package com.autozone.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import com.autozone.database.DatabaseConnection;
import com.autozone.interfaces.DAO;
import com.autozone.models.Prestamo;
import com.autozone.utils.Validator;

public class PrestamoDAO implements DAO<Prestamo, Integer> {
	
	LocalDate localDate = LocalDate.now( ZoneId.of( "America/Chihuahua" ) );
	
	@Override
	public void add(Prestamo prestamo) throws SQLException, IllegalArgumentException, IllegalAccessException{

		Validator.validate(prestamo);
		
		String sql = "insert into tbl_prestamos(nombre, ISBN, disponible, fecha_prestamo, fecha_devolucion) values (?,?,?,?,?)";
		
		try(Connection conn = DatabaseConnection.getInstance().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)){
			
			pstmt.setString(1, prestamo.getNombre());
			pstmt.setString(2, prestamo.getISBN());
			pstmt.setBoolean(3, prestamo.getDisponible());
			pstmt.setDate(4, prestamo.getFecha_prestamo());
			pstmt.setDate(5, prestamo.getFecha_devolucion());
			
			pstmt.executeUpdate();
			System.out.println(prestamo.getNombre() + " | " + prestamo.getISBN() + " | " + prestamo.getDisponible() + " | " + prestamo.getFecha_prestamo() + prestamo.getFecha_devolucion() + " Agregado");
			//fileManager.writeToCSV(fetchRecords());
			
		}
	}
	
	@Override
	public void update(Prestamo prestamo) throws SQLException, IllegalArgumentException, IllegalAccessException{

		Validator.validate(prestamo);
		
		String sql = "update tbl_prestamos set nombre = ?, ISBN = ?, disponiible = ?, fecha_prestamo = ?, fecha_devolucion = ?"
				+ " where id_prestamo = ?";
		
		try(Connection conn = DatabaseConnection.getInstance().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)){
			
			pstmt.setString(1, prestamo.getNombre());
			pstmt.setString(2, prestamo.getISBN());
			pstmt.setBoolean(3, prestamo.getDisponible());
			pstmt.setDate(4, prestamo.getFecha_prestamo());
			pstmt.setDate(5, prestamo.getFecha_devolucion());
			pstmt.setInt(6, prestamo.getId_prestamo());
		
			
			pstmt.executeUpdate();
			System.out.println(prestamo.getId_prestamo() + " | " + prestamo.getNombre() + " | " + prestamo.getISBN() + " | " + prestamo.getDisponible() + " | " + prestamo.getFecha_prestamo() + prestamo.getFecha_devolucion() + " Actualizado");
			//fileManager.writeToCSV(fetchRecords());
		}
	}
	
	@Override
	public void delete(Integer id_prestamo) throws SQLException{

		
		String sql = "delete from tbl_prestamos where id_prestamo = ?";
		
		try(Connection conn = DatabaseConnection.getInstance().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)){
			
					pstmt.setInt(1, id_prestamo);
					pstmt.executeUpdate();
					System.out.println("Libro con id_prestamo: " + id_prestamo + " Borrado");
					//fileManager.writeToCSV(fetchRecords());
			
		}
	}
	
	@Override
	public List<Prestamo> fetchRecords() throws SQLException{

		String sql = "select * from tbl_prestamos";
		List<Prestamo> prestamos = new ArrayList<Prestamo>();
		
		try(Connection conn = DatabaseConnection.getInstance().getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {
				
				Prestamo prestamo = null;
				
				while (rs.next()) {
					prestamo = new Prestamo(
							rs.getString("nombre"),
							rs.getBoolean("disponible"),
							rs.getString("ISBN"),
							rs.getDate("fecha_prestamo"),
							rs.getDate("fecha_devolucion"),
							rs.getInt("id_prestamo")
					);
				
					prestamo.setId_prestamo(rs.getInt("id_prestamo"));
					prestamos.add(prestamo);
					
					
					
				}
		}
		return prestamos;
	}
	
	@Override
	public List<Prestamo> search(String field, String criteria) throws SQLException{

		String sql = "select * from tbl_prestamos where " + field + " = ?";
		List<Prestamo> prestamos = new ArrayList<Prestamo>();
		
		
		try(Connection conn = DatabaseConnection.getInstance().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)){
			
			if(field == "id_prestamo") {pstmt.setInt(1, Integer.valueOf(criteria));
			} else {pstmt.setString(1, criteria);}
				
				ResultSet rs = pstmt.executeQuery();
				
				Prestamo prestamo = null;
				
				while (rs.next()) {
					prestamo = new Prestamo(
							rs.getString("nombre"),
							rs.getBoolean("disponible"),
							rs.getString("ISBN"),
							rs.getDate("fecha_prestamo"),
							rs.getDate("fecha_devolucion"),
							rs.getInt("id_prestamo")
					);
				
					prestamo.setId_prestamo(rs.getInt("id_prestamo"));
					prestamos.add(prestamo);
					
					
					
				}
		}
		return prestamos;
	}
}

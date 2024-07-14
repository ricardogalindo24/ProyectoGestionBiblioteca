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
import com.autozone.models.Miembro;
import com.autozone.utils.Validator;

public class MiembroDAO implements DAO<Miembro, Integer> {
	
	@Override
	public void add(Miembro miembro) throws SQLException, IllegalArgumentException, IllegalAccessException {

		Validator.validate(miembro);
		
		String sql = "insert into tbl_miembros(nombre) values (?)";
		
		try(Connection conn = DatabaseConnection.getInstance().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)){
			
			//pstmt.setInt(1, miembro.getId_miembro());
			pstmt.setString(1, miembro.getNombre());
			pstmt.executeUpdate();
			
			miembro = search("nombre", miembro.getNombre()).get(0);
			System.out.printf("%-20s%-20s%-20s\n", miembro.getId_miembro(),  miembro.getNombre(),"☑ Added");
			
			//System.out.println(miembro.getId_miembro() + " | " + miembro.getNombre() + " Añadido");
			//fileManager.writeToCSV(fetchRecords());
			
		}
	}
	
	@Override
	public void update(Miembro miembro) throws SQLException, IllegalArgumentException, IllegalAccessException{
		Validator.validate(miembro);
		
		String sql = "update tbl_miembros set nombre = ?"
				+ " where id_miembro = ?";
		
		try(Connection conn = DatabaseConnection.getInstance().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)){
			
			pstmt.setString(1, miembro.getNombre());
			pstmt.setInt(2, miembro.getId_miembro());
		
			
			pstmt.executeUpdate();
			
			miembro = search("id_miembro", Integer.toString(miembro.getId_miembro())).get(0);
			System.out.printf("%-20s%-20s%-20s\n", miembro.getId_miembro(),  miembro.getNombre(),"☑ Updated");
			//System.out.println(miembro.getId_miembro() + " | " + miembro.getNombre() +  " Actualizado");
			//fileManager.writeToCSV(fetchRecords());
		}
	}
	
	@Override
	public void delete(Integer id_miembro) throws SQLException{

		
		String sql = "delete from tbl_miembros where id_miembro = ?";
		Miembro miembro = search("id_miembro", id_miembro.toString()).get(0);
		
		try(Connection conn = DatabaseConnection.getInstance().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)){
			
					pstmt.setInt(1, id_miembro);
	
					pstmt.executeUpdate();
					System.out.printf("%-20s%-20s%-20s\n", miembro.getId_miembro(),  miembro.getNombre(),"❌ Deleted");
					//System.out.println("Miembro con id_miembro: " + id_miembro + " Borrado");
					//fileManager.writeToCSV(fetchRecords());
			
		}
	}
	
	@Override
	public List<Miembro> fetchRecords() throws SQLException{

		String sql = "select * from tbl_miembros";
		List<Miembro> miembros = new ArrayList<Miembro>();
		
		try(Connection conn = DatabaseConnection.getInstance().getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {
				
				Miembro miembro = null;
				
				while (rs.next()) {
					miembro = new Miembro(
							rs.getString("nombre"),
							rs.getInt("id_miembro"));
				
					miembro.setId_miembro(rs.getInt("id_miembro"));
					miembros.add(miembro);
					
					
					
				}
		}
		return miembros;
	}
	
	@Override
	public List<Miembro> search(String field, String criteria) throws SQLException{

		String sql = "select * from tbl_miembros where " + field + " = ?";
		List<Miembro> miembros = new ArrayList<Miembro>();
		
		try(Connection conn = DatabaseConnection.getInstance().getConnection();
				
				PreparedStatement pstmt = conn.prepareStatement(sql)){
			
				if(field == "id_miembro") {pstmt.setInt(1, Integer.valueOf(criteria));
				} else {pstmt.setString(1, criteria);}
				
				ResultSet rs = pstmt.executeQuery();
				
				Miembro miembro = null;
				
				while (rs.next()) {
					miembro = new Miembro(
							rs.getString("nombre"),
							rs.getInt("id_miembro")
					);
				
					miembro.setId_miembro(rs.getInt("id_miembro"));
					miembros.add(miembro);
					
					
					
				}
		}
		return miembros;
	}
}

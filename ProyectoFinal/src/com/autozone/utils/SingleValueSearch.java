package com.autozone.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.autozone.database.DatabaseConnection;

public class SingleValueSearch {

	public Boolean integerExists(String table, Integer criteria, String field) throws SQLException{

		String sql = "select " + field + " from " + table+ " where " + field + " = ?";
		List<Integer> ids_prestamos = new ArrayList<Integer>();
		Boolean integerExists;
		Integer value;
		
		try(Connection conn = DatabaseConnection.getInstance().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)){
			
			pstmt.setInt(1, Integer.valueOf(criteria));
				
				ResultSet rs = pstmt.executeQuery();
				
				while (rs.next()) {
					value = rs.getInt(field);
					ids_prestamos.add(value);}
				
				integerExists = !ids_prestamos.isEmpty();
		}
		return integerExists;
	}
	
	public Boolean stringExists(String table, String criteria, String field) throws SQLException{

		String sql = "select " + field + " from " + table+ " where " + field + " = ?";
		List<String> values = new ArrayList<String>();
		Boolean stringExists;
		String value;
		
		try(Connection conn = DatabaseConnection.getInstance().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)){
			
			pstmt.setString(1, criteria);
				
				ResultSet rs = pstmt.executeQuery();
				
				while (rs.next()) {
					value = rs.getString(field);
					values.add(value);}
				
				stringExists = !values.isEmpty();
		}
		return stringExists;
	}
	
	
	public String returnValue(String table, String criteria, String search_field, String result_field, Boolean topRow) throws SQLException{

		String sql = "select " + result_field + " from " + table + " where " + search_field + " = ?";
		List<String> values = new ArrayList<String>();
		String value;
		String result;
		
		try(Connection conn = DatabaseConnection.getInstance().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)){
			
			pstmt.setString(1, criteria);
				
				ResultSet rs = pstmt.executeQuery();
				
				while (rs.next()) {
					value = rs.getString(result_field);
					values.add(value);}
				
				Integer elements = values.size();
				if(elements > 0) {
				if(topRow == true) {
					result = values.get(0);
				} else {result = values.get(elements - 1);}} else {result = "Not Found";}
		}
		return result;
	}
}

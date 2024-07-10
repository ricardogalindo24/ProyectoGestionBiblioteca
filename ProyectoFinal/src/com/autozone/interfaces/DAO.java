package com.autozone.interfaces;


import java.sql.SQLException;
import java.util.List;
//import com.autozone.utils.FileManager;


public interface DAO<T, K> {
	
		
		//FileManager fileManager = new FileManager();
		
		public void add(T t) throws SQLException, IllegalArgumentException, IllegalAccessException;
		public void update(T t) throws SQLException, IllegalArgumentException, IllegalAccessException;
		public void delete(K k) throws SQLException;
		public List<T> fetchRecords() throws SQLException;
		public List<T> search(String field, String criteria) throws SQLException;
}


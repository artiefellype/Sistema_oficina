package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.SQLException;


public abstract class BaseDAO<VO> {
	
	public static Connection conect = null;
	public static final String url = "jdbc:postgresql://localhost:5432/sistemaOficina";
	public static final String user = "postgres";
	public static final String pass = "arsec021";
	
	
	public static Connection getConnection() {
		
		if(conect == null) {
			
			try {
				conect = DriverManager.getConnection(url, user, pass);
			}catch (SQLException e) {
				e.printStackTrace();
			}
			
			return conect;
		}else {
			return conect;
		}
		
	}
	
	public static void closeConnection() {
		if(!(conect == null)) {
			try {
				conect.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
}

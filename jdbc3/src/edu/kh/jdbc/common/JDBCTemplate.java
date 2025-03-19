package edu.kh.jdbc.common;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class JDBCTemplate {

	
	private static Connection conn = null;
	
	public static Connection getConnertion() {
		
		try {
			
			if(!conn.isClosed() && conn != null) {
				return conn;
			}
			
			Properties prop = new Properties();
			
			String filePath = "driver.xml";
			
			prop.loadFromXML(new FileInputStream(filePath));
			
			Class.forName(prop.getProperty("driver"));
			
			conn = DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("ID"), prop.getProperty("password"));
			
			conn.setAutoCommit(false);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return conn;
		
	}

	public static void commit(Connection conn) {
		
		try {
			
			if(conn != null && !conn.isClosed()) conn.commit();
			
		}catch(Exception e) {
			System.out.println("커밋 예외");
			e.printStackTrace();
		}
	}

	public static void Rollback(Connection conn) {
		
		try {
			
			if(conn != null && !conn.isClosed()) conn.rollback();
			
		}catch(Exception e) {
			System.out.println("롤백 예외");
			e.printStackTrace();
		}
	}

	public static void close(Connection conn) {
		
		try {
			
			if(conn != null && !conn.isClosed()) conn.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void close(Statement stmt) {
		try {
			if(stmt !=  null && !stmt.isClosed()) stmt.close();
		} catch (Exception e) {
			System.out.println("Statmentcloser() 중 예외 발생");
			e.printStackTrace();
		}
	}
	
	public static void close(ResultSet rs) {
		try {
			
			if(rs != null && !rs.isClosed()) rs.close();
			
		} catch (Exception e) {
			System.out.println("ResultSet close() 중 예외 발생");
			e.printStackTrace();
		}
	}
	
	
	

}

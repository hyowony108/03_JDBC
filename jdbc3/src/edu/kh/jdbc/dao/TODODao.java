package edu.kh.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import edu.kh.jdbc.dto.User;

public class TODODao {

	ResultSet rs = null;
	PreparedStatement pstmt = null;
	Statement stmt = null;
	
	
	public String serchTodo(Connection conn, List<User> login) throws Exception {
		String res = null;
		
		try {
			
			String sql = """
					SELECT TODO_NO, TITLE, FINISH, DATE
					FROM TB_TODO
					WHERE NAME = ?
					""";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, login.get(0).getName());
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				
				int tonoNo = pstmt.getInt(1);
				
			}
			
			
		}finally {
			
		}
		return null;
	}

}

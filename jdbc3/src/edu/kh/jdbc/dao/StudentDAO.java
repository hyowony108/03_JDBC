package edu.kh.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import edu.kh.jdbc.common.JDBCTemplate;
import edu.kh.jdbc.dto.Student;

public class StudentDAO {

	private ResultSet rs = null;
	private PreparedStatement pstmt = null;
	private Statement stmt = null;
	
	public int insertStudent(Connection conn, Student std) throws Exception {
		
		int res = 0;
		
		try {
			
			String sql = """
					INSERT INTO TB_STUDENT2 
					VALUES (?, ?, ?, ?, ?);
					""";
			
			pstmt = conn.prepareStatement(sql);
				
			pstmt.setInt(1, std.getStudentNo());
			pstmt.setString(3, std.getStudentName());
			pstmt.setString(2, std.getDepartmentNo());
			pstmt.setString(4, std.getStudentSsn());
			pstmt.setString(5, std.getStudentAddress());
			
			res = pstmt.executeUpdate();
			
			
		}finally {
			JDBCTemplate.close(pstmt);
		}
		
		return 0;
	}

}

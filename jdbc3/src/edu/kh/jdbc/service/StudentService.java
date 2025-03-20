package edu.kh.jdbc.service;

import java.sql.Connection;

import edu.kh.jdbc.common.JDBCTemplate;
import edu.kh.jdbc.dao.StudentDAO;
import edu.kh.jdbc.dto.Student;



public class StudentService {
	
	private StudentDAO dao = new StudentDAO();

	public int insertStudent(Student std) throws Exception {

		Connection conn = JDBCTemplate.getConnertion();
		
		int res = dao.insertStudent(conn, std);
		
		return res;
	}

}

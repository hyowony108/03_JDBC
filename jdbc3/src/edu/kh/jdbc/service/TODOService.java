package edu.kh.jdbc.service;

import java.sql.Connection;
import java.util.List;

import edu.kh.jdbc.common.JDBCTemplate;
import edu.kh.jdbc.dao.TODODao;
import edu.kh.jdbc.dto.User;

public class TODOService {
	
	TODODao dao = new TODODao();


	public String serchTodo(List<User> login) throws Exception {
		
		Connection conn = JDBCTemplate.getConnertion();
		
		String res = dao.serchTodo(conn, login);
		
		return res;
	}

}

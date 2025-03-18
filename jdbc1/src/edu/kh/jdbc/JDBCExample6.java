package edu.kh.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class JDBCExample6 {

	public static void main(String[] args) {

		// 아이디, 비밀번호, 이름을 입력받아
		// 아이디, 비밀번호가 일치하는 사용자의
		// 이름을 수정(UPDATE)
		
		// 1. PreparedStatement 이용하기
		// 2. commit / rollback 처리하기
		// 3. 성공 시 "수정 성공!" 출력 / 실패 시 "아이디 또는 비밀번호 불일치" 출력
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		Scanner sc = null;
		
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			String userName = "kh";
			String password = "kh1234";
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", userName, password);
			
			sc = new Scanner(System.in);
			
			System.out.print("아이디 : ");
			String id = sc.nextLine();
		
			System.out.print("비밀번호 : ");
			String pw = sc.nextLine();
			
			System.out.print("수정할 이름 : ");
			String name = sc.nextLine();
			
			String sql = """
					UPDATE TB_USER
					SET USER_NAME = ?
					WHERE USER_ID = ? AND USER_PW = ?
					""";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, name);
			pstmt.setString(2, id);
			pstmt.setString(3, pw);
			
			conn.setAutoCommit(false);
			
			int res = pstmt.executeUpdate();
			
			
			if(res > 0) {
				System.out.println("수정 성공");
				conn.commit(); // COMMIT 수행 -> DB에 INSERT 영구 반영
				
			}else { 
				System.out.println("아이디 또는 비밀번호 불일치");
				conn.rollback();
				
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
				
				if(sc != null) sc.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
	}

}

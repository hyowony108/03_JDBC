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
			
			String sql = """
					SELECT USER_ID, USER_PW
					FROM TB_USER
					WHERE USER_ID = ? AND USER_PW = ?
					""";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			
			conn.setAutoCommit(false);
			
			ResultSet result = pstmt.executeQuery();
			
			if(result != null) {
				conn.rollback();
				
				System.out.print("이름 : ");
				String name = sc.nextLine();
				
				String sql2 = """
						INSERT INTO TB_USER 
						VALUES(SEQ_USER_NO.NEXTVAL, ?, ?, ?, DEFAULT )
						""";
				pstmt = conn.prepareStatement(sql2);
				pstmt.setString(1, id);
				pstmt.setString(2, pw);
				pstmt.setString(3, name);
				
				
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
	}

}

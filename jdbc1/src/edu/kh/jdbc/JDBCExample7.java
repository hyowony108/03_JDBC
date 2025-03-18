package edu.kh.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class JDBCExample7 {

	public static void main(String[] args) {

		// EMPLOYEE	테이블에서
		// 사번, 이름, 성별, 급여, 직급명, 부서명을 조회
		// 단, 입력 받은 조건에 맞는 결과만 조회하고 정렬할 것
				
		// - 조건 1 : 성별 (M, F)
		// - 조건 2 : 급여 범위
		// - 조건 3 : 급여 오름차순/내림차순
				
		// [실행화면]
		// 조회할 성별(M/F) : F
		// 급여 범위(최소, 최대 순서로 작성) :
		// 3000000
		// 4000000
		// 급여 정렬(1.ASC, 2.DESC) : 2
				
		// 사번 | 이름   | 성별 | 급여    | 직급명 | 부서명
		//--------------------------------------------------------
		// 218  | 이오리 | F    | 3890000 | 사원   | 없음
		// 203  | 송은희 | F    | 3800000 | 차장   | 해외영업2부
		// 212  | 장쯔위 | F    | 3550000 | 대리   | 기술지원부
		// 222  | 이태림 | F    | 3436240 | 대리   | 기술지원부
		// 207  | 하이유 | F    | 3200000 | 과장   | 해외영업1부
		// 210  | 윤은해 | F    | 3000000 | 사원   | 해외영업1부

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String ju = null;
		
		Scanner sc = null;
		
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			String url = "jdbc:oracle:thin:@localhost:1521:XE";
			String userName = "kh";
			String password = "kh1234";
			
			conn = DriverManager.getConnection(url, userName, password);
			
			sc = new Scanner(System.in);
			
			System.out.print("조회할 성별(M/F) : ");
			String ma = sc.next().toUpperCase();
			
			System.out.println("급여 범위(최소, 최대 순서로 작성) : ");
			int min = sc.nextInt();
			int max = sc.nextInt();
			
			System.out.print("급여 정렬(1.ASC, 2.DESC) : ");
			int j = sc.nextInt();
			sc.nextLine();
			
			if(j == 1) {
				ju = "ASC";
			}else if (j == 2) {
				ju = "DESC";
			}else {
				System.out.println("정확한 값을 입력해주세요");
				return;
			}
			
			/*
			 * 문자열 이어쓰기로도 할 수 있음
			 * String sql = """
					SELECT EMP_ID, EMP_NAME, DECODE(SUBSTR(EMP_NO, 8, 1), 1 , 'M', 2, 'F') GENDER, 
					SALARY, JOB_NAME, NVL(DEPT_TITLE, '없음') DEPT
					FROM EMPLOYEE
					JOIN JOB ON(EMPLOYEE.JOB_CODE = JOB.JOB_CODE )
					LEFT JOIN DEPARTMENT ON(DEPT_ID = DEPT_CODE)
					WHERE (SALARY BETWEEN ? AND ?)
					AND DECODE(SUBSTRC(EMP_NO, 8, 1), 1 , 'M', 2, 'F') = ?
					ORDER BY SALARY 
					"""
			
				if(j == 1) {
					sql += "ASC";
				}else if (j == 2) {
					sql += "DESC";		
			
			 * */
			
			String sql = """
					SELECT EMP_ID, EMP_NAME, DECODE(SUBSTR(EMP_NO, 8, 1), 1 , 'M', 2, 'F') GENDER, 
					SALARY, JOB_NAME, NVL(DEPT_TITLE, '없음') DEPT
					FROM EMPLOYEE
					JOIN JOB ON(EMPLOYEE.JOB_CODE = JOB.JOB_CODE )
					LEFT JOIN DEPARTMENT ON(DEPT_ID = DEPT_CODE)
					WHERE (SALARY BETWEEN ? AND ?)
					AND DECODE(SUBSTRC(EMP_NO, 8, 1), 1 , 'M', 2, 'F') = ?
					ORDER BY SALARY 
					""" + ju ;
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, min);
			pstmt.setInt(2, max);
			pstmt.setString(3, ma);
			
			rs = pstmt.executeQuery();
			
			System.out.println("사번 | 이름   | 성별 | 급여    | 직급명 | 부서명");
			System.out.println("----------------------------------------------------------");
			
			boolean flag = true; // true : 조회결과가 없음, false : 조회결과 존재
			
			while(rs.next()) {
				
				flag = false; // while문이 1회 이상 반복함 = 조회결과가 1행이라도 있다
				
				String empId = rs.getString("EMP_ID");
				String empName = rs.getString("EMP_NAME");
				String male = rs.getString("GENDER");
				int salary = rs.getInt("SALARY");
				String job = rs.getString("JOB_NAME");
				String dept = rs.getString("DEPT");
				
				System.out.printf("%-4s | %3s | %-4s | %7d | %-3s  | %s \n",
						empId, empName, male, salary, job, dept);

			}
			
			if(flag) {
				System.out.println("조회 결과 없음");
			}
			
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
				if(sc != null) sc.close();
				
			} catch (Exception e) {
				
			}
		}
	}

}

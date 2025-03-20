package edu.kh.jdbc.view;

import java.util.InputMismatchException;
import java.util.Scanner;

import edu.kh.jdbc.dto.Student;
import edu.kh.jdbc.service.StudentService;

public class StudentView {

	private Scanner sc = new Scanner(System.in);
	private StudentService service = new StudentService();
	
public void mainMenu() {
		

	
		int input = 0;
		
		do {
			try {
				
				System.out.println("\n===== 학생 관리 프로그램 =====\n");
				System.out.println("1. 학생 등록(INSERT)");
				System.out.println("2. 학생 전체 조회(SELECT)");
				System.out.println("3. 학생 중 이름에 검색어가 포함된 회원 조회 (SELECT)");
				System.out.println("4. STUDENT_NO를 입력 받아 일치하는 User 조회(SELECT)");
				System.out.println("5. STUDENT_NO를 입력 받아 일치하는 User 삭제(DELETE)");
				System.out.println("6. ID, PW가 일치하는 회원이 있을 경우 이름 수정(UPDATE)");
				System.out.println("7. 학생 등록(아이디 중복 검사)");
				System.out.println("8. 여러 학생 등록하기");
				System.out.println("0. 프로그램 종료");
				
				System.out.print("메뉴 선택 : ");
				input = sc.nextInt();
				sc.nextLine(); // 버퍼에 남은 개행문자 제거
				
				switch(input) {
				
				case 1: insertStudent(); break; 
				case 2: selectAll(); break; 
				case 3: selectName(); break; 
				case 4: selectUser(); break;
				case 5: deleteUser(); break; 
				case 6: updateName(); break; 
				case 7: insertUser2(); break;
				case 8: multiInsertUser(); break; 
				
				case 0 : System.out.println("\n[프로그램 종료]\n"); break;
				default: System.out.println("\n[메뉴 번호만 입력하세요]\n"); 
				}
				
				System.out.println("\n-------------------------------------\n");
				
			} catch (InputMismatchException e) {
				// Scanner를 이용한 입력 시 자료형이 잘못된 경우
				System.out.println("\n***잘못 입력 하셨습니다***\n");
				
				input = -1; // 잘못 입력해서 while문 멈추는걸 방지
				sc.nextLine(); // 입력 버퍼에 남아있는 잘못된 문자 제거
				
			} catch (Exception e) {
				// 발생되는 예외를 모두 해당 catch 구문으로 모아서 처리
				e.printStackTrace();
			}
			
		}while(input != 0);
		
	} // mainMenu() 종료

private void insertStudent() throws Exception {

	System.out.println("\n=== 1. 학생 등록 ===\n");
	
	System.out.print("학생 번호 : ");
	int stdNo = sc.nextInt();
	
	System.out.print("학생 이름 : ");
	String name = sc.next();
	
	System.out.print("학과 번호 : ");
	String deNo = sc.next();
	
	System.out.print("주민 번호 : ");
	String stdSsn = sc.next();
	
	System.out.print("주소 : ");
	String address = sc.next();
	
	Student std = new Student();
	
	std.setStudentNo(stdNo);
	std.setStudentName(name);
	std.setDepartmentNo(deNo);
	std.setStudentSsn(stdSsn);
	std.setStudentAddress(address);
	
	int res = service.insertStudent(std);
	
}

private void selectAll() {
	System.out.println("\n=== 2. 학생 전체 조회 ===\n");
	
}

private void selectName() {
	System.out.println("\n=== 3. 학생 중 이름에 검색어가 포함된 회원 조회 (SELECT) ===\n");
	
}

private void selectUser() {
	System.out.println("\n=== 4. STUDENT_NO를 입력 받아 일치하는 User 조회(SELECT) ===\n");
	
}

private void deleteUser() {
	System.out.println("\n=== 5. STUDENT_NO를 입력 받아 일치하는 User 삭제(DELETE) ===\n");
	
}

private void updateName() {
	System.out.println("\n=== 6. ID, PW가 일치하는 회원이 있을 경우 이름 수정 ===\n");
	
}

private void insertUser2() {
	System.out.println("\n=== 7. 학생 등록(아이디 중복 검사) ===\n");
	
}

private void multiInsertUser() {
	System.out.println("\n=== 8. 여러 학생 등록하기 ===\n");
	
}
}

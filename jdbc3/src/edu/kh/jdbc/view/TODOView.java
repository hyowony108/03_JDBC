package edu.kh.jdbc.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import edu.kh.jdbc.dto.User;
import edu.kh.jdbc.service.TODOService;

public class TODOView {

	private Scanner sc = new Scanner(System.in);
	private List<User> user = new ArrayList<>();
	private List<User> login = new ArrayList<>();
	private TODOService service = new TODOService();
	
	public TODOView() {
		
		user.add(new User("홍길동", "user01", "pass01"));
		user.add(new User("저이유", "user02", "pass02"));
		
	}
	
	public void mainMenu() {
		
		try {
			int menu = 0;
			
			do {
				System.out.println("=== TODO리스트 프로그램 ===");
				
				String program = """
						1. 회원가입
						2. 로그인
						3. 내 Todo 전체조회 (번호, 제목, 완료여부, 작성일)
						4. 새로운 Todo 추가
						5. Todo 수정 (제목, 내용)
						6. 완료 여부 변경 (Y <-> N)
						7. Todo 삭제
						8. 로그아웃
						0. 종료
						""";
				System.out.println(program);
				
				System.out.print("메뉴 입력 : ");
				menu = sc.nextInt();
				
				switch(menu) {
				case 1 : signIn(); break;
				case 2 : login(); break;
				case 3 : serchTodo(); break;
				case 4 : newTodo(); break;
				case 5 : editTodo(); break;
				case 6 : finish(); break;
				case 7 : delect(); break;
				case 8 : logout(); break;
				case 0 : System.out.println("\n=== 프로그램 종료 ===\n"); break;
				
				default : System.out.println("일치하는 번호가 아닙니다 다시 입력해주세요"); break;
				
				}
				
			}while (menu == 0);
			
		} catch(Exception e){
			e.printStackTrace();
		}
	}

	private void signIn() {

		System.out.println("=== 회원가입 ===");
		
		System.out.print("이름 : ");
		String name = sc.next();
		System.out.print("아이디 : ");
		String id = sc.next();
		System.out.print("비밀번호 : ");
		String pw = sc.next();
		System.out.print("비밀번호 확인 : ");
		String pw2 = sc.next();
		
		if(pw.equals(pw2)) {
			user.add(new User(name, id, pw));
			
			System.out.println(name + "님 회원가입 되었습니다.");
			
		} else {
			System.out.println("비밀번호가 일치하지 않습니다");
		}
	}

	private void login() {

	if(user.isEmpty()) {
		System.out.println("회원 가입부터 진행해 주세요.");
	} else {
		
		System.out.println("=== 로그인 ===");
		
		System.out.print("아이디 : ");
		String id = sc.next();
		
		System.out.print("비밀번호 : ");
		String pw = sc.next();
		
		for(int i = 0 ; i < user.size() ; i++) {
			
			if(id.equals(user.get(i).getId()) && pw.equals(user.get(i).getPass())){
				
				login.add(new User(user.get(i).getName(), id, pw));
				
				System.out.println(user.get(i).getName() + "고객님 로그인 되었습니다.");
				
				return;
			}
		}
		
		System.out.println("아이디 또는 비밀번호가 일치하지 않습니다.");
	}
	
		
	}

	private void serchTodo() throws Exception {

		if(login.isEmpty()) {
			System.out.println("회원 정보를 조회할 수 없습니다. 로그인부터 진행해주세요");
			return;
		}
		
		System.out.println("\n=== 내 Todo 전체 조회 ===\n");
		
		String res = service.serchTodo(login);
		
		
		
	}

	private void newTodo() {
		// TODO Auto-generated method stub
		
	}

	private void editTodo() {
		// TODO Auto-generated method stub
		
	}

	private void finish() {
		// TODO Auto-generated method stub
		
	}

	private void delect() {
		// TODO Auto-generated method stub
		
	}

	private void logout() {
		// TODO Auto-generated method stub
		
	}
}

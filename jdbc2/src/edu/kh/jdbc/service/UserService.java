package edu.kh.jdbc.service;

import java.sql.Connection;
import java.util.List;

import edu.kh.jdbc.common.JDBCTemplate;
import edu.kh.jdbc.dao.UserDAO;
import edu.kh.jdbc.dto.User;

// (Model 중 하나)Service : 비즈니스 로직을 처리하는 계층
// 데이터를 가공하고 트랜잭션(commit, rollback) 관리 수행
public class UserService {

	// 필드
	private UserDAO dao = new UserDAO();

	
	// 메서드
	/** 전달받은 아이디와 일치하는 User 정보 반환 서비스
	 * @param input (입력된 아이디)
	 * @return 아이디가 일치하는 회원 정보가 담긴 User 객체,
	 * 			없으면 null 반환
	 */
	public User selectId(String input) {
		
		// 1. 커넥션 생성
		Connection conn = JDBCTemplate.getConnertion();
		
		// 2. 데이터 가공 (할게 없으면 넘어감)
		
		// 3. DAO 메서드 호출 결과 반환
		User user = dao.selectId(conn, input);
		
		// 4. DML(commit/rollback)
		
		// 5. 다 쓴 커넥션 자원 반환
		JDBCTemplate.close(conn);
		
		// 6. 결과를 view 리턴
		
		return user;
	}


	/** 1. User 등록 서비스
	 * @param user
	 * @return 결과 행의 갯수
	 * @throws Exception 
	 */
	public int insertUser(User user) throws Exception {
		
		// 1. 커넥션 생성
		Connection conn = JDBCTemplate.getConnertion();
		
		// 2. 데이터 가공 (할게 없으면 넘어감)
		
		// 3. DAO 메서드 호출 결과 반환		
		int res = dao.insertUser(conn, user);
		
		// 4. DML(INSERT) 수행 결과에 따라 트랜잭션 제어 처리
		if(res>0) {
			JDBCTemplate.commit(conn);
			
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		// 5. Connection 반환
		JDBCTemplate.close(conn);
		
		// 6. 결과 반환
		return res;
	}


	/** 2. User 전체 조회 서비스
	 * @return 조회된 User들이 담긴 List
	 * @throws Exception 
	 */
	public List<User> selectAll() throws Exception {
		
		// 1. 커넥션 생성
		Connection conn = JDBCTemplate.getConnertion();
		
		// 2. DAO 메서드 호출(SELECT) 후  결과반환(List<User>)	
		List<User> userList = dao.selectAll(conn);
		
		// 3. Connection 반환
		JDBCTemplate.close(conn);
		
		// 4. 결과 반환
		return userList;
	}


	/** 3. User 중 이름에 검색어가 포함된 회원 조회 서비스
	 * @param keyword : 입력한 키워드
	 * @return serchList : 조회된 회원 리스트
	 */
	public List<User> selectName(String keyword) throws Exception{

		Connection conn = JDBCTemplate.getConnertion();
		
		List<User> serchList = dao.selectName(conn, keyword);
		
		JDBCTemplate.close(conn);
		
		return serchList;
	}


	/** 4. USER_NO를 입력받아 일치하는 User 조회 서비스
	 * @param input
	 * @return user(조회된 회원 정보 또는 null)
	 * @throws Exception
	 */
	public User selectUser(int input) throws Exception{
		
		Connection conn = JDBCTemplate.getConnertion();
		
		User user = dao.selectUser(conn, input);
		
		JDBCTemplate.close(conn);
		
		return user;
	}


	/** USER_NO를 입력받아 일치하는 User 삭제 서비스
	 * @param input
	 * @return result
	 */
	public int deleteUser(int input) throws Exception{
		
		Connection conn = JDBCTemplate.getConnertion();
		
		int result = dao.deleteUser(conn, input);
		
		// 결과에 따른 트랜잭션 처리 필요
		if(result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return result;
	}


	/** ID, PW가 일치하는 회원의 USER_NO 조회 서비스
	 * @param userId
	 * @param userPw
	 * @return userNo
	 */
	public int selectUserNo(String userId, String userPw) throws Exception{
		
		Connection conn = JDBCTemplate.getConnertion();
		
		int userNo = dao.selectUser(conn, userId, userPw);
		
		JDBCTemplate.close(conn);
		
		return userNo;
	}


	/** userNo가 일치하는 회원의이름 수정 서비스
	 * @param name
	 * @param userNo
	 * @return result
	 */
	public int updateName(String name, int userNo) throws Exception{

		Connection conn = JDBCTemplate.getConnertion();
		
		int result = dao.updateName(conn, name, userNo);
		
		if(result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return result;
	}


	/** 아이디 중복 확인 서비스
	 * @param userId
	 * @return count
	 */
	public int idCheck(String id) throws Exception{
		
		Connection conn = JDBCTemplate.getConnertion();
		
		int count = dao.idCheck(conn, id);
		
		JDBCTemplate.close(conn);
		
		return count;
	}


	/** userList에 있는 모든 User 객체를 INSERT 서비스
	 * @param userList
	 * @return count
	 */
	public int multiInsertUser(List<User> userList) throws Exception{
		
		// 다중 INSERT 방법
		// 1) SQL을 이용한 다중 INSERT
		// 2) Java 반복문을 이용한 다중 INSERT (이거 사용)
		
		Connection conn = JDBCTemplate.getConnertion();
		
		int count = 0; // 삽입 성공한 행의 개수 count
		
		// 1행씩 삽입
		for(User u : userList) {
			int result = dao.insertUser(conn, u);
			count += result; // 삽입 성공한 행의 개수를 count 누적
		}
		
		count --;
		
		// 트랜잭션 제어 처리
		// 전체 삽입 성공 시 commit / 아니면 rollback(일부 삽입, 전체 실패)
		if(count == userList.size()) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
				
		return count;
	}




	
	
}

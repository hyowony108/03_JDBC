package edu.kh.jdbc.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

// DT0 (Data Transfer Object - 데이터 전송 객체)
// : 값을 묶어서 전달하는 용도의 객체
// -> DB에 데이터를 묶어서 전달하거나,
// DB에서 조회한 결과를 가져올 때 사용(데이터 교환을 위한 객체)
// == DB 특정 체이블의 한 행의 데이터를 저장할 수 있는 형태로 class 작성

// lombok : 자바코드에서 반복적으로 작성해야하는 코들르 자동으로 완성해주는 라이브러리

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {

	//lombok : 자바 코드에서 반복으로 실행해야하는 코드(보일러 플레이트 등) 
	// 자동완성 해주는 라이브러리
	private int userNo;
	private String userId;
	private String userPw;
	private String userName;
	private String enrollDate;
	// -> enrollDate 를 왜 Java.sqp.Date 타입이 아닌 string으로 했는가?
	// -> DB 조회 시 날짜 데이터를 원하는 형태의 문자열로
	//	  변환하여 조회할 예정 -> To_char 이용
	
	// getter/setter
	// 생성자를
	// toString() ,,
	// 보일러 플레이트 코드
	
}

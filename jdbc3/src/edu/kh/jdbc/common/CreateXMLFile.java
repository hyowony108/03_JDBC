package edu.kh.jdbc.common;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

public class CreateXMLFile {
	
	public static void main(String[] args) {
	
		Scanner sc = new Scanner(System.in);
		
		Properties prop = new Properties();
		
		System.out.print("생성할 xml파일 이름 : ");
		String file = sc.next();
		
		try {
			FileOutputStream fos = new FileOutputStream(file + ".xml");
			
			prop.storeToXML(fos, file + ".xml file");
			
			System.out.println(file + ".xml 파일 생성 완료!");
			
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		/*
		 *  <entry key = "driver">oracle.jdbc.driver.OracleDriver</entry>
			<entry key = "url">jdbc:oracle:thin:@localhost:1521:XE</entry>
			<entry key = "ID">workbook</entry>
			<entry key = "passWord">workbook</entry>
		 * */
	}
}

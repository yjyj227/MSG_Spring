package com.board.util; //프로젝트에서 공통으로 사용되는 기능만 모아놓은 클래스

import java.io.File;

//파일 업로드시 업로드할 경로 지정 및 파일의 새 이름을 부여(공통모듈)
public class FileUtil {

	//업로드 및 다운로드 경로->정적상수
	public static final String UPLOAD_PATH="C:/webtest/4.jsp/2.back-end/sou2/MSG_Spring/src/main/webapp/upload";
	
	//1. 탐색기의 원본파일명만 받아서 처리해주는 메서드(ex test.txt)
	public static String rename(String filename) throws Exception {
		if (filename==null) return null; //업로드X->이름변경X
		//새 이름 규칙=>시스템날짜+랜덤숫자(0~49)->조합
		String newName=Long.toString(System.currentTimeMillis())+(int)(Math.random()*50);
		System.out.println("newName(난수)=>"+newName);
		return rename(filename, newName);
	}
	
	//2. 실제로 새로운 파일명을 변경하는 역할->확장자 구분->변경된 이름만 구해서
	public static String rename(String filename, String newName) throws Exception {
		if (filename==null) return null;
		//확장자 구하기(ex testdddddddd.txt)
		int idx=filename.lastIndexOf("."); //못 찾으면 -1을 리턴
		String extention=""; //확장자 저장할 변수
		String newFileName=""; //새 파일명 저장할 변수
		if (idx!=-1) {
			extention=filename.substring(idx); //idx부터 파일 끝까지
			System.out.println("extention=>"+extention);
		}
		//새 파일명
		int newIdx=newName.lastIndexOf("."); //확장자를 포함한 변경된 파일명
		if (newIdx!=-1) {
			newName=newName.substring(0, newIdx); //0은 포함, newidx 바로 앞까지
			System.out.println("newName(변경된 파일명)=>"+newName);
		}
		newFileName=newName+extention.toLowerCase(); //확장자(대)->소문자
		return newFileName; //실질적으로 업로드된 파일명
	}
	
	//3. 글수정->업로드된 파일도 수정->기존 업로드된 파일 삭제->새로 업로드
	//파일삭제->수정목적
	public static void removeFile(String filename) {
		File file=new File(UPLOAD_PATH, filename); //1. 경로  2. 파일명
		if (file.exists()) file.delete(); //이 경로에 파일이 존재한다면 삭제해라
	}
}

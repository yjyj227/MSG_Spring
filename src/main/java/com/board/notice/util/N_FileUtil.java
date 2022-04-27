package com.board.notice.util;

import java.io.File;

public class N_FileUtil {

	public static final String UPLOAD_PATH="C:/webtest/4.jsp/2.back-end/sou2/MSG_Spring/src/main/webapp/board/notice/upload";
	
	public static String rename(String not_filename) throws Exception {
		
		if (not_filename == null) return null;
		
		String newName=Long.toString(System.currentTimeMillis())+(int)(Math.random()*50);
		System.out.println("newName(난수)=>"+newName);
		return rename(not_filename, newName);
	}
	
	public static String rename(String not_filename, String newName) throws Exception {
		
		if (not_filename == null) return null;
		
		int idx=not_filename.lastIndexOf(".");
		String extention=""; //확장자 저장할 변수
		String newFileName=""; //새 파일명 저장할 변수
		if (idx != -1) {
			extention=not_filename.substring(idx);
			System.out.println("extention=>"+extention);
		}
		
		//새 파일명
		int newIdx=newName.lastIndexOf(".");
		if(newIdx != -1) {
			newName=newName.substring(0, newIdx);
			System.out.println("newName(변경된 파일명)=>"+newName);
		}
		newFileName=newName+extention.toLowerCase();
		return newFileName;
	}
	
	public static void removeFile(String not_filename) {
		File file=new File(UPLOAD_PATH, not_filename);
		if (file.exists()) file.delete();
	}
	
}

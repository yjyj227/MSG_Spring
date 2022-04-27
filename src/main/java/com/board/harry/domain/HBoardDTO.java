package com.board.harry.domain;

import java.sql.Date;
import java.sql.Timestamp;

import org.springframework.web.multipart.MultipartFile;


public class HBoardDTO {

	private int h_number;
	private String mem_id;
	private String h_nickname;
	private String h_title;
	private String h_body;
	private Timestamp h_date;
	private int h_count;
	private int h_scrap;
	private int h_category; //글카테고리->11:해리포터1, 12:해리포터2, 13:해리포터3,,,
	
	private int h_ref; //게시판분류->1:자유, 2:정보, 3:별점, 4:hot
	
	private int h_comments; //댓글 수 담을
	
	//추가
	private MultipartFile upload; //업로드할 때 필요로 하는 객체
	private String h_filename; //업로드한 파일명
	
	
	
	public int getH_number() {
		return h_number;
	}
	public void setH_number(int h_number) {
		this.h_number = h_number;
	}
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = convert(mem_id);
	}
	public String getH_nickname() {
		return h_nickname;
	}
	public void setH_nickname(String h_nickname) {
		this.h_nickname = convert(h_nickname);
	}
	public String getH_title() {
		return h_title;
	}
	public void setH_title(String h_title) {
		this.h_title = convert(h_title);
	}
	public String getH_body() {
		return h_body;
	}
	public void setH_body(String h_body) {
		this.h_body = convert(h_body);
	}
	public Timestamp getH_date() {
		return h_date;
	}
	public void setH_date(Timestamp h_date) {
		this.h_date = h_date;
	}
	public int getH_count() {
		return h_count;
	}
	public void setH_count(int h_count) {
		this.h_count = h_count;
	}
	public int getH_scrap() {
		return h_scrap;
	}
	public void setH_scrap(int h_scrap) {
		this.h_scrap = h_scrap;
	}
	public int getH_category() {
		return h_category;
	}
	public void setH_category(int h_category) {
		this.h_category = h_category;
	}
	public int getH_ref() {
		return h_ref;
	}
	public void setH_ref(int h_ref) {
		this.h_ref = h_ref;
	}
	public int getH_comments() {
		return h_comments;
	}
	public void setH_comments(int h_comments) {
		this.h_comments = h_comments;
	}
	public MultipartFile getUpload() {
		return upload;
	}
	public void setUpload(MultipartFile upload) {
		this.upload = upload;
	}
	public String getH_filename() {
		return h_filename;
	}
	public void setH_filename(String h_filename) {
		this.h_filename = h_filename;
	}
	
	private static String convert(String name) {
		if(name!=null){
	    	name=name.replaceAll("<","&lt");
	    	name=name.replaceAll(">","&gt");
	    	
	    	name=name.replaceAll("\\(","&#40");
	    	name=name.replaceAll("\\)","&#41");
	    	
	    	name=name.replaceAll("\"","&quot");
	    	name=name.replaceAll("\'","&apos");
	    }else{
	    	return null;
	    }
		return name;
	}
}

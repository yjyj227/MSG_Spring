package com.board.zzang.domain;

import java.sql.Timestamp;

import org.springframework.web.multipart.MultipartFile;

public class ZBoardDTO {

	private int z_number;
	private String mem_id;
	private String z_nickname;
	private String z_title;
	private String z_body;
	private Timestamp z_date;
	private int z_count;
	private int z_scrap;
	private int z_category; //글카테고리->11:해리포터1, 12:해리포터2, 13:해리포터3,,,
	private int z_ref; //게시판분류->1:자유, 2:정보, 3:별점, 4:hot
	
	private int z_comments; //댓글 수 담을
	
	//추가
	private MultipartFile upload; //업로드할 때 필요로 하는 객체
	private String z_filename; //업로드한 파일명

	public int getZ_number() {
		return z_number;
	}

	public void setZ_number(int z_number) {
		this.z_number = z_number;
	}

	public String getMem_id() {
		return mem_id;
	}

	public void setMem_id(String mem_id) {
		this.mem_id = convert(mem_id);
	}

	public String getZ_nickname() {
		return z_nickname;
	}

	public void setZ_nickname(String z_nickname) {
		this.z_nickname = convert(z_nickname);
	}

	public String getZ_title() {
		return z_title;
	}

	public void setZ_title(String z_title) {
		this.z_title = convert(z_title);
	}

	public String getZ_body() {
		return z_body;
	}

	public void setZ_body(String z_body) {
		this.z_body = convert(z_body);
	}

	public Timestamp getZ_date() {
		return z_date;
	}

	public void setZ_date(Timestamp z_date) {
		this.z_date = z_date;
	}

	public int getZ_count() {
		return z_count;
	}

	public void setZ_count(int z_count) {
		this.z_count = z_count;
	}

	public int getZ_scrap() {
		return z_scrap;
	}

	public void setZ_scrap(int z_scrap) {
		this.z_scrap = z_scrap;
	}

	public int getZ_category() {
		return z_category;
	}

	public void setZ_category(int z_category) {
		this.z_category = z_category;
	}

	public int getZ_ref() {
		return z_ref;
	}

	public void setZ_ref(int z_ref) {
		this.z_ref = z_ref;
	}

	public int getZ_comments() {
		return z_comments;
	}

	public void setZ_comments(int z_comments) {
		this.z_comments = z_comments;
	}

	
	
	public MultipartFile getUpload() {
		return upload;
	}

	public void setUpload(MultipartFile upload) {
		this.upload = upload;
	}

	public String getZ_filename() {
		return z_filename;
	}

	public void setZ_filename(String z_filename) {
		this.z_filename = z_filename;
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

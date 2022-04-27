package com.board.notice.domain;

import java.sql.Timestamp;

import org.springframework.web.multipart.MultipartFile;

public class NoticeDTO {

	private int notice_number;
	private String admin_id;
	private String not_title;
	private String not_body;
	private Timestamp not_date;
	private int not_count; //조회수
	
	private MultipartFile upload;
	private String not_filename;
	
	
	public int getNotice_number() {
		return notice_number;
	}
	public String getAdmin_id() {
		return admin_id;
	}
	public String getNot_title() {
		return not_title;
	}
	public String getNot_body() {
		return not_body;
	}
	public Timestamp getNot_date() {
		return not_date;
	}
	public int getNot_count() {
		return not_count;
	}
	public void setNotice_number(int notice_number) {
		this.notice_number = notice_number;
	}
	public void setAdmin_id(String admin_id) {
		this.admin_id = convert(admin_id);
	}
	public void setNot_title(String not_title) {
		this.not_title = convert(not_title);
	}
	public void setNot_body(String not_body) {
		this.not_body = convert(not_body);
	}
	public void setNot_date(Timestamp not_date) {
		this.not_date = not_date;
	}
	public void setNot_count(int not_count) {
		this.not_count = not_count;
	}
	public MultipartFile getUpload() {
		return upload;
	}
	public void setUpload(MultipartFile upload) {
		this.upload = upload;
	}
	public String getNot_filename() {
		return not_filename;
	}
	public void setNot_filename(String not_filename) {
		this.not_filename = not_filename;
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

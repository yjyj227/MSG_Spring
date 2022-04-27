package com.member.domain;

public class MemberDTO {

	private String mem_id;
	private String mem_passwd;
	private String log_passwd;
	private String mem_name;
	private String mem_nickname;
	private String log_nickname;
	private String mem_birth;
	private String mem_tel;
	private String mem_email;
	private String mem_addr;
	private String mem_genre;
	private int mem_point;
	private int log_point;
	private int mem_grade;
	private int log_grade;
	
	
	
	public String getLog_nickname() {
		return log_nickname;
	}
	public void setLog_nickname(String log_nickname) {
		this.log_nickname = convert(log_nickname);
	}
	public int getLog_point() {
		return log_point;
	}
	public void setLog_point(int log_point) {
		this.log_point = log_point;
	}
	public int getLog_grade() {
		return log_grade;
	}
	public void setLog_grade(int log_grade) {
		this.log_grade = log_grade;
	}
	public String getLog_passwd() {
		return log_passwd;
	}
	public void setLog_passwd(String log_passwd) {
		this.log_passwd = convert(log_passwd);
	}
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = convert(mem_id);
	}
	public String getMem_passwd() {
		return mem_passwd;
	}
	public void setMem_passwd(String mem_passwd) {
		this.mem_passwd = convert(mem_passwd);
	}
	public String getMem_name() {
		return mem_name;
	}
	public void setMem_name(String mem_name) {
		this.mem_name = convert(mem_name);
	}
	public String getMem_nickname() {
		return mem_nickname;
	}
	public void setMem_nickname(String mem_nickname) {
		this.mem_nickname = convert(mem_nickname);
	}
	public String getMem_birth() {
		return mem_birth;
	}
	public void setMem_birth(String mem_birth) {
		this.mem_birth = convert(mem_birth);
	}
	public String getMem_tel() {
		return mem_tel;
	}
	public void setMem_tel(String mem_tel) {
		this.mem_tel = convert(mem_tel);
	}
	public String getMem_email() {
		return mem_email;
	}
	public void setMem_email(String mem_email) {
		this.mem_email = convert(mem_email);
	}
	public String getMem_addr() {
		return mem_addr;
	}
	public void setMem_addr(String mem_addr) {
		this.mem_addr = convert(mem_addr);
	}
	public String getMem_genre() {
		return mem_genre;
	}
	public void setMem_genre(String mem_genre) {
		this.mem_genre = convert(mem_genre);
	}
	public int getMem_point() {
		return mem_point;
	}
	public void setMem_point(int mem_point) {
		this.mem_point = mem_point;
	}
	public int getMem_grade() {
		return mem_grade;
	}
	public void setMem_grade(int mem_grade) {
		this.mem_grade = mem_grade;
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

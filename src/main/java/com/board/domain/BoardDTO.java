package com.board.domain;

import java.sql.*;

public class BoardDTO {

	private int number;
	private String mem_id;
	private String nickname;
	private String title;
	private String body;
	
	private Timestamp date;
	private int count;
	private int scrap;
	private int category; //글 카테고리
	private int ref; //게시판 분류
	private int comments;
	
	//-------scrap
	private int scrap_number;
	private int s_number;
	private int s_category;
	private String s_title;
	private Timestamp s_date;
	private String s_url;
	private String s_nickname;
	private String init;
	
	//-------rank
	private int v_number;
	private int v_movie;
	private double v_star;
	private int v_like;
	
	
	
	public String getInit() {
		return init;
	}
	public void setInit(String init) {
		this.init = init;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp date) {
		this.date = date;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getScrap() {
		return scrap;
	}
	public void setScrap(int scrap) {
		this.scrap = scrap;
	}
	public int getCategory() {
		return category;
	}
	public void setCategory(int category) {
		this.category = category;
	}
	public int getRef() {
		return ref;
	}
	public void setRef(int ref) {
		this.ref = ref;
	}
	public int getComments() {
		return comments;
	}
	public void setComments(int comments) {
		this.comments = comments;
	}
	public int getScrap_number() {
		return scrap_number;
	}
	public void setScrap_number(int scrap_number) {
		this.scrap_number = scrap_number;
	}
	public int getS_number() {
		return s_number;
	}
	public void setS_number(int s_number) {
		this.s_number = s_number;
	}
	public int getS_category() {
		return s_category;
	}
	public void setS_category(int s_category) {
		this.s_category = s_category;
	}
	public String getS_title() {
		return s_title;
	}
	public void setS_title(String s_title) {
		this.s_title = s_title;
	}
	public Timestamp getS_date() {
		return s_date;
	}
	public void setS_date(Timestamp s_date) {
		this.s_date = s_date;
	}
	public String getS_url() {
		return s_url;
	}
	public void setS_url(String s_url) {
		this.s_url = s_url;
	}
	public String getS_nickname() {
		return s_nickname;
	}
	public void setS_nickname(String s_nickname) {
		this.s_nickname = s_nickname;
	}
	public int getV_number() {
		return v_number;
	}
	public void setV_number(int v_number) {
		this.v_number = v_number;
	}
	public int getV_movie() {
		return v_movie;
	}
	public void setV_movie(int v_movie) {
		this.v_movie = v_movie;
	}
	public double getV_star() {
		return v_star;
	}
	public void setV_star(double v_star) {
		this.v_star = v_star;
	}
	public int getV_like() {
		return v_like;
	}
	public void setV_like(int v_like) {
		this.v_like = v_like;
	}
	
	
	
}

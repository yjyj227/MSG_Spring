package com.board.ghibli.domain;

import java.util.Date;
import java.sql.Timestamp;

public class GCommentDTO {
	private int g_cnumber;
	private String mem_id;
	private int g_number;
	private String g_cbody;
	private String g_cnickname;
	private Timestamp g_cdate;
	
	private int g_cref;
	private int g_cre_step;
	private int g_cre_level;
	public int getG_cnumber() {
		return g_cnumber;
	}
	public void setG_cnumber(int g_cnumber) {
		this.g_cnumber = g_cnumber;
	}
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public int getG_number() {
		return g_number;
	}
	public void setG_number(int g_number) {
		this.g_number = g_number;
	}
	public String getG_cbody() {
		return g_cbody;
	}
	public void setG_cbody(String g_cbody) {
		this.g_cbody = g_cbody;
	}
	public String getG_cnickname() {
		return g_cnickname;
	}
	public void setG_cnickname(String g_cnickname) {
		this.g_cnickname = g_cnickname;
	}
	public Timestamp getG_cdate() {
		return g_cdate;
	}
	public void setG_cdate(Timestamp g_cdate) {
		this.g_cdate = g_cdate;
	}
	public int getG_cref() {
		return g_cref;
	}
	public void setG_cref(int g_cref) {
		this.g_cref = g_cref;
	}
	public int getG_cre_step() {
		return g_cre_step;
	}
	public void setG_cre_step(int g_cre_step) {
		this.g_cre_step = g_cre_step;
	}
	public int getG_cre_level() {
		return g_cre_level;
	}
	public void setG_cre_level(int g_cre_level) {
		this.g_cre_level = g_cre_level;
	}
	
	
	
	
	
}

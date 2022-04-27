package com.board.ring.domain;

import java.util.Date;
import java.sql.Timestamp;

public class RCommentDTO {
	private int r_cnumber;
	private String mem_id;
	private int r_number;
	private String r_cbody;
	private String r_cnickname;
	private Timestamp r_cdate;
	
	private int r_cref;
	private int r_cre_step;
	private int r_cre_level;
	public int getR_cnumber() {
		return r_cnumber;
	}
	public void setR_cnumber(int r_cnumber) {
		this.r_cnumber = r_cnumber;
	}
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = convert(mem_id);
	}
	public int getR_number() {
		return r_number;
	}
	public void setR_number(int r_number) {
		this.r_number = r_number;
	}
	public String getR_cbody() {
		return r_cbody;
	}
	public void setR_cbody(String r_cbody) {
		this.r_cbody = convert(r_cbody);
	}
	public String getR_cnickname() {
		return r_cnickname;
	}
	public void setR_cnickname(String r_cnickname) {
		this.r_cnickname = convert(r_cnickname);
	}
	public Timestamp getR_cdate() {
		return r_cdate;
	}
	public void setR_cdate(Timestamp r_cdate) {
		this.r_cdate = r_cdate;
	}
	public int getR_cref() {
		return r_cref;
	}
	public void setR_cref(int r_cref) {
		this.r_cref = r_cref;
	}
	public int getR_cre_step() {
		return r_cre_step;
	}
	public void setR_cre_step(int r_cre_step) {
		this.r_cre_step = r_cre_step;
	}
	public int getR_cre_level() {
		return r_cre_level;
	}
	public void setR_cre_level(int r_cre_level) {
		this.r_cre_level = r_cre_level;
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

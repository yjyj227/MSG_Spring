package com.board.harry.domain;

import java.sql.Timestamp;

public class HCommentDTO {

	private int h_cnumber;
	private String mem_id;
	private int h_number;
	private String h_cbody;
	private String h_cnickname;
	private Timestamp h_cdate;
	
	private int h_cref;
	private int h_cre_step;
	private int h_cre_level;
	
	private int h_commentcount;

	
	
	public int getH_cnumber() {
		return h_cnumber;
	}

	public void setH_cnumber(int h_cnumber) {
		this.h_cnumber = h_cnumber;
	}

	public String getMem_id() {
		return mem_id;
	}

	public void setMem_id(String mem_id) {
		this.mem_id = convert(mem_id);
	}

	public int getH_number() {
		return h_number;
	}

	public void setH_number(int h_number) {
		this.h_number = h_number;
	}

	public String getH_cbody() {
		return h_cbody;
	}

	public void setH_cbody(String h_cbody) {
		this.h_cbody = convert(h_cbody);
	}

	public String getH_cnickname() {
		return h_cnickname;
	}

	public void setH_cnickname(String h_cnickname) {
		this.h_cnickname = convert(h_cnickname);
	}

	public Timestamp getH_cdate() {
		return h_cdate;
	}

	public void setH_cdate(Timestamp h_cdate) {
		this.h_cdate = h_cdate;
	}

	public int getH_cref() {
		return h_cref;
	}

	public void setH_cref(int h_cref) {
		this.h_cref = h_cref;
	}

	public int getH_cre_step() {
		return h_cre_step;
	}

	public void setH_cre_step(int h_cre_step) {
		this.h_cre_step = h_cre_step;
	}

	public int getH_cre_level() {
		return h_cre_level;
	}

	public void setH_cre_level(int h_cre_level) {
		this.h_cre_level = h_cre_level;
	}

	public int getH_commentcount() {
		return h_commentcount;
	}

	public void setH_commentcount(int h_commentcount) {
		this.h_commentcount = h_commentcount;
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

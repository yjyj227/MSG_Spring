package com.board.marvel.domain;

import java.util.Date;
import java.sql.Timestamp;

public class MCommentDTO {
	private int m_cnumber;
	private String mem_id;
	private int m_number;
	private String m_cbody;
	private String m_cnickname;
	private Timestamp m_cdate;
	
	private int m_cref;
	private int m_cre_step;
	private int m_cre_level;
	public int getM_cnumber() {
		return m_cnumber;
	}
	public void setM_cnumber(int m_cnumber) {
		this.m_cnumber = m_cnumber;
	}
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = convert(mem_id);
	}
	public int getM_number() {
		return m_number;
	}
	public void setM_number(int m_number) {
		this.m_number = m_number;
	}
	public String getM_cbody() {
		return m_cbody;
	}
	public void setM_cbody(String m_cbody) {
		this.m_cbody = convert(m_cbody);
	}
	public String getM_cnickname() {
		return m_cnickname;
	}
	public void setM_cnickname(String m_cnickname) {
		this.m_cnickname = convert(m_cnickname);
	}
	public Timestamp getM_cdate() {
		return m_cdate;
	}
	public void setM_cdate(Timestamp m_cdate) {
		this.m_cdate = m_cdate;
	}
	public int getM_cref() {
		return m_cref;
	}
	public void setM_cref(int m_cref) {
		this.m_cref = m_cref;
	}
	public int getM_cre_step() {
		return m_cre_step;
	}
	public void setM_cre_step(int m_cre_step) {
		this.m_cre_step = m_cre_step;
	}
	public int getM_cre_level() {
		return m_cre_level;
	}
	public void setM_cre_level(int m_cre_level) {
		this.m_cre_level = m_cre_level;
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

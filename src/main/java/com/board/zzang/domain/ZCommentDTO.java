package com.board.zzang.domain;

import java.util.Date;
import java.sql.Timestamp;

public class ZCommentDTO {
	private int z_cnumber;
	private String mem_id;
	private int z_number;
	private String z_cbody;
	private String z_cnickname;
	private Timestamp z_cdate;
	
	private int z_cref;
	private int z_cre_step;
	private int z_cre_level;
	public int getZ_cnumber() {
		return z_cnumber;
	}
	public void setZ_cnumber(int z_cnumber) {
		this.z_cnumber = z_cnumber;
	}
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = convert(mem_id);
	}
	public int getZ_number() {
		return z_number;
	}
	public void setZ_number(int z_number) {
		this.z_number = z_number;
	}
	public String getZ_cbody() {
		return z_cbody;
	}
	public void setZ_cbody(String z_cbody) {
		this.z_cbody = convert(z_cbody);
	}
	public String getZ_cnickname() {
		return z_cnickname;
	}
	public void setZ_cnickname(String z_cnickname) {
		this.z_cnickname = convert(z_cnickname);
	}
	public Timestamp getZ_cdate() {
		return z_cdate;
	}
	public void setZ_cdate(Timestamp z_cdate) {
		this.z_cdate = z_cdate;
	}
	public int getZ_cref() {
		return z_cref;
	}
	public void setZ_cref(int z_cref) {
		this.z_cref = z_cref;
	}
	public int getZ_cre_step() {
		return z_cre_step;
	}
	public void setZ_cre_step(int z_cre_step) {
		this.z_cre_step = z_cre_step;
	}
	public int getZ_cre_level() {
		return z_cre_level;
	}
	public void setZ_cre_level(int z_cre_level) {
		this.z_cre_level = z_cre_level;
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

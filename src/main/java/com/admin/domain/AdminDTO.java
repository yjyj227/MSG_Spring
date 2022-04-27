package com.admin.domain;

public class AdminDTO {

	private String admin_id;
	private String admin_passwd;
	private String admin_name;
	private String admin_nickname;
	
	
	public String getAdmin_id() {
		return admin_id;
	}
	public void setAdmin_id(String admin_id) {
		this.admin_id = convert(admin_id);
	}
	public String getAdmin_passwd() {
		return admin_passwd;
	}
	public void setAdmin_passwd(String admin_passwd) {
		this.admin_passwd = convert(admin_passwd);
	}
	public String getAdmin_name() {
		return admin_name;
	}
	public void setAdmin_name(String admin_name) {
		this.admin_name = convert(admin_name);
	}
	public String getAdmin_nickname() {
		return admin_nickname;
	}
	public void setAdmin_nickname(String admin_nickname) {
		this.admin_nickname = convert(admin_nickname);
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

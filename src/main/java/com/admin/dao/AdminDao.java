package com.admin.dao;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import com.admin.domain.AdminDTO;
import com.member.domain.MemberDTO;

public interface AdminDao {

	public boolean adminLoginCheck(AdminDTO admin);
	public String getAdminN(String admin_id);
	
	public Hashtable pageList(String pageNum, int count);
	public int getMemberCount(Map<String, Object> map);
	public List<MemberDTO> getMembers(Map<String, Object> map);
	
	public MemberDTO getMember_admin(String mem_id);
	
	public int memberModify(MemberDTO mem);
	public int loginModify(MemberDTO mem);
	
	public int syncPG_admin(MemberDTO mem);
	
	
	public int beforeQuit_admin(String mem_id);
	public int matchPw_admin(String id, String passwd);
	public int memberDelete(String mem_id);
	
}

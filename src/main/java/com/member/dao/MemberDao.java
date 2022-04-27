package com.member.dao;

import java.util.List;
import java.util.Map;

import com.member.domain.MemberDTO;

public interface MemberDao {

	//1-1. 회원 로그인
	public boolean loginCheck(MemberDTO mem);
	
	//1-2. 닉네임, 포인트, 등급 세션처리
	public MemberDTO getNPG(String mem_id);
	
	//2. id 중복체크
	public int checkId(String id);
	
	//3. 닉네임 중복체크
	public int checkNickname(String nickname);
	
	//4. 회원가입
	public int memberInsert(MemberDTO mem);
	public int loginInsert(MemberDTO mem);
	
	//5. 포인트, 등급 동기화
	public MemberDTO selectPG(String mem_id);
	public int syncPG(MemberDTO mem);
	
	//6. 회원수정->특정 회원 찾기
	public MemberDTO getMember(String mem_id);
	
	//7. 찾은 회원을 수정->회원가입 메서드와 동일
	public int memberUpdate(MemberDTO mem);
	public int loginUpdate(MemberDTO mem);
	
	//8-1. 회원탈퇴 전 정보 지우기
	public int beforeQuit(String id);
	
	//8. 회원탈퇴
	public int matchPw(String id, String passwd);
	public int quit(String id);
	
	
}

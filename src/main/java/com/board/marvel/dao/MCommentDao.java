package com.board.marvel.dao;

import java.util.List;
import java.util.Map;

import com.board.marvel.domain.MCommentDTO;

public interface MCommentDao {

	//1. 댓글 조회 getComment()
	public List<MCommentDTO> getComment(Integer m_number);
	
	//2. 댓글 수 getCommentCount()
	public int getCommentCount(Integer m_number);
	
	//3-0-1. 최댓값 구하기
	public int getNewCommentNum();
	//3-0-2. step값 설정...
	public int getSuperStep(Integer m_cref);
	//3-1. 댓글 작성
	public int insertComment(MCommentDTO comment);
	//3-2. 댓글 작성 성공시 포인트 적립
	public int pointupC(String mem_id);
	
	
	public int lowerComment(MCommentDTO comment);
	//4-1-1. 댓글 수정 삭제   글수정 비슷하게 생각해야 할듯
	public int upDeleteComment(Integer m_cnumber);
	//4-1-2. 댓글 진짜 삭제
	public int deleteComment(Integer m_cnumber);
	//4-2. 댓글 삭제 성공시 포인트 회수
	public int pointdownC(String mem_id);
	
	//5. 댓글 수정    가능하다면
	public void updateComment(MCommentDTO comment);
}

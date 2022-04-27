package com.board.harry.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Service;

import com.board.harry.domain.HCommentDTO;

@Service("hCommentDaoImpl")
public class HCommentDaoImpl extends SqlSessionDaoSupport implements HCommentDao {

	//1. 댓글 조회 getComment()
	@Override
	public List<HCommentDTO> getComment(Integer h_number) {
		// TODO Auto-generated method stub
		System.out.println("getComment() 호출됨");
		List<HCommentDTO> commentList=getSqlSession().selectList("getComment_H", h_number);
		return commentList;
	}

	//2. 댓글 수 getCommentCount()
	@Override
	public int getCommentCount(Integer h_number) {
		// TODO Auto-generated method stub
		System.out.println("getCommentCount() 호출됨");
		return getSqlSession().selectOne("getCommentCount_H", h_number);
	}

	//3-0-1. 최댓값 구하기
	@Override
	public int getNewCommentNum() {
		// TODO Auto-generated method stub
		int newnum=0;
		if ((Integer)getSqlSession().selectOne("getNewCommentNum_H") != null) {
			newnum=(Integer)getSqlSession().selectOne("getNewCommentNum_H");
		}
		System.out.println("getNewNum()의 newnum=>"+newnum);
		return newnum;
	}
	
	//3-0-2.
	@Override
	public int getSuperStep(Integer h_cref) {
		// TODO Auto-generated method stub
		return (Integer)getSqlSession().selectOne("getSuperStep_H", h_cref);
	}
	
	//3-1. 댓글 작성
	@Override
	public int insertComment(HCommentDTO comment) {
		// TODO Auto-generated method stub
		return getSqlSession().insert("insertComment_H", comment);
	}

	//3-2. 댓글 작성 성공시 포인트 적립
	@Override
	public int pointupC(String mem_id) {
		// TODO Auto-generated method stub
		return getSqlSession().update("pointupC_H", mem_id);
	}

	
	@Override
	public int lowerComment(HCommentDTO comment) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne("lowerComment_H", comment);
	}
	
	//4-1-1. 댓글 수정 삭제   글수정 비슷하게 생각해야 할듯
	@Override
	public int upDeleteComment(Integer h_cnumber) {
		// TODO Auto-generated method stub
		return getSqlSession().update("upDeleteComment_H", h_cnumber);
	}
	
	//4-1-2. 댓글 진짜 삭제
	@Override
	public int deleteComment(Integer h_cnumber) {
		// TODO Auto-generated method stub
		return getSqlSession().delete("deleteComment_H", h_cnumber);
	}

	//4-2. 댓글 삭제 성공시 포인트 회수
	@Override
	public int pointdownC(String mem_id) {
		// TODO Auto-generated method stub
		return getSqlSession().update("pointdownC_H", mem_id);
	}

	//5. 댓글 수정    가능하다면
	@Override
	public void updateComment(HCommentDTO comment) {
		// TODO Auto-generated method stub

	}

}

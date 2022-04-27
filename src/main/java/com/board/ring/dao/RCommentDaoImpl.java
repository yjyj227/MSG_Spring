package com.board.ring.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Service;

import com.board.ring.domain.RCommentDTO;

@Service("rCommentDaoImpl")
public class RCommentDaoImpl extends SqlSessionDaoSupport implements RCommentDao {

	//1. 댓글 조회 getComment()
	@Override
	public List<RCommentDTO> getComment(Integer r_number) {
		// TODO Auto-generated method stub
		System.out.println("getComment() 호출됨");
		List<RCommentDTO> commentList=getSqlSession().selectList("getComment_R", r_number);
		return commentList;
	}

	//2. 댓글 수 getCommentCount()
	@Override
	public int getCommentCount(Integer r_number) {
		// TODO Auto-generated method stub
		System.out.println("getCommentCount() 호출됨");
		return getSqlSession().selectOne("getCommentCount_R", r_number);
	}

	//3-0-1. 최댓값 구하기
	@Override
	public int getNewCommentNum() {
		// TODO Auto-generated method stub
		int newnum=0;
		if ((Integer)getSqlSession().selectOne("getNewCommentNum_R") != null) {
			newnum=(Integer)getSqlSession().selectOne("getNewCommentNum_R");
		}
		System.out.println("getNewNum()의 newnum=>"+newnum);
		return newnum;
	}
	
	//3-0-2.
	@Override
	public int getSuperStep(Integer r_cref) {
		// TODO Auto-generated method stub
		return (Integer)getSqlSession().selectOne("getSuperStep_R", r_cref);
	}
	
	//3-1. 댓글 작성
	@Override
	public int insertComment(RCommentDTO comment) {
		// TODO Auto-generated method stub
		return getSqlSession().insert("insertComment_R", comment);
	}

	//3-2. 댓글 작성 성공시 포인트 적립
	@Override
	public int pointupC(String mem_id) {
		// TODO Auto-generated method stub
		return getSqlSession().update("pointupC_R", mem_id);
	}

	
	@Override
	public int lowerComment(RCommentDTO comment) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne("lowerComment_R", comment);
	}
	
	//4-1-1. 댓글 수정 삭제   글수정 비슷하게 생각해야 할듯
	@Override
	public int upDeleteComment(Integer r_cnumber) {
		// TODO Auto-generated method stub
		return getSqlSession().update("upDeleteComment_R", r_cnumber);
	}
	
	//4-1-2. 댓글 진짜 삭제
	@Override
	public int deleteComment(Integer r_cnumber) {
		// TODO Auto-generated method stub
		return getSqlSession().delete("deleteComment_R", r_cnumber);
	}

	//4-2. 댓글 삭제 성공시 포인트 회수
	@Override
	public int pointdownC(String mem_id) {
		// TODO Auto-generated method stub
		return getSqlSession().update("pointdownC_R", mem_id);
	}

	//5. 댓글 수정    가능하다면
	@Override
	public void updateComment(RCommentDTO comment) {
		// TODO Auto-generated method stub

	}

}

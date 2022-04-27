package com.board.marvel.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Service;

import com.board.marvel.domain.MCommentDTO;

@Service("mCommentDaoImpl")
public class MCommentDaoImpl extends SqlSessionDaoSupport implements MCommentDao {

	//1. 댓글 조회 getComment()
	@Override
	public List<MCommentDTO> getComment(Integer m_number) {
		// TODO Auto-generated method stub
		System.out.println("getComment() 호출됨");
		List<MCommentDTO> commentList=getSqlSession().selectList("getComment_M", m_number);
		return commentList;
	}

	//2. 댓글 수 getCommentCount()
	@Override
	public int getCommentCount(Integer m_number) {
		// TODO Auto-generated method stub
		System.out.println("getCommentCount() 호출됨");
		return getSqlSession().selectOne("getCommentCount_M", m_number);
	}

	//3-0-1. 최댓값 구하기
	@Override
	public int getNewCommentNum() {
		// TODO Auto-generated method stub
		int newnum=0;
		if ((Integer)getSqlSession().selectOne("getNewCommentNum_M") != null) {
			newnum=(Integer)getSqlSession().selectOne("getNewCommentNum_M");
		}
		System.out.println("getNewNum()의 newnum=>"+newnum);
		return newnum;
	}
	
	//3-0-2.
	@Override
	public int getSuperStep(Integer m_cref) {
		// TODO Auto-generated method stub
		return (Integer)getSqlSession().selectOne("getSuperStep_M", m_cref);
	}
	
	//3-1. 댓글 작성
	@Override
	public int insertComment(MCommentDTO comment) {
		// TODO Auto-generated method stub
		return getSqlSession().insert("insertComment_M", comment);
	}

	//3-2. 댓글 작성 성공시 포인트 적립
	@Override
	public int pointupC(String mem_id) {
		// TODO Auto-generated method stub
		return getSqlSession().update("pointupC_M", mem_id);
	}

	
	@Override
	public int lowerComment(MCommentDTO comment) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne("lowerComment_M", comment);
	}
	
	//4-1-1. 댓글 수정 삭제   글수정 비슷하게 생각해야 할듯
	@Override
	public int upDeleteComment(Integer m_cnumber) {
		// TODO Auto-generated method stub
		return getSqlSession().update("upDeleteComment_M", m_cnumber);
	}
	
	//4-1-2. 댓글 진짜 삭제
	@Override
	public int deleteComment(Integer m_cnumber) {
		// TODO Auto-generated method stub
		return getSqlSession().delete("deleteComment_M", m_cnumber);
	}

	//4-2. 댓글 삭제 성공시 포인트 회수
	@Override
	public int pointdownC(String mem_id) {
		// TODO Auto-generated method stub
		return getSqlSession().update("pointdownC_M", mem_id);
	}

	//5. 댓글 수정    가능하다면
	@Override
	public void updateComment(MCommentDTO comment) {
		// TODO Auto-generated method stub

	}

}

package com.board.zzang.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Service;

import com.board.zzang.domain.ZCommentDTO;

@Service("zCommentDaoImpl")
public class ZCommentDaoImpl extends SqlSessionDaoSupport implements ZCommentDao {

	//1. 댓글 조회 getComment()
	@Override
	public List<ZCommentDTO> getComment(Integer z_number) {
		// TODO Auto-generated method stub
		System.out.println("getComment() 호출됨");
		List<ZCommentDTO> commentList=getSqlSession().selectList("getComment_Z", z_number);
		return commentList;
	}

	//2. 댓글 수 getCommentCount()
	@Override
	public int getCommentCount(Integer z_number) {
		// TODO Auto-generated method stub
		System.out.println("getCommentCount() 호출됨");
		return getSqlSession().selectOne("getCommentCount_Z", z_number);
	}

	//3-0-1. 최댓값 구하기
	@Override
	public int getNewCommentNum() {
		// TODO Auto-generated method stub
		int newnum=0;
		if ((Integer)getSqlSession().selectOne("getNewCommentNum_Z") != null) {
			newnum=(Integer)getSqlSession().selectOne("getNewCommentNum_Z");
		}
		System.out.println("getNewNum()의 newnum=>"+newnum);
		return newnum;
	}
	
	//3-0-2.
	@Override
	public int getSuperStep(Integer z_cref) {
		// TODO Auto-generated method stub
		return (Integer)getSqlSession().selectOne("getSuperStep_Z", z_cref);
	}
	
	//3-1. 댓글 작성
	@Override
	public int insertComment(ZCommentDTO comment) {
		// TODO Auto-generated method stub
		return getSqlSession().insert("insertComment_Z", comment);
	}

	//3-2. 댓글 작성 성공시 포인트 적립
	@Override
	public int pointupC(String mem_id) {
		// TODO Auto-generated method stub
		return getSqlSession().update("pointupC_Z", mem_id);
	}

	
	@Override
	public int lowerComment(ZCommentDTO comment) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne("lowerComment_Z", comment);
	}
	
	//4-1-1. 댓글 수정 삭제   글수정 비슷하게 생각해야 할듯
	@Override
	public int upDeleteComment(Integer z_cnumber) {
		// TODO Auto-generated method stub
		return getSqlSession().update("upDeleteComment_Z", z_cnumber);
	}
	
	//4-1-2. 댓글 진짜 삭제
	@Override
	public int deleteComment(Integer z_cnumber) {
		// TODO Auto-generated method stub
		return getSqlSession().delete("deleteComment_Z", z_cnumber);
	}

	//4-2. 댓글 삭제 성공시 포인트 회수
	@Override
	public int pointdownC(String mem_id) {
		// TODO Auto-generated method stub
		return getSqlSession().update("pointdownC_Z", mem_id);
	}

	//5. 댓글 수정    가능하다면
	@Override
	public void updateComment(ZCommentDTO comment) {
		// TODO Auto-generated method stub

	}

}

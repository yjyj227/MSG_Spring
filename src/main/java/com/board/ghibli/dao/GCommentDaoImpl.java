package com.board.ghibli.dao;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Service;

import com.board.ghibli.domain.GCommentDTO;

@Service("gCommentDaoImpl")
public class GCommentDaoImpl extends SqlSessionDaoSupport implements GCommentDao {

	@Override
	public List<GCommentDTO> getComment(Integer g_number) {
		// TODO Auto-generated method stub
		List<GCommentDTO> commentList=getSqlSession().selectList("getComment_G", g_number);
		return commentList;
	}

	@Override
	public int getCommentCount(Integer g_number) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne("getCommentCount_G", g_number);
	}

	@Override
	public int getNewCommentNum() {
		// TODO Auto-generated method stub
		int newnum=0;
		if ((Integer)getSqlSession().selectOne("getNewCommentNum_G") != null) {
			newnum=(Integer)getSqlSession().selectOne("getNewCommentNum_G");
		}
		System.out.println("getNewNum()의 newnum=>"+newnum);
		return newnum;
	}

	@Override
	public int getSuperStep(Integer g_cref) {
		// TODO Auto-generated method stub
		return (Integer)getSqlSession().selectOne("getSuperStep_G", g_cref);
	}

	@Override
	public int insertComment(GCommentDTO comment) {
		// TODO Auto-generated method stub
		return getSqlSession().insert("insertComment_G", comment);
	}

	@Override
	public int pointupC(String mem_id) {
		// TODO Auto-generated method stub
		return getSqlSession().update("pointupC_G", mem_id);
	}

	@Override
	public int lowerComment(GCommentDTO comment) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne("lowerComment_G", comment);
	}

	@Override
	public int upDeleteComment(Integer g_cnumber) {
		// TODO Auto-generated method stub
		return getSqlSession().update("upDeleteComment_G", g_cnumber);
	}

	@Override
	public int deleteComment(Integer g_cnumber) {
		// TODO Auto-generated method stub
		return getSqlSession().delete("deleteComment_G", g_cnumber);
	}

	@Override
	public int pointdownC(String mem_id) {
		// TODO Auto-generated method stub
		return getSqlSession().update("pointdownC_G", mem_id);
	}

	@Override
	public void updateComment(GCommentDTO comment) {
		// TODO Auto-generated method stub

	}

}

package com.member.dao;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Service;

import com.member.domain.MemberDTO;

@Service("memberDaoImpl")
public class MemberDaoImpl extends SqlSessionDaoSupport implements MemberDao {

	@Override
	public boolean loginCheck(MemberDTO mem) {
		// TODO Auto-generated method stub
		boolean check=getSqlSession().selectOne("loginCheck", mem);
		return check;
	}

	@Override
	public MemberDTO getNPG(String mem_id) {
		// TODO Auto-generated method stub
		return (MemberDTO)getSqlSession().selectOne("getNPG", mem_id);
	}

	@Override
	public int checkId(String id) {
		// TODO Auto-generated method stub
		int check=getSqlSession().selectOne("checkId", id);
		return check;
	}

	@Override
	public int checkNickname(String nickname) {
		// TODO Auto-generated method stub
		int check=getSqlSession().selectOne("checkNickname", nickname);
		return check;
	}

	@Override
	public int memberInsert(MemberDTO mem) {
		// TODO Auto-generated method stub
		int insert=getSqlSession().insert("memberInsert", mem);
		return insert;
	}
	
	@Override
	public int loginInsert(MemberDTO mem) {
		// TODO Auto-generated method stub
		int insert=getSqlSession().insert("loginInsert", mem);
		return insert;
	}

	@Override
	public MemberDTO selectPG(String mem_id) {
		// TODO Auto-generated method stub
		return (MemberDTO)getSqlSession().selectOne("selectPG", mem_id);
	}
	
	@Override
	public int syncPG(MemberDTO mem) {
		// TODO Auto-generated method stub
		return getSqlSession().update("syncPG", mem);
	}

	@Override
	public MemberDTO getMember(String mem_id) {
		// TODO Auto-generated method stub
		return (MemberDTO)getSqlSession().selectOne("getMember", mem_id);
	}

	@Override
	public int memberUpdate(MemberDTO mem) {
		// TODO Auto-generated method stub
		return getSqlSession().update("memberUpdate", mem);
	}

	@Override
	public int loginUpdate(MemberDTO mem) {
		// TODO Auto-generated method stub
		return getSqlSession().update("loginUpdate", mem);
	}
	
	@Override
	public int beforeQuit(String id) {
		// TODO Auto-generated method stub
		getSqlSession().update("deleteH", id);
		getSqlSession().update("deleteHC", id);
		getSqlSession().update("deleteZ", id);
		getSqlSession().update("deleteZC", id);
		getSqlSession().update("deleteM", id);
		getSqlSession().update("deleteMC", id);
		getSqlSession().update("deleteG", id);
		getSqlSession().update("deleteGC", id);
		getSqlSession().update("deleteR", id);
		getSqlSession().update("deleteRC", id);
		getSqlSession().delete("deleteS", id);
		getSqlSession().delete("deleteStar", id);
		return 1;
	}
	
	@Override
	public int matchPw(String id, String passwd) {
		// TODO Auto-generated method stub
		int check=0;
		String dbpasswd=getSqlSession().selectOne("matchPw", id);
		if (dbpasswd.equals(passwd)) {
			check=1;
		}
		return check;
	}
	
	@Override
	public int quit(String id) {
		// TODO Auto-generated method stub
		return getSqlSession().delete("quit", id);
	}

}

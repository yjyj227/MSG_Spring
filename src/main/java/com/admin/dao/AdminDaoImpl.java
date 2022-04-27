package com.admin.dao;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Service;

import com.admin.domain.AdminDTO;
import com.member.domain.MemberDTO;

@Service("adminDaoImpl")
public class AdminDaoImpl extends SqlSessionDaoSupport implements AdminDao {

	@Override
	public boolean adminLoginCheck(AdminDTO admin) {
		// TODO Auto-generated method stub
		boolean check=getSqlSession().selectOne("adminLoginCheck", admin);
		return check;
	}

	@Override
	public String getAdminN(String admin_id) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne("getAdminN", admin_id);
	}

	@Override
	public Hashtable pageList(String pageNum, int count) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		Hashtable<String, Integer> pgList = new Hashtable<String, Integer>();

		int pageSize = 10; // numPerPage=>페이지당 보여주는 게시물 수(=레코드 수) default:10
		int blockSize = 10; // pagePerBlock=>블럭당 보여주는 페이지수 default:10

		// 게시판을 맨 처음 실행시키면 무조건 1페이지부터 출력->가장 최근의 글이 나오기 때문
		if (pageNum == null) {
			pageNum = "1"; // default(무조건 1페이지는 선택하지 않아도 보여줘야 하기 때문)
		}
		int currentPage = Integer.parseInt(pageNum); // "1"->1(=nowPage)(현재 페이지)
		// (1-1)*10+1=1, (2-1)*10+1=11, (3-1)*10+1=21
		int startRow = (currentPage - 1) * pageSize + 1; // 시작 레코드 번호
		int endRow = currentPage * pageSize; // 1*10=10, 2*10=20, 3*10=30
		int number = 0; // beginPerPage->페이지별로 시작하는 맨 처음에 나오는 게시물번호
		System.out.println("현재 레코드 수(count)=>" + count);
		number = count - (currentPage - 1) * pageSize;
		System.out.println(currentPage + "페이지별 number=>" + number);
		// 모델1의 list.jsp 페이징 처리 복사
		// 122/10=12.2+1.0=13.2=13
		int pageCount = count / pageSize + (count % pageSize == 0 ? 0 : 1);
		// 2. 시작페이지 @@@@중요@@@@
		int startPage = 0;
		if (currentPage % blockSize != 0) { // 페이지 수가 1~9, 11~19. 21~29 (10의 배수가 아닌 경우)
			startPage = currentPage / blockSize * blockSize + 1; // 경계선 때문에
		} else { // 10%10=0(10,20,30,40,,,)
					// ((10/10)-1)*10+1=1
			startPage = ((currentPage / blockSize) - 1) * blockSize + 1;
		}
		// 종료페이지
		int endPage = startPage + blockSize - 1; // 1+10-1=10, 2+10-1=11
		System.out.println("startPage=>" + startPage + ", endPage=>" + endPage);
		// 블럭별로 구분해서 링크 걸어서 출력(마지막 페이지 > 총 페이지 수 면 안 됨... 같아야 함)
		// 11>10=>endPage=10
		if (endPage > pageCount)
			endPage = pageCount; // 마지막 페이지=총 페이지 수 이도록 만들어주기
		// 페이징 처리에 대한 계산결과->Hashtable, HashMap=>ListAction에 전달->list.jsp
		pgList.put("pageSize", pageSize); // <->pgList(키명)("pageSize")
		pgList.put("blockSize", blockSize);
		pgList.put("currentPage", currentPage);
		pgList.put("startRow", startRow);
		pgList.put("endRow", endRow);
		pgList.put("count", count);
		pgList.put("number", number);
		pgList.put("startPage", startPage);
		pgList.put("endPage", endPage);
		pgList.put("pageCount", pageCount);

		return pgList;
	}

	@Override
	public int getMemberCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne("getMemberCount", map);
	}
	
	@Override
	public List<MemberDTO> getMembers(Map<String, Object> map) {
		// TODO Auto-generated method stub
		List<MemberDTO> list=getSqlSession().selectList("getMembers", map);
		return list;
	}
	
	@Override
	public MemberDTO getMember_admin(String mem_id) {
		// TODO Auto-generated method stub
		return (MemberDTO)getSqlSession().selectOne("getMember_admin", mem_id);
	}
	
	@Override
	public int memberModify(MemberDTO mem) {
		// TODO Auto-generated method stub
		return getSqlSession().update("memberModify", mem);
	}
	
	@Override
	public int loginModify(MemberDTO mem) {
		// TODO Auto-generated method stub
		return getSqlSession().update("loginModify", mem);
	}
	
	
	@Override
	public int syncPG_admin(MemberDTO mem) {
		// TODO Auto-generated method stub
		return getSqlSession().update("syncPG_admin", mem);
	}
	
	
	
	@Override
	public int beforeQuit_admin(String mem_id) {
		// TODO Auto-generated method stub
		getSqlSession().update("deleteH_admin", mem_id);
		getSqlSession().update("deleteHC_admin", mem_id);
		getSqlSession().update("deleteZ_admin", mem_id);
		getSqlSession().update("deleteZC_admin", mem_id);
		getSqlSession().update("deleteM_admin", mem_id);
		getSqlSession().update("deleteMC_admin", mem_id);
		getSqlSession().update("deleteG_admin", mem_id);
		getSqlSession().update("deleteGC_admin", mem_id);
		getSqlSession().update("deleteR_admin", mem_id);
		getSqlSession().update("deleteRC_admin", mem_id);
		getSqlSession().delete("deleteS_admin", mem_id);
		getSqlSession().delete("deleteStar_admin", mem_id);
		return 1;
	}
	
	@Override
	public int matchPw_admin(String id, String passwd) {
		// TODO Auto-generated method stub
		int check=0;
		String dbpasswd=getSqlSession().selectOne("matchPw_admin", id);
		if (dbpasswd.equals(passwd)) {
			check=1;
		}
		return check;
	}
	
	@Override
	public int memberDelete(String mem_id) {
		// TODO Auto-generated method stub
		return getSqlSession().delete("memberDelete", mem_id);
	}

}

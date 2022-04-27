package com.board.ghibli.dao;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Service;

import com.board.ghibli.domain.GBoardDTO;

@Service("gBoardDaoImpl")
public class GBoardDaoImpl extends SqlSessionDaoSupport implements GBoardDao {

	@Override
	public List<GBoardDTO> list(Map<String, Object> map) {
		// TODO Auto-generated method stub
		List<GBoardDTO> list=getSqlSession().selectList("selectList_G", map);
		System.out.println("list() 테스트 중");
		return list;
	}

	@Override
	public List<GBoardDTO> list_gref(Map<String, Object> map) {
		// TODO Auto-generated method stub
		List<GBoardDTO> list=getSqlSession().selectList("selectListGref_G", map);
		System.out.println("list() h_ref 호출됨");
		return list;
	}

	@Override
	public List<GBoardDTO> list_hot(Map<String, Object> map) {
		// TODO Auto-generated method stub
		List<GBoardDTO> list=getSqlSession().selectList("selectListHot_G", map);
		System.out.println("list() hot 호출됨");
		return list;
	}

	@Override
	public int getRowCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne("selectCount_G", map);
	}

	@Override
	public int getRowCount_gref(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne("selectCountGref_G", map);
	}

	@Override
	public int getRowCount_hot(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne("selectCountHot_G", map);
	}

	@Override
	public Hashtable pageList(String pageNum, int count) {
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
	public int getNewNum() {
		// TODO Auto-generated method stub
		int newnum=0;
		if ((Integer)getSqlSession().selectOne("getNewNum_G") != null) {
			newnum=(Integer)getSqlSession().selectOne("getNewNum_G");
		}
		System.out.println("getNewNum()의 newnum=>"+newnum);
		return newnum;
	}

	@Override
	public int insert(GBoardDTO board) {
		// TODO Auto-generated method stub
		return getSqlSession().insert("insertBoard_G", board);
	}

	@Override
	public int pointupA(String mem_id) {
		// TODO Auto-generated method stub
		return getSqlSession().update("pointupA_G", mem_id);
	}

	@Override
	public GBoardDTO getBoard(Integer num) {
		// TODO Auto-generated method stub
		return (GBoardDTO)getSqlSession().selectOne("selectBoard_G", num);
	}

	@Override
	public void updateHit(Integer num) {
		// TODO Auto-generated method stub
		getSqlSession().update("updateHit_G", num);
	}

	@Override
	public void update(GBoardDTO board) {
		// TODO Auto-generated method stub
		getSqlSession().update("updateBoard_G", board);
	}

	@Override
	public int delete(Integer num) {
		// TODO Auto-generated method stub
		return getSqlSession().delete("deleteBoard_G", num);
	}

	@Override
	public int pointdownA(String mem_id) {
		// TODO Auto-generated method stub
		return getSqlSession().update("pointdownA_G", mem_id);
	}

	@Override
	public int deleteComment(Integer num) {
		// TODO Auto-generated method stub
		return getSqlSession().delete("deleteBoardComment_G", num);
	}

	@Override
	public int deleteScrap(GBoardDTO board) {
		// TODO Auto-generated method stub
		return getSqlSession().delete("deleteBoardScrap_G", board);
	}

	@Override
	public int getPoint(String mem_id) {
		// TODO Auto-generated method stub
		return (Integer)getSqlSession().selectOne("getPoint_G", mem_id);
	}

	@Override
	public int getGrade(String mem_id) {
		// TODO Auto-generated method stub
		return (Integer)getSqlSession().selectOne("getGrade_G", mem_id);
	}

	@Override
	public int changeGrade1(String mem_id) {
		// TODO Auto-generated method stub
		return getSqlSession().update("changeGrade1_G", mem_id);
	}

	@Override
	public int changeGrade2(String mem_id) {
		// TODO Auto-generated method stub
		return getSqlSession().update("changeGrade2_G", mem_id);
	}

	@Override
	public int changeGrade3(String mem_id) {
		// TODO Auto-generated method stub
		return getSqlSession().update("changeGrade3_G", mem_id);
	}

	@Override
	public int changeGrade4(String mem_id) {
		// TODO Auto-generated method stub
		return getSqlSession().update("changeGrade4_G", mem_id);
	}

	@Override
	public int changeGrade5(String mem_id) {
		// TODO Auto-generated method stub
		return getSqlSession().update("changeGrade5_G", mem_id);
	}

}

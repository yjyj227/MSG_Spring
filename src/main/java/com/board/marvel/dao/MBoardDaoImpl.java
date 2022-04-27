package com.board.marvel.dao;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Service;

import com.board.marvel.domain.MBoardDTO;

@Service("mBoardDaoImpl") // =>컨트롤러와 DB(DAO)사이의 중간역할을 하는 클래스에게 부여(빈등록)
public class MBoardDaoImpl extends SqlSessionDaoSupport implements MBoardDao {

	// 댓글 수 담기
	public List<MBoardDTO> getCommentCountForList(Map<String, Object> map) {
		System.out.println("getCommentCountForList() 호출됨");
		List<MBoardDTO> list=getSqlSession().selectList("selectCommentCount_M", map);
		System.out.println("댓글수담기...list=>"+list);
		return list;
	};
	 

	// article~
	/*
	 * public MBoardCommand makeArticleFromResult() {
	 * 
	 * };
	 */

	// 1. 글목록보기
	// public List<BoardCommand> list(Map<String, Object> map);
	public List<MBoardDTO> list(Map<String, Object> map) {
		List<MBoardDTO> list=getSqlSession().selectList("selectList_M", map);
		return list;
	};

	public List<MBoardDTO> list_mref(Map<String, Object> map) {
		List<MBoardDTO> list=getSqlSession().selectList("selectListMref_M", map);
		return list;
	};

	public List<MBoardDTO> list_hot(Map<String, Object> map) {
		List<MBoardDTO> list=getSqlSession().selectList("selectListHot_M", map);
		return list;
	};

	// 2. 총 레코드 수(검색어 맞는 레코드수까지 포함)
	// public int getRowCount(Map<String, Object> map);
	public int getRowCount(Map<String, Object> map) {
		return getSqlSession().selectOne("selectCount_M", map);
	};
	@Override
	public int getRowCount_mref(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne("selectCountMref_M", map);
	}
	@Override
	public int getRowCount_hot(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne("selectCountHot_M", map);
	}
	
	//페이징 처리
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

	// 3. 최댓값 번호 구하기
	// public int getNewSeq();
	public int getNewNum() {
		int newnum=0;
		if ((Integer)getSqlSession().selectOne("getNewNum_M") != null) {
			newnum=(Integer)getSqlSession().selectOne("getNewNum_M");
		}
		System.out.println("getNewNum()의 newnum=>"+newnum);
		return newnum;
	};

	// 4-1. 자료실의 글쓰기
	// public void insert(BoardCommand board);
	public int insert(MBoardDTO board) {
		return getSqlSession().insert("insertBoard_M", board);
		/*
			int insert=getSqlSession().insert("insertBoard", board);
			int pointup;
			if (insert > 0) {
			pointup=getSqlSession().update("pointupA", board.getMem_id());
			}
			return pointup;
			
		 */
	};
	// 4-2. 글쓰기 성공시 포인트 적립
	@Override
	public int pointupA(String mem_id) {
		// TODO Auto-generated method stub
		return getSqlSession().update("pointupA_M", mem_id);
	}

	// 5. 글상세보기
	// public BoardCommand selectBoard(Integer seq);
	public MBoardDTO getBoard(Integer num) {
		return (MBoardDTO)getSqlSession().selectOne("selectBoard_M", num);
	};

	// 6. 게시물번호에 해당하는 조회수 증가
	// public void updateHit(Integer seq);
	public void updateHit(Integer num) {
		getSqlSession().update("updateHit_M", num);
	};

	// 7. 글수정하기
	// public void update(BoardCommand board);
	public void update(MBoardDTO board) {
		getSqlSession().update("updateBoard_M", board);
	};

	// 8. 글삭제하기
	// public void delete(Integer seq);
	public int delete(Integer num) {
		return getSqlSession().delete("deleteBoard_M", num);
	};
	// 8-2. 글 삭제시 포인트 회수
	@Override
	public int pointdownA(String mem_id) {
		// TODO Auto-generated method stub
		return getSqlSession().update("pointdownA_M", mem_id);
	}
	
	// 8-3. 글삭제 성공시 해당 글의 댓글 삭제
	@Override
	public int deleteComment(Integer num) {
		// TODO Auto-generated method stub
		return getSqlSession().delete("deleteBoardComment_M", num);
	}
	
	// 8-4. 글삭제 성공시 스크랩 삭제
	@Override
	public int deleteScrap(MBoardDTO board) {
		// TODO Auto-generated method stub
		return getSqlSession().delete("deleteBoardScrap_M", board);
	}
	
	
	//포인트 구하기
	@Override
	public int getPoint(String mem_id) {
		// TODO Auto-generated method stub
		return (Integer)getSqlSession().selectOne("getPoint_M", mem_id);
	};
	//등급 구하기
	@Override
	public int getGrade(String mem_id) {
		// TODO Auto-generated method stub
		return (Integer)getSqlSession().selectOne("getGrade_M", mem_id);
	}
	
	
	//등급 변동
	@Override
	public int changeGrade1(String mem_id) {
		// TODO Auto-generated method stub
		return getSqlSession().update("changeGrade1_M", mem_id);
	}
	@Override
	public int changeGrade2(String mem_id) {
		// TODO Auto-generated method stub
		return getSqlSession().update("changeGrade2_M", mem_id);
	}
	@Override
	public int changeGrade3(String mem_id) {
		// TODO Auto-generated method stub
		return getSqlSession().update("changeGrade3_M", mem_id);
	}
	@Override
	public int changeGrade4(String mem_id) {
		// TODO Auto-generated method stub
		return getSqlSession().update("changeGrade4_M", mem_id);
	}
	@Override
	public int changeGrade5(String mem_id) {
		// TODO Auto-generated method stub
		return getSqlSession().update("changeGrade5_M", mem_id);
	}

}

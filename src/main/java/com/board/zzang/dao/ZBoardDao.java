package com.board.zzang.dao;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import com.board.zzang.domain.ZBoardDTO;

public interface ZBoardDao {

	// 댓글 수 담기
	public List<ZBoardDTO> getCommentCountForList(Map<String, Object> map);

	//article~
	//public ZBoardCommand makeArticleFromResult();
		
	// 1. 글목록보기
	//public List<BoardCommand> list(Map<String, Object> map);
	public List<ZBoardDTO> list(Map<String, Object> map);
	public List<ZBoardDTO> list_zref(Map<String, Object> map);
	public List<ZBoardDTO> list_hot(Map<String, Object> map);

	// 2. 총 레코드 수(검색어 맞는 레코드수까지 포함)
	//public int getRowCount(Map<String, Object> map);
	public int getRowCount(Map<String, Object> map);
	public int getRowCount_zref(Map<String, Object> map);
	public int getRowCount_hot(Map<String, Object> map);
	
	//페이징처리
	public Hashtable pageList(String pageNum, int count);

	// 3. 최댓값 번호 구하기
	//public int getNewSeq();
	public int getNewNum();

	// 4-1. 자료실의 글쓰기
	//public void insert(BoardCommand board);
	public int insert(ZBoardDTO board);
	// 4-2. 글쓰기 성공시 포인트 적립
	public int pointupA(String mem_id);
	
	
	
	
	// 5. 글상세보기
	//public BoardCommand selectBoard(Integer seq);
	public ZBoardDTO getBoard(Integer num);

	// 6. 게시물번호에 해당하는 조회수 증가
	//public void updateHit(Integer seq);
	public void updateHit(Integer num);

	// 7. 글수정하기
	//public void update(BoardCommand board);
	public void update(ZBoardDTO board);

	// 8-1. 글삭제하기
	//public void delete(Integer seq);
	public int delete(Integer num);
	// 8-2. 글삭제 성공시 포인트 회수
	public int pointdownA(String mem_id);
	// 8-3. 글삭제 성공시 해당 글의 댓글 삭제
	public int deleteComment(Integer num);
	// 8-4. 글삭제 성공시 스크랩 삭제
	public int deleteScrap(ZBoardDTO board);
	
	//포인트 구하기
	public int getPoint(String mem_id);
	//등급 구하기
	public int getGrade(String mem_id);
	
	//등급 변동
	public int changeGrade1(String mem_id);
	public int changeGrade2(String mem_id);
	public int changeGrade3(String mem_id);
	public int changeGrade4(String mem_id);
	public int changeGrade5(String mem_id);
}

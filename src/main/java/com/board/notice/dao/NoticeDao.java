package com.board.notice.dao;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import com.board.notice.domain.NoticeDTO;

public interface NoticeDao {

	// 1. 글목록보기
		//public List<BoardCommand> list(Map<String, Object> map);
		public List<NoticeDTO> list(Map<String, Object> map);

		// 2. 총 레코드 수(검색어 맞는 레코드수까지 포함)
		//public int getRowCount(Map<String, Object> map);
		public int getRowCount(Map<String, Object> map);
		
		//페이징처리
		public Hashtable pageList(String pageNum, int count);

		// 3. 최댓값 번호 구하기
		//public int getNewSeq();
		public int getNewNum();

		// 4-1. 자료실의 글쓰기
		//public void insert(BoardCommand board);
		public int insert(NoticeDTO board);
		
		
		
		
		// 5. 글상세보기
		//public BoardCommand selectBoard(Integer seq);
		public NoticeDTO getBoard(Integer num);

		// 6. 게시물번호에 해당하는 조회수 증가
		//public void updateHit(Integer seq);
		public void updateHit(Integer num);

		// 7. 글수정하기
		//public void update(BoardCommand board);
		public void update(NoticeDTO board);

		// 8-1. 글삭제하기
		//public void delete(Integer seq);
		public int delete(Integer num);
		
}

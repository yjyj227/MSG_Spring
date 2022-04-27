package com.board.dao;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import com.board.domain.BoardDTO;
import com.board.harry.domain.HBoardDTO;

public interface BoardDao {

	//makeHArticleFromResult()
	
	//내가 쓴 글 모아보기
	//레코드 수
	public int getMyArticleCount(Map<String, Object> map, String genre);
	
	//글 목록 조회
	public List getMyArticles(Map<String, Object> map, String genre);
	
	//이니셜 만들기
	//public String makeInit(int s_category)
	public String makeInit(Integer s_category);
		
	//url만들기
	//public String makeScrapUrl(int s_category, int s_number)
	public String makeScrapUrl(String init, Integer s_number);
	
	
	//스크랩하기
	//public void scrap(BoardDTO article)
	public int getNewScrapNum();
	public int scrap(BoardDTO com);
	public int scrapUp(BoardDTO com);
	
	//스크랩 레코드 수
	//public int getScrapArticleCount(String mem_id)
	public int getScrapArticleCount(String mem_id);
	
	//스크랩 목록 조회
	//public List<BoardDTO> getScrapArticles(String mem_id, int start, int end)
	public List<BoardDTO> getScrapArticles(Map<String, Object> map);
	
	//페이징처리
	public Hashtable pageList(String pageNum, int count);
	
	//스크랩 삭제
	//public int deleteScrapArticle(int scrap_number, int s_category, int s_number)
	public int deleteScrapArticle(Integer scrap_number);
	public int scrapDown(BoardDTO com);
	
	
	
	//별점
	//makeRateArticleFromResult()
	//
	
	
	//별점 평균내기
	//public double avgStar(int v_movie)
	public double avgStar(Integer v_movie);
	
	//좋아요 총합
	//public int countLikes(int v_movie)
	public int countLikes(Integer v_movie);
	
	//내 별점 표시
	//public BoardDTO getMyRate(String mem_id, int v_movie)
	public BoardDTO getMyRate(Map<String, Object> map);
	
	//별점, 좋아요 입력
	//public void rate(BoardDTO article)
	//public void rate(BoardDTO com);
	public int findRate(Map<String, Object> map);
	public int updateRate(BoardDTO board);
	public int getMaxNum();
	public int insertRate(BoardDTO board);
	
	
	
	
	
	
	
	
	
	
	
}

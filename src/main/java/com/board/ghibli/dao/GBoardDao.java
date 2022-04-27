package com.board.ghibli.dao;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import com.board.ghibli.domain.GBoardDTO;

public interface GBoardDao {

	public List<GBoardDTO> list(Map<String, Object> map);
	public List<GBoardDTO> list_gref(Map<String, Object> map);
	public List<GBoardDTO> list_hot(Map<String, Object> map);
	
	public int getRowCount(Map<String, Object> map);
	public int getRowCount_gref(Map<String, Object> map);
	public int getRowCount_hot(Map<String, Object> map);
	
	public Hashtable pageList(String pageNum, int count);
	
	public int getNewNum();
	
	public int insert(GBoardDTO board);
	public int pointupA(String mem_id);
	
	public GBoardDTO getBoard(Integer num);
	public void updateHit(Integer num);
	
	public void update(GBoardDTO board);
	
	public int delete(Integer num);
	public int pointdownA(String mem_id);
	public int deleteComment(Integer num);
	public int deleteScrap(GBoardDTO board);
	
	public int getPoint(String mem_id);
	public int getGrade(String mem_id);
	
	public int changeGrade1(String mem_id);
	public int changeGrade2(String mem_id);
	public int changeGrade3(String mem_id);
	public int changeGrade4(String mem_id);
	public int changeGrade5(String mem_id);
	
}

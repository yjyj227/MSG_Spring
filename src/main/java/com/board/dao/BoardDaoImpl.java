package com.board.dao;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Service;

import com.board.domain.BoardDTO;
import com.board.harry.domain.HBoardDTO;

@Service("boardDaoImpl")
public class BoardDaoImpl extends SqlSessionDaoSupport implements BoardDao {

	@Override
	public int getMyArticleCount(Map<String, Object> map, String genre) {
		int count=0;
		if (genre!=null) {
			if (genre.equals("h")) {
				count=getSqlSession().selectOne("getMyArticleCountH", map);
			}else if (genre.equals("z")) {
				count=getSqlSession().selectOne("getMyArticleCountZ", map);
			}else if (genre.equals("m")) {
				count=getSqlSession().selectOne("getMyArticleCountM", map);
			}else if (genre.equals("g")) {
				count=getSqlSession().selectOne("getMyArticleCountG", map);
			}else if (genre.equals("r")) {
				count=getSqlSession().selectOne("getMyArticleCountR", map);
			}
		}else {
			count=0;
		}
		return count;
	}
	
	@Override
	public List getMyArticles(Map<String, Object> map, String genre) {
		List list=null;
		if (genre!=null) {
			if (genre.equals("h")) {
				list=getSqlSession().selectList("getMyArticlesH", map);
			}else if (genre.equals("z")) {
				list=getSqlSession().selectList("getMyArticlesZ", map);
			}else if (genre.equals("m")) {
				list=getSqlSession().selectList("getMyArticlesM", map);
			}else if (genre.equals("g")) {
				list=getSqlSession().selectList("getMyArticlesG", map);
			}else if (genre.equals("r")) {
				list=getSqlSession().selectList("getMyArticlesR", map);
			}
		}else {
			System.out.println("x");
			list=getSqlSession().selectList("getMyArticles", map);
		}
		return list;
	}
	
	
	
	@Override
	public String makeInit(Integer s_category) {
		String init="";
		
		if (s_category>=10 && s_category<20) {
			init="h";
		}else if (s_category>=20 && s_category<30) {
			init="z";
		}else if (s_category>=30 && s_category<40) {
			init="m";
		}else if (s_category>=40 && s_category<50) {
			init="g";
		}else if (s_category>=50 && s_category<60) {
			init="r";
		}
		
		return init;
	}
	
	@Override
	public String makeScrapUrl(String init, Integer s_number) {
		String s_url=init.toUpperCase()+"_Content.do?"+init+"_number="+s_number;
		return s_url;
	}

	

	@Override
	public int getNewScrapNum() {
		int newnum=(Integer)getSqlSession().selectOne("getNewScrapNum");
		return newnum;
	}

	@Override
	public int scrap(BoardDTO com) {
		return getSqlSession().insert("scrap", com);
	}

	@Override
	public int scrapUp(BoardDTO com) {
		return getSqlSession().update("scrapUp", com);
	}

	@Override
	public int getScrapArticleCount(String mem_id) {
		return getSqlSession().selectOne("getScrapArticleCount", mem_id);
	}

	@Override
	public List<BoardDTO> getScrapArticles(Map<String, Object> map) {
		List<BoardDTO> list=getSqlSession().selectList("getScrapArticles", map);
		return list;
	}

	@Override
	public Hashtable pageList(String pageNum, int count) {
		Hashtable<String, Integer> pgList = new Hashtable<String, Integer>();

		int pageSize = 10;
		int blockSize = 10;

		if (pageNum == null) {
			pageNum = "1";
		}
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = currentPage * pageSize;
		int number = 0;
		System.out.println("현재 레코드 수(count)=>" + count);
		number = count - (currentPage - 1) * pageSize;
		System.out.println(currentPage + "페이지별 number=>" + number);
		int pageCount = count / pageSize + (count % pageSize == 0 ? 0 : 1);
		int startPage = 0;
		if (currentPage % blockSize != 0) {
			startPage = currentPage / blockSize * blockSize + 1;
		} else {
			startPage = ((currentPage / blockSize) - 1) * blockSize + 1;
		}
		int endPage = startPage + blockSize - 1;
		System.out.println("startPage=>" + startPage + ", endPage=>" + endPage);
		if (endPage > pageCount)
			endPage = pageCount;
		pgList.put("pageSize", pageSize);
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
	public int deleteScrapArticle(Integer scrap_number) {
		return getSqlSession().delete("deleteScrapArticle", scrap_number);
	}

	@Override
	public int scrapDown(BoardDTO com) {
		return getSqlSession().update("scrapDown", com);
	}

	@Override
	public double avgStar(Integer v_movie) {
		double avg=0.0;
		if (getSqlSession().selectOne("avgStar", v_movie) != null) {
			avg=getSqlSession().selectOne("avgStar", v_movie);
		}
		return avg;
	}

	@Override
	public int countLikes(Integer v_movie) {
		int countLike=0;
		if (getSqlSession().selectOne("countLikes", v_movie) != null) {
			countLike=getSqlSession().selectOne("countLikes", v_movie);
		}
		return countLike;
	}

	@Override
	public BoardDTO getMyRate(Map<String, Object> map) {
		return (BoardDTO)getSqlSession().selectOne("getMyRate", map);
	}

	@Override
	public int findRate(Map<String, Object> map) {
		return getSqlSession().selectOne("findRate", map);
	}
	
	@Override
	public int updateRate(BoardDTO board) {
		return getSqlSession().update("updateRate", board);
	}
	
	@Override
	public int getMaxNum() {
		return getSqlSession().selectOne("getMaxNum");
	}
	
	@Override
	public int insertRate(BoardDTO board) {
		return getSqlSession().insert("insertRate", board);
	}

}

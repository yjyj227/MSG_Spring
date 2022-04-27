package com.member.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.board.dao.BoardDao;
import com.board.domain.BoardDTO;
import com.board.harry.domain.HBoardDTO;

@Component
@Controller
public class MyArticlesController {

	private Logger log=Logger.getLogger(this.getClass());
	
	@Autowired
	private BoardDao boardDao;
	
	@RequestMapping("/MyArticles.do")
	public ModelAndView process(
			@RequestParam(value="pageNum", defaultValue="1") String currentPage,
			HttpServletRequest request) {
		
		if (log.isDebugEnabled()) {
			System.out.println("/MyArticles.do 요청중");
			log.debug("currentPage : "+currentPage);
		}
		
		Map<String, Object> map=new HashMap<String, Object>();
		
		String genre=request.getParameter("genre");
		HttpSession session=request.getSession();
		String mem_id=(String)session.getAttribute("idKey");
		
		map.put("genre", genre);
		map.put("mem_id", mem_id);
		
		int count=0;
		if (genre==null) {
			count=0;
		}else {
			count=boardDao.getMyArticleCount(map, genre);
		}
		
		Hashtable<String, Integer> pgList=boardDao.pageList(currentPage, count);
		
		map.put("start", pgList.get("startRow"));
		map.put("end", pgList.get("endRow"));
		
		List articleList=null;
		if (count > 0) {
			articleList=boardDao.getMyArticles(map, genre);
		}else {
			articleList=Collections.EMPTY_LIST;
		}
		
		
		ModelAndView mav=new ModelAndView("/member/MyArticles");
		mav.addObject("count", count);
		mav.addObject("articleList", articleList);
		mav.addObject("pgList", pgList);
		mav.addObject("genre", genre);
		mav.addObject("mem_id", mem_id);
		
		return mav;
	}
}

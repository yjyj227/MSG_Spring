package com.board.notice.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.board.harry.domain.HBoardDTO;
import com.board.notice.dao.NoticeDao;
import com.board.notice.domain.NoticeDTO;

@Component
@Controller
public class N_ListController {
	
	private Logger log=Logger.getLogger(this.getClass());
	
	@Autowired
	private NoticeDao noticeDao;
	
	@RequestMapping("/N_List.do")
	public ModelAndView process(
			@RequestParam(value="pageNum", defaultValue="1") String currentPage,
			@RequestParam(value="search", defaultValue="") String search,
			@RequestParam(value="searchtext", defaultValue="") String searchtext,
			HttpServletRequest request
			) {
		
		if (log.isDebugEnabled()) { //로그객체가 디버깅모드 상태인지 아닌지를 체크
			System.out.println("/N_List.do 요청중"); //?을 출력X
			log.debug("currentPage:"+currentPage); //?을 출력 가능(select ~ where num=? 등의 ?)
			log.debug("search=>"+search);
			log.debug("searchtext=>"+searchtext);
		}
		Map<String, Object> map=new HashMap<String, Object>();
		
		map.put("search", search);
		map.put("searchtext", searchtext);
		
		int count=noticeDao.getRowCount(map);
		
		System.out.println("N_ListController클래스의 count=>"+count);
		
		//PagingUtil page=new PagingUtil(currentPage, count, 10, 10, "H_List.do");
		Hashtable<String, Integer> pgList=noticeDao.pageList(currentPage, count);
		
		map.put("start", pgList.get("startRow"));
		map.put("end", pgList.get("endRow"));
		
		List<NoticeDTO> articleList=null;
		
		if (count > 0) {
			articleList=noticeDao.list(map);
		}else {
			articleList=Collections.EMPTY_LIST;
		}
		
		ModelAndView mav=new ModelAndView("/board/notice/N_List");
		mav.addObject("count", count);
		mav.addObject("articleList", articleList);
		mav.addObject("pgList", pgList);
		
		return mav;
	}
	
}

package com.board.harry.controller;

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

import com.board.harry.dao.HBoardDao;
import com.board.harry.domain.HBoardDTO;
import com.board.notice.dao.NoticeDao;
import com.board.notice.domain.NoticeDTO;

@Component
@Controller
public class H_ListController {
	
	private Logger log=Logger.getLogger(this.getClass());
	
	@Autowired
	private HBoardDao hBoardDao;
	@Autowired
	private NoticeDao noticeDao;
	
	@RequestMapping("/H_List.do")
	public ModelAndView process(
			@RequestParam(value="pageNum", defaultValue="1") String currentPage,
			@RequestParam(value="search", defaultValue="") String search,
			@RequestParam(value="searchtext", defaultValue="") String searchtext,
			HttpServletRequest request
			) {
		
		if (log.isDebugEnabled()) { //로그객체가 디버깅모드 상태인지 아닌지를 체크
			System.out.println("/H_List.do 요청중"); //?을 출력X
			log.debug("currentPage:"+currentPage); //?을 출력 가능(select ~ where num=? 등의 ?)
			log.debug("search=>"+search);
			log.debug("searchtext=>"+searchtext);
		}
		
		Map<String, Object> map=new HashMap<String, Object>();
		
		String h_ref=request.getParameter("h_ref");
		map.put("h_ref", h_ref);
		map.put("search", search);
		map.put("searchtext", searchtext);
		
		int count=0;
		if (h_ref==null) {
			count=hBoardDao.getRowCount(map);
		}else if (Integer.parseInt(h_ref)==1 || Integer.parseInt(h_ref)==2) {
			count=hBoardDao.getRowCount_href(map);
		}else if (Integer.parseInt(h_ref)==4) {
			count=hBoardDao.getRowCount_hot(map);
		}
		
		Hashtable<String, Integer> pgList=hBoardDao.pageList(currentPage, count);
		
		map.put("start", pgList.get("startRow"));
		map.put("end", pgList.get("endRow"));
		
		List<HBoardDTO> articleList=null;
		if (count > 0) {
			if (h_ref==null) {
				articleList=hBoardDao.list(map);
			}else if (Integer.parseInt(h_ref)==1 || Integer.parseInt(h_ref)==2) {
				articleList=hBoardDao.list_href(map);
			}else if (Integer.parseInt(h_ref)==4) {
				articleList=hBoardDao.list_hot(map);
			}
		}else {
			articleList=Collections.EMPTY_LIST;
		}
		
		//----------공지----------
		
		Map<String, Object> map2=new HashMap<String, Object>();
		
		map2.put("search", search);
		map2.put("searchtext", searchtext);
		
		int count2=noticeDao.getRowCount(map2);
		
		System.out.println("N_ListController클래스의 count2=>"+count2);
		
		Hashtable<String, Integer> pgList2=noticeDao.pageList(currentPage, count2);
		
		map2.put("start", pgList.get("startRow"));
		map2.put("end", pgList.get("endRow"));
		
		List<NoticeDTO> noticeList=null;
		
		if (count2 > 0) {
			noticeList=noticeDao.list(map);
		}else {
			noticeList=Collections.EMPTY_LIST;
		}
		
		ModelAndView mav=new ModelAndView("/board/harry/H_List");
		mav.addObject("count", count);
		mav.addObject("articleList", articleList);
		mav.addObject("pgList", pgList);
		
		mav.addObject("count2", count2);
		mav.addObject("noticeList", noticeList);
		mav.addObject("pgList2", pgList2);
		
		return mav;
	}
	
}

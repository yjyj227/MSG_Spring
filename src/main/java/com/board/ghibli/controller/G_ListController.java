package com.board.ghibli.controller;

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

import com.board.ghibli.dao.GBoardDao;
import com.board.ghibli.domain.GBoardDTO;
import com.board.notice.dao.NoticeDao;
import com.board.notice.domain.NoticeDTO;

@Component
@Controller
public class G_ListController {
	
	private Logger log=Logger.getLogger(this.getClass());
	
	@Autowired
	private GBoardDao gBoardDao;
	@Autowired
	private NoticeDao noticeDao;
	
	@RequestMapping("/G_List.do")
	public ModelAndView process(
			@RequestParam(value="pageNum", defaultValue="1") String currentPage,
			@RequestParam(value="search", defaultValue="") String search,
			@RequestParam(value="searchtext", defaultValue="") String searchtext,
			HttpServletRequest request
			) {
		
		if (log.isDebugEnabled()) { //로그객체가 디버깅모드 상태인지 아닌지를 체크
			System.out.println("/G_List.do 요청중"); //?을 출력X
			log.debug("currentPage:"+currentPage); //?을 출력 가능(select ~ where num=? 등의 ?)
			log.debug("search=>"+search);
			log.debug("searchtext=>"+searchtext);
		}
		Map<String, Object> map=new HashMap<String, Object>();
		
		String g_ref=request.getParameter("g_ref");
		System.out.println("g_ref=>"+g_ref);
		
		map.put("g_ref", g_ref);
		map.put("search", search);
		map.put("searchtext", searchtext);
		
		int count=0;
		
		if (g_ref==null) {
			count=gBoardDao.getRowCount(map);
		}else if (Integer.parseInt(g_ref)==1 || Integer.parseInt(g_ref)==2) {
			count=gBoardDao.getRowCount_gref(map);
		}else if (Integer.parseInt(g_ref)==4) {
			count=gBoardDao.getRowCount_hot(map);
		}
		
		System.out.println("G_ListController클래스의 count=>"+count);
		
		//PagingUtil page=new PagingUtil(currentPage, count, 10, 10, "G_List.do");
		Hashtable<String, Integer> pgList=gBoardDao.pageList(currentPage, count);
		
		map.put("start", pgList.get("startRow"));
		map.put("end", pgList.get("endRow"));
		
		List<GBoardDTO> articleList=null;
		
		List<GBoardDTO> commentList=new ArrayList(pgList.get("endRow"));
		//commentList=;
		GBoardDTO g_numbers=new GBoardDTO();
		int g_number=0;
		int g_comments=0;
		if (count > 0) {
			if (g_ref==null) {
				articleList=gBoardDao.list(map); //애초에 이 단계에서 g_comments도 만들어져야 함...
				//map2.put("g_number", articleList.get(0).getG_number());
				//System.out.println("articleList.get(0).getG_number()=>"+articleList.get(0).getG_number());
				//commentList=gBoardDao.getCommentCountForList(map2);
				//System.out.println("tq=>"+commentList);
				//g_numbers=articleList.get(0);
				
				//g_number=g_numbers.getG_number();
				//g_comments=gBoardDao.getCommentCountForList(g_number);
				//g_numbers.setG_comments(g_comments);
				//commentList.add(g_numbers);
				
				
				
				
			}else if (Integer.parseInt(g_ref)==1 || Integer.parseInt(g_ref)==2) {
				articleList=gBoardDao.list_gref(map);
			}else if (Integer.parseInt(g_ref)==4) {
				articleList=gBoardDao.list_hot(map);
			}
			System.out.println("g_numbers=>"+g_numbers);
			System.out.println("commentList=>"+commentList);
			System.out.println("g_number=>"+g_number);
			System.out.println("g_comments=>"+g_comments);
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
		
		
		
		ModelAndView mav=new ModelAndView("/board/ghibli/G_List");
		mav.addObject("count", count);
		mav.addObject("articleList", articleList);
		mav.addObject("pgList", pgList);
		
		mav.addObject("count2", count2);
		mav.addObject("noticeList", noticeList);
		mav.addObject("pgList2", pgList2);
		
		mav.addObject("g_comments", g_comments);
		mav.addObject("commentList", commentList);
		
		return mav;
	}
	
}

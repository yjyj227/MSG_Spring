package com.board.marvel.controller;

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

import com.board.marvel.dao.MBoardDao;
import com.board.marvel.domain.MBoardDTO;
import com.board.notice.dao.NoticeDao;
import com.board.notice.domain.NoticeDTO;

@Component
@Controller
public class M_ListController {
	
	private Logger log=Logger.getLogger(this.getClass());
	
	@Autowired
	private MBoardDao mBoardDao;
	@Autowired
	private NoticeDao noticeDao;
	
	@RequestMapping("/M_List.do")
	public ModelAndView process(
			@RequestParam(value="pageNum", defaultValue="1") String currentPage,
			@RequestParam(value="search", defaultValue="") String search,
			@RequestParam(value="searchtext", defaultValue="") String searchtext,
			HttpServletRequest request
			) {
		
		if (log.isDebugEnabled()) { //로그객체가 디버깅모드 상태인지 아닌지를 체크
			System.out.println("/M_List.do 요청중"); //?을 출력X
			log.debug("currentPage:"+currentPage); //?을 출력 가능(select ~ where num=? 등의 ?)
			log.debug("search=>"+search);
			log.debug("searchtext=>"+searchtext);
		}
		Map<String, Object> map=new HashMap<String, Object>();
		
		String m_ref=request.getParameter("m_ref");
		System.out.println("m_ref=>"+m_ref);
		
		map.put("m_ref", m_ref);
		map.put("search", search);
		map.put("searchtext", searchtext);
		
		int count=0;
		
		if (m_ref==null) {
			count=mBoardDao.getRowCount(map);
		}else if (Integer.parseInt(m_ref)==1 || Integer.parseInt(m_ref)==2) {
			count=mBoardDao.getRowCount_mref(map);
		}else if (Integer.parseInt(m_ref)==4) {
			count=mBoardDao.getRowCount_hot(map);
		}
		
		System.out.println("M_ListController클래스의 count=>"+count);
		
		//PagingUtil page=new PagingUtil(currentPage, count, 10, 10, "M_List.do");
		Hashtable<String, Integer> pgList=mBoardDao.pageList(currentPage, count);
		
		map.put("start", pgList.get("startRow"));
		map.put("end", pgList.get("endRow"));
		
		List<MBoardDTO> articleList=null;
		
		List<MBoardDTO> commentList=new ArrayList(pgList.get("endRow"));
		//commentList=;
		MBoardDTO m_numbers=new MBoardDTO();
		int m_number=0;
		int m_comments=0;
		if (count > 0) {
			if (m_ref==null) {
				articleList=mBoardDao.list(map); //애초에 이 단계에서 m_comments도 만들어져야 함...
				//map2.put("m_number", articleList.get(0).getM_number());
				//System.out.println("articleList.get(0).getM_number()=>"+articleList.get(0).getM_number());
				//commentList=mBoardDao.getCommentCountForList(map2);
				//System.out.println("tq=>"+commentList);
				//m_numbers=articleList.get(0);
				
				//m_number=m_numbers.getM_number();
				//m_comments=mBoardDao.getCommentCountForList(m_number);
				//m_numbers.setM_comments(m_comments);
				//commentList.add(m_numbers);
				
				
				
				
			}else if (Integer.parseInt(m_ref)==1 || Integer.parseInt(m_ref)==2) {
				articleList=mBoardDao.list_mref(map);
			}else if (Integer.parseInt(m_ref)==4) {
				articleList=mBoardDao.list_hot(map);
			}
			System.out.println("m_numbers=>"+m_numbers);
			System.out.println("commentList=>"+commentList);
			System.out.println("m_number=>"+m_number);
			System.out.println("m_comments=>"+m_comments);
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
		
		
		
		ModelAndView mav=new ModelAndView("/board/marvel/M_List");
		mav.addObject("count", count);
		mav.addObject("articleList", articleList);
		mav.addObject("pgList", pgList);
		
		mav.addObject("count2", count2);
		mav.addObject("noticeList", noticeList);
		mav.addObject("pgList2", pgList2);
		
		mav.addObject("m_comments", m_comments);
		mav.addObject("commentList", commentList);
		
		return mav;
	}
	
}

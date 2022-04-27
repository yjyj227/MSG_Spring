package com.board.ring.controller;

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

import com.board.ring.dao.RBoardDao;
import com.board.ring.domain.RBoardDTO;
import com.board.notice.dao.NoticeDao;
import com.board.notice.domain.NoticeDTO;

@Component
@Controller
public class R_ListController {
	
	private Logger log=Logger.getLogger(this.getClass());
	
	@Autowired
	private RBoardDao rBoardDao;
	@Autowired
	private NoticeDao noticeDao;
	
	@RequestMapping("/R_List.do")
	public ModelAndView process(
			@RequestParam(value="pageNum", defaultValue="1") String currentPage,
			@RequestParam(value="search", defaultValue="") String search,
			@RequestParam(value="searchtext", defaultValue="") String searchtext,
			HttpServletRequest request
			) {
		
		if (log.isDebugEnabled()) { //로그객체가 디버깅모드 상태인지 아닌지를 체크
			System.out.println("/R_List.do 요청중"); //?을 출력X
			log.debug("currentPage:"+currentPage); //?을 출력 가능(select ~ where num=? 등의 ?)
			log.debug("search=>"+search);
			log.debug("searchtext=>"+searchtext);
		}
		Map<String, Object> map=new HashMap<String, Object>();
		
		String r_ref=request.getParameter("r_ref");
		System.out.println("r_ref=>"+r_ref);
		
		map.put("r_ref", r_ref);
		map.put("search", search);
		map.put("searchtext", searchtext);
		
		int count=0;
		
		if (r_ref==null) {
			count=rBoardDao.getRowCount(map);
		}else if (Integer.parseInt(r_ref)==1 || Integer.parseInt(r_ref)==2) {
			count=rBoardDao.getRowCount_rref(map);
		}else if (Integer.parseInt(r_ref)==4) {
			count=rBoardDao.getRowCount_hot(map);
		}
		
		System.out.println("R_ListController클래스의 count=>"+count);
		
		//PagingUtil page=new PagingUtil(currentPage, count, 10, 10, "R_List.do");
		Hashtable<String, Integer> pgList=rBoardDao.pageList(currentPage, count);
		
		map.put("start", pgList.get("startRow"));
		map.put("end", pgList.get("endRow"));
		
		List<RBoardDTO> articleList=null;
		
		List<RBoardDTO> commentList=new ArrayList(pgList.get("endRow"));
		//commentList=;
		RBoardDTO r_numbers=new RBoardDTO();
		int r_number=0;
		int r_comments=0;
		if (count > 0) {
			if (r_ref==null) {
				articleList=rBoardDao.list(map); //애초에 이 단계에서 r_comments도 만들어져야 함...
				//map2.put("r_number", articleList.get(0).getR_number());
				//System.out.println("articleList.get(0).getR_number()=>"+articleList.get(0).getR_number());
				//commentList=rBoardDao.getCommentCountForList(map2);
				//System.out.println("tq=>"+commentList);
				//r_numbers=articleList.get(0);
				
				//r_number=r_numbers.getR_number();
				//r_comments=rBoardDao.getCommentCountForList(r_number);
				//r_numbers.setR_comments(r_comments);
				//commentList.add(r_numbers);
				
				
				
				
			}else if (Integer.parseInt(r_ref)==1 || Integer.parseInt(r_ref)==2) {
				articleList=rBoardDao.list_rref(map);
			}else if (Integer.parseInt(r_ref)==4) {
				articleList=rBoardDao.list_hot(map);
			}
			System.out.println("r_numbers=>"+r_numbers);
			System.out.println("commentList=>"+commentList);
			System.out.println("r_number=>"+r_number);
			System.out.println("r_comments=>"+r_comments);
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
		
		
		
		ModelAndView mav=new ModelAndView("/board/ring/R_List");
		mav.addObject("count", count);
		mav.addObject("articleList", articleList);
		mav.addObject("pgList", pgList);
		
		mav.addObject("count2", count2);
		mav.addObject("noticeList", noticeList);
		mav.addObject("pgList2", pgList2);
		
		mav.addObject("r_comments", r_comments);
		mav.addObject("commentList", commentList);
		
		return mav;
	}
	
}

package com.board.zzang.controller;

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

import com.board.zzang.dao.ZBoardDao;
import com.board.zzang.domain.ZBoardDTO;
import com.board.notice.dao.NoticeDao;
import com.board.notice.domain.NoticeDTO;

@Component
@Controller
public class Z_ListController {
	
	private Logger log=Logger.getLogger(this.getClass());
	
	@Autowired
	private ZBoardDao zBoardDao;
	@Autowired
	private NoticeDao noticeDao;
	
	@RequestMapping("/Z_List.do")
	public ModelAndView process(
			@RequestParam(value="pageNum", defaultValue="1") String currentPage,
			@RequestParam(value="search", defaultValue="") String search,
			@RequestParam(value="searchtext", defaultValue="") String searchtext,
			HttpServletRequest request
			) {
		
		if (log.isDebugEnabled()) { //로그객체가 디버깅모드 상태인지 아닌지를 체크
			System.out.println("/Z_List.do 요청중"); //?을 출력X
			log.debug("currentPage:"+currentPage); //?을 출력 가능(select ~ where num=? 등의 ?)
			log.debug("search=>"+search);
			log.debug("searchtext=>"+searchtext);
		}
		Map<String, Object> map=new HashMap<String, Object>();
		
		String z_ref=request.getParameter("z_ref");
		System.out.println("z_ref=>"+z_ref);
		
		map.put("z_ref", z_ref);
		map.put("search", search);
		map.put("searchtext", searchtext);
		
		int count=0;
		
		if (z_ref==null) {
			count=zBoardDao.getRowCount(map);
		}else if (Integer.parseInt(z_ref)==1 || Integer.parseInt(z_ref)==2) {
			count=zBoardDao.getRowCount_zref(map);
		}else if (Integer.parseInt(z_ref)==4) {
			count=zBoardDao.getRowCount_hot(map);
		}
		
		System.out.println("Z_ListController클래스의 count=>"+count);
		
		//PagingUtil page=new PagingUtil(currentPage, count, 10, 10, "Z_List.do");
		Hashtable<String, Integer> pgList=zBoardDao.pageList(currentPage, count);
		
		map.put("start", pgList.get("startRow"));
		map.put("end", pgList.get("endRow"));
		
		List<ZBoardDTO> articleList=null;
		
		List<ZBoardDTO> commentList=new ArrayList(pgList.get("endRow"));
		//commentList=;
		ZBoardDTO z_numbers=new ZBoardDTO();
		int z_number=0;
		int z_comments=0;
		if (count > 0) {
			if (z_ref==null) {
				articleList=zBoardDao.list(map); //애초에 이 단계에서 z_comments도 만들어져야 함...
				//map2.put("z_number", articleList.get(0).getZ_number());
				//System.out.println("articleList.get(0).getZ_number()=>"+articleList.get(0).getZ_number());
				//commentList=zBoardDao.getCommentCountForList(map2);
				//System.out.println("tq=>"+commentList);
				//z_numbers=articleList.get(0);
				
				//z_number=z_numbers.getZ_number();
				//z_comments=zBoardDao.getCommentCountForList(z_number);
				//z_numbers.setZ_comments(z_comments);
				//commentList.add(z_numbers);
				
				
				
				
			}else if (Integer.parseInt(z_ref)==1 || Integer.parseInt(z_ref)==2) {
				articleList=zBoardDao.list_zref(map);
			}else if (Integer.parseInt(z_ref)==4) {
				articleList=zBoardDao.list_hot(map);
			}
			System.out.println("z_numbers=>"+z_numbers);
			System.out.println("commentList=>"+commentList);
			System.out.println("z_number=>"+z_number);
			System.out.println("z_comments=>"+z_comments);
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
		
		
		
		ModelAndView mav=new ModelAndView("/board/zzang/Z_List");
		mav.addObject("count", count);
		mav.addObject("articleList", articleList);
		mav.addObject("pgList", pgList);
		
		mav.addObject("count2", count2);
		mav.addObject("noticeList", noticeList);
		mav.addObject("pgList2", pgList2);
		
		mav.addObject("z_comments", z_comments);
		mav.addObject("commentList", commentList);
		
		return mav;
	}
	
}

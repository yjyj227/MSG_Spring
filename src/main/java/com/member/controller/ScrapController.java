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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.board.dao.BoardDao;
import com.board.domain.BoardDTO;

@Component
@Controller
public class ScrapController {

	private Logger log=Logger.getLogger(this.getClass());
	
	@Autowired
	private BoardDao boardDao;
	
	@ModelAttribute("command")
	public BoardDTO forBacking() {
		System.out.println("ScrapController의 forBacking() 호출됨");
		return new BoardDTO();
	}
	
	@RequestMapping(value="/Scrap.do")
	public String submit(@ModelAttribute("command") BoardDTO board,
						 BindingResult result,
						 HttpServletRequest request) {
		
		if (log.isDebugEnabled()) {
			System.out.println("/Scrap.do 요청중");
			log.debug("BoardDTO : "+board);
		}
		
		int scrap_number=boardDao.getNewScrapNum()+1;
		HttpSession session=request.getSession();
		String mem_id=(String)session.getAttribute("idKey");
		
		int s_number=Integer.parseInt(request.getParameter("s_number"));
		int s_category=Integer.parseInt(request.getParameter("s_category"));
		System.out.println("s_category=>"+s_category);
		String s_title=request.getParameter("s_title");
		String s_nickname=request.getParameter("s_nickname");
		
		String init=boardDao.makeInit(s_category);
		board.setInit(init);
		
		String s_url=boardDao.makeScrapUrl(init, s_number);
		System.out.println("생성된 s_url=>"+s_url);
		
		board.setScrap_number(scrap_number);
		board.setMem_id(mem_id);
		board.setS_number(s_number);
		board.setS_category(s_category);
		board.setS_title(s_title);
		board.setS_url(s_url);
		board.setS_nickname(s_nickname);
		
		int scrap=boardDao.scrap(board);
		System.out.println("스크랩 성공 여부(scrap)=>"+scrap);
		
		int scrapup=boardDao.scrapUp(board);
		
		request.setAttribute("s_url", s_url);
		return "redirect:/"+s_url;
	}
	
	
	@RequestMapping(value="/Scrap_List.do")
	public ModelAndView process(@RequestParam(value="pageNum", defaultValue="1") String currentPage,
								HttpServletRequest request) {
		
		if (log.isDebugEnabled()) {
			System.out.println("/Scrap_List.do 요청중");
			log.debug("currentPage : "+currentPage);
		}
		
		Map<String, Object> map=new HashMap<String, Object>();
		
		HttpSession session=request.getSession();
		String mem_id=(String)session.getAttribute("idKey");
		//String mem_id=request.getParameter("mem_id");
		System.out.println("ScrapController의 mem_id=>"+mem_id);
		map.put("mem_id", mem_id);
		
		int count=boardDao.getScrapArticleCount(mem_id);
		System.out.println("스크랩 수(count)=>"+count);
		
		Hashtable<String, Object> pgList=boardDao.pageList(currentPage, count);
		
		map.put("start", pgList.get("startRow"));
		map.put("end", pgList.get("endRow"));
		
		List<BoardDTO> articleList=null;
		if (count > 0) {
			articleList=boardDao.getScrapArticles(map);
		}else {
			articleList=Collections.EMPTY_LIST;
		}
		
		ModelAndView mav=new ModelAndView("/member/Scrap_List");
		mav.addObject("count", count);
		mav.addObject("articleList", articleList);
		mav.addObject("pgList", pgList);
		
		return mav;
	}
	
	//스크랩 삭제폼으로 이동
	@RequestMapping(value="/Scrap_Delete.do", method=RequestMethod.GET)
	public String form(HttpServletRequest request) {
		
		//scrap_number, s_category, s_number
		HttpSession session=request.getSession();
		String mem_id=(String)session.getAttribute("idKey");
		//String mem_id=request.getParameter("mem_id");
		System.out.println("ScrapController form()의 mem_id=>"+mem_id);
		
		int scrap_number=Integer.parseInt(request.getParameter("scrap_number"));
		int s_category=Integer.parseInt(request.getParameter("s_category"));
		int s_number=Integer.parseInt(request.getParameter("s_number"));
		System.out.println("scrap_number=>"+scrap_number+", s_category=>"+s_category+", s_number=>"+s_number);
		
		request.setAttribute("mem_id", mem_id);
		request.setAttribute("scrap_number", scrap_number);
		request.setAttribute("s_category", s_category);
		request.setAttribute("s_number", s_number);
		
		return "/member/Scrap_Delete";
	}
	
	//스크랩 삭제
	@RequestMapping(value="/Scrap_DeleteProc.do", method=RequestMethod.GET)
	public ModelAndView delete(@ModelAttribute("command") BoardDTO board,
							   HttpServletRequest request) {
		
		if (log.isDebugEnabled()) {
			System.out.println("/Scrap_DeleteProc.do 요청중");
			log.debug("BoardDTO : "+board);
		}
		
		HttpSession session=request.getSession();
		String mem_id=(String)session.getAttribute("idKey");
		//String mem_id=request.getParameter("mem_id");
		System.out.println("ScrapController delete()의 mem_id=>"+mem_id);
		
		int scrap_number=Integer.parseInt(request.getParameter("scrap_number"));
		int s_category=Integer.parseInt(request.getParameter("s_category"));
		int s_number=Integer.parseInt(request.getParameter("s_number"));
		System.out.println("scrap_number=>"+scrap_number+", s_category=>"+s_category+", s_number=>"+s_number);
		String init=boardDao.makeInit(s_category);
		System.out.println("init=>"+init);
		
		board.setScrap_number(scrap_number);
		board.setS_category(s_category);
		board.setS_number(s_number);
		board.setInit(init);
		
		int delete=boardDao.deleteScrapArticle(scrap_number);
		System.out.println("스크랩 삭제 여부(delete)=>"+delete);
		
		int scrapdown=boardDao.scrapDown(board);
		System.out.println("scrapdown=>"+scrapdown);
		
		ModelAndView mav=new ModelAndView("/member/Scrap_DeleteProc");
		mav.addObject("mem_id", mem_id);
		mav.addObject("delete", delete);
		return mav;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

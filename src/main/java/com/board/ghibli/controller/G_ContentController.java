package com.board.ghibli.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.board.ghibli.dao.GBoardDao;
import com.board.ghibli.dao.GCommentDao;
import com.board.ghibli.domain.GBoardDTO;
import com.board.ghibli.domain.GCommentDTO;
import com.board.util.StringUtil;

@Component
@Controller
public class G_ContentController {

	private Logger log=Logger.getLogger(this.getClass());
	
	@Autowired
	private GBoardDao gBoardDao;
	@Autowired
	private GCommentDao gCommentDao;
	
	@RequestMapping("/G_Content.do")
	public ModelAndView process(@RequestParam("g_number") int g_number,
								HttpServletRequest request) {
		if (log.isDebugEnabled()) {
			System.out.println("/G_Content.do 요청중");
			log.debug("g_number=>"+g_number);
		}
		
		gBoardDao.updateHit(g_number);
		GBoardDTO article=gBoardDao.getBoard(g_number);
		article.setG_body(StringUtil.parseBr(article.getG_body()));
		
		String mem_id=article.getMem_id();
		System.out.println("G_ContentController의 mem_id=>"+mem_id);
		
		request.setAttribute("article", article);
		request.setAttribute("content_mem_id", mem_id);
		
		//댓글
		int g_cnumber=0, g_cref=1, g_cre_step=0, g_cre_level=0;
		System.out.println("request.getParameter('g_cnumber')=>"+request.getParameter("g_cnumber"));
		
		if (request.getParameter("g_cnumber")!=null) {
			g_cnumber=Integer.parseInt(request.getParameter("g_cnumber"));
			g_cref=Integer.parseInt(request.getParameter("g_cref"));
			g_cre_step=Integer.parseInt(request.getParameter("g_cre_step"));
			g_cre_level=Integer.parseInt(request.getParameter("g_cre_level"));
			System.out.println("매개변수 확인");
			System.out.println("g_cnumber=>"+g_cnumber+", g_cref=>"+g_cref+", g_cre_step=>"+g_cre_step+", g_cre_level=>"+g_cre_level);
		}else {
			System.out.println("g_cnumber가 null일 때-->> g_cnumber=>"+g_cnumber+", g_cref=>"+g_cref+", g_cre_step=>"+g_cre_step+", g_cre_level=>"+g_cre_level);
		}
		
		/*
		request.setAttribute("g_cnumber", g_cnumber);
		request.setAttribute("g_cref", g_cref);
		request.setAttribute("g_cre_step", g_cre_step);
		request.setAttribute("g_cre_level", g_cre_level);
		*/
		
		//댓글처리
		List<GCommentDTO> commentList=gCommentDao.getComment(g_number);
		
		//댓글 수
		int cocount=0;
		cocount=gCommentDao.getCommentCount(g_number);
		System.out.println("댓글 수=>"+cocount);
		
		
		
		//return new ModelAndView("/board/ghibli/G_Content", "article", article);
		ModelAndView mav=new ModelAndView("/board/ghibli/G_Content");
		mav.addObject("article", article);
		mav.addObject("commentList", commentList);
		mav.addObject("g_cnumber", g_cnumber);
		mav.addObject("g_cref", g_cref);
		mav.addObject("g_cre_step", g_cre_step);
		mav.addObject("g_cre_level", g_cre_level);
		mav.addObject("cocount", cocount);
		return mav;
	}
}

package com.board.harry.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.board.harry.dao.HBoardDao;
import com.board.harry.dao.HCommentDao;
import com.board.harry.domain.HBoardDTO;
import com.board.harry.domain.HCommentDTO;
import com.board.util.StringUtil;

@Component
@Controller
public class H_ContentController {

	private Logger log=Logger.getLogger(this.getClass());
	
	@Autowired
	private HBoardDao hBoardDao;
	@Autowired
	private HCommentDao hCommentDao;
	
	@RequestMapping("/H_Content.do")
	public ModelAndView process(@RequestParam("h_number") int h_number,
								HttpServletRequest request) {
		if (log.isDebugEnabled()) {
			System.out.println("/H_Content.do 요청중");
			log.debug("h_number=>"+h_number);
		}
		
		hBoardDao.updateHit(h_number);
		HBoardDTO article=hBoardDao.getBoard(h_number);
		article.setH_body(StringUtil.parseBr(article.getH_body()));
		
		String mem_id=article.getMem_id();
		System.out.println("H_ContentController의 mem_id=>"+mem_id);
		
		request.setAttribute("article", article);
		request.setAttribute("content_mem_id", mem_id);
		
		//댓글
		int h_cnumber=0, h_cref=1, h_cre_step=0, h_cre_level=0;
		System.out.println("request.getParameter('h_cnumber')=>"+request.getParameter("h_cnumber"));
		
		if (request.getParameter("h_cnumber")!=null) {
			h_cnumber=Integer.parseInt(request.getParameter("h_cnumber"));
			h_cref=Integer.parseInt(request.getParameter("h_cref"));
			h_cre_step=Integer.parseInt(request.getParameter("h_cre_step"));
			h_cre_level=Integer.parseInt(request.getParameter("h_cre_level"));
			System.out.println("매개변수 확인");
			System.out.println("h_cnumber=>"+h_cnumber+", h_cref=>"+h_cref+", h_cre_step=>"+h_cre_step+", h_cre_level=>"+h_cre_level);
		}else {
			System.out.println("h_cnumber가 null일 때-->> h_cnumber=>"+h_cnumber+", h_cref=>"+h_cref+", h_cre_step=>"+h_cre_step+", h_cre_level=>"+h_cre_level);
		}
		
		/*
		request.setAttribute("h_cnumber", h_cnumber);
		request.setAttribute("h_cref", h_cref);
		request.setAttribute("h_cre_step", h_cre_step);
		request.setAttribute("h_cre_level", h_cre_level);
		*/
		
		//댓글처리
		List<HCommentDTO> commentList=hCommentDao.getComment(h_number);
		
		//댓글 수
		int cocount=0;
		cocount=hCommentDao.getCommentCount(h_number);
		System.out.println("댓글 수=>"+cocount);
		
		
		
		//return new ModelAndView("/board/harry/H_Content", "article", article);
		ModelAndView mav=new ModelAndView("/board/harry/H_Content");
		mav.addObject("article", article);
		mav.addObject("commentList", commentList);
		mav.addObject("h_cnumber", h_cnumber);
		mav.addObject("h_cref", h_cref);
		mav.addObject("h_cre_step", h_cre_step);
		mav.addObject("h_cre_level", h_cre_level);
		mav.addObject("cocount", cocount);
		return mav;
	}
}

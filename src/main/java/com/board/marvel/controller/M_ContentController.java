package com.board.marvel.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.board.marvel.dao.MBoardDao;
import com.board.marvel.dao.MCommentDao;
import com.board.marvel.domain.MBoardDTO;
import com.board.marvel.domain.MCommentDTO;
import com.board.util.StringUtil;

@Component
@Controller
public class M_ContentController {

	private Logger log=Logger.getLogger(this.getClass());
	
	@Autowired
	private MBoardDao mBoardDao;
	@Autowired
	private MCommentDao mCommentDao;
	
	@RequestMapping("/M_Content.do")
	public ModelAndView process(@RequestParam("m_number") int m_number,
								HttpServletRequest request) {
		if (log.isDebugEnabled()) {
			System.out.println("/M_Content.do 요청중");
			log.debug("m_number=>"+m_number);
		}
		
		mBoardDao.updateHit(m_number);
		MBoardDTO article=mBoardDao.getBoard(m_number);
		article.setM_body(StringUtil.parseBr(article.getM_body()));
		
		String mem_id=article.getMem_id();
		System.out.println("M_ContentController의 mem_id=>"+mem_id);
		
		request.setAttribute("article", article);
		request.setAttribute("content_mem_id", mem_id);
		
		//댓글
		int m_cnumber=0, m_cref=1, m_cre_step=0, m_cre_level=0;
		System.out.println("request.getParameter('m_cnumber')=>"+request.getParameter("m_cnumber"));
		
		if (request.getParameter("m_cnumber")!=null) {
			m_cnumber=Integer.parseInt(request.getParameter("m_cnumber"));
			m_cref=Integer.parseInt(request.getParameter("m_cref"));
			m_cre_step=Integer.parseInt(request.getParameter("m_cre_step"));
			m_cre_level=Integer.parseInt(request.getParameter("m_cre_level"));
			System.out.println("매개변수 확인");
			System.out.println("m_cnumber=>"+m_cnumber+", m_cref=>"+m_cref+", m_cre_step=>"+m_cre_step+", m_cre_level=>"+m_cre_level);
		}else {
			System.out.println("m_cnumber가 null일 때-->> m_cnumber=>"+m_cnumber+", m_cref=>"+m_cref+", m_cre_step=>"+m_cre_step+", m_cre_level=>"+m_cre_level);
		}
		
		/*
		request.setAttribute("m_cnumber", m_cnumber);
		request.setAttribute("m_cref", m_cref);
		request.setAttribute("m_cre_step", m_cre_step);
		request.setAttribute("m_cre_level", m_cre_level);
		*/
		
		//댓글처리
		List<MCommentDTO> commentList=mCommentDao.getComment(m_number);
		
		//댓글 수
		int cocount=0;
		cocount=mCommentDao.getCommentCount(m_number);
		System.out.println("댓글 수=>"+cocount);
		
		
		
		//return new ModelAndView("/board/marvel/M_Content", "article", article);
		ModelAndView mav=new ModelAndView("/board/marvel/M_Content");
		mav.addObject("article", article);
		mav.addObject("commentList", commentList);
		mav.addObject("m_cnumber", m_cnumber);
		mav.addObject("m_cref", m_cref);
		mav.addObject("m_cre_step", m_cre_step);
		mav.addObject("m_cre_level", m_cre_level);
		mav.addObject("cocount", cocount);
		return mav;
	}
}

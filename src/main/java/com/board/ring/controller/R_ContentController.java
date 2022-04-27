package com.board.ring.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.board.ring.dao.RBoardDao;
import com.board.ring.dao.RCommentDao;
import com.board.ring.domain.RBoardDTO;
import com.board.ring.domain.RCommentDTO;
import com.board.util.StringUtil;

@Component
@Controller
public class R_ContentController {

	private Logger log=Logger.getLogger(this.getClass());
	
	@Autowired
	private RBoardDao rBoardDao;
	@Autowired
	private RCommentDao rCommentDao;
	
	@RequestMapping("/R_Content.do")
	public ModelAndView process(@RequestParam("r_number") int r_number,
								HttpServletRequest request) {
		if (log.isDebugEnabled()) {
			System.out.println("/R_Content.do 요청중");
			log.debug("r_number=>"+r_number);
		}
		
		rBoardDao.updateHit(r_number);
		RBoardDTO article=rBoardDao.getBoard(r_number);
		article.setR_body(StringUtil.parseBr(article.getR_body()));
		
		String mem_id=article.getMem_id();
		System.out.println("R_ContentController의 mem_id=>"+mem_id);
		
		request.setAttribute("article", article);
		request.setAttribute("content_mem_id", mem_id);
		
		//댓글
		int r_cnumber=0, r_cref=1, r_cre_step=0, r_cre_level=0;
		System.out.println("request.getParameter('r_cnumber')=>"+request.getParameter("r_cnumber"));
		
		if (request.getParameter("r_cnumber")!=null) {
			r_cnumber=Integer.parseInt(request.getParameter("r_cnumber"));
			r_cref=Integer.parseInt(request.getParameter("r_cref"));
			r_cre_step=Integer.parseInt(request.getParameter("r_cre_step"));
			r_cre_level=Integer.parseInt(request.getParameter("r_cre_level"));
			System.out.println("매개변수 확인");
			System.out.println("r_cnumber=>"+r_cnumber+", r_cref=>"+r_cref+", r_cre_step=>"+r_cre_step+", r_cre_level=>"+r_cre_level);
		}else {
			System.out.println("r_cnumber가 null일 때-->> r_cnumber=>"+r_cnumber+", r_cref=>"+r_cref+", r_cre_step=>"+r_cre_step+", r_cre_level=>"+r_cre_level);
		}
		
		/*
		request.setAttribute("r_cnumber", r_cnumber);
		request.setAttribute("r_cref", r_cref);
		request.setAttribute("r_cre_step", r_cre_step);
		request.setAttribute("r_cre_level", r_cre_level);
		*/
		
		//댓글처리
		List<RCommentDTO> commentList=rCommentDao.getComment(r_number);
		
		//댓글 수
		int cocount=0;
		cocount=rCommentDao.getCommentCount(r_number);
		System.out.println("댓글 수=>"+cocount);
		
		
		
		//return new ModelAndView("/board/ring/R_Content", "article", article);
		ModelAndView mav=new ModelAndView("/board/ring/R_Content");
		mav.addObject("article", article);
		mav.addObject("commentList", commentList);
		mav.addObject("r_cnumber", r_cnumber);
		mav.addObject("r_cref", r_cref);
		mav.addObject("r_cre_step", r_cre_step);
		mav.addObject("r_cre_level", r_cre_level);
		mav.addObject("cocount", cocount);
		return mav;
	}
}

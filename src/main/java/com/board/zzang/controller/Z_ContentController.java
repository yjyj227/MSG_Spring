package com.board.zzang.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.board.zzang.dao.ZBoardDao;
import com.board.zzang.dao.ZCommentDao;
import com.board.zzang.domain.ZBoardDTO;
import com.board.zzang.domain.ZCommentDTO;
import com.board.util.StringUtil;

@Component
@Controller
public class Z_ContentController {

	private Logger log=Logger.getLogger(this.getClass());
	
	@Autowired
	private ZBoardDao zBoardDao;
	@Autowired
	private ZCommentDao zCommentDao;
	
	@RequestMapping("/Z_Content.do")
	public ModelAndView process(@RequestParam("z_number") int z_number,
								HttpServletRequest request) {
		if (log.isDebugEnabled()) {
			System.out.println("/Z_Content.do 요청중");
			log.debug("z_number=>"+z_number);
		}
		
		zBoardDao.updateHit(z_number);
		ZBoardDTO article=zBoardDao.getBoard(z_number);
		article.setZ_body(StringUtil.parseBr(article.getZ_body()));
		
		String mem_id=article.getMem_id();
		System.out.println("Z_ContentController의 mem_id=>"+mem_id);
		
		request.setAttribute("article", article);
		request.setAttribute("content_mem_id", mem_id);
		
		//댓글
		int z_cnumber=0, z_cref=1, z_cre_step=0, z_cre_level=0;
		System.out.println("request.getParameter('z_cnumber')=>"+request.getParameter("z_cnumber"));
		
		if (request.getParameter("z_cnumber")!=null) {
			z_cnumber=Integer.parseInt(request.getParameter("z_cnumber"));
			z_cref=Integer.parseInt(request.getParameter("z_cref"));
			z_cre_step=Integer.parseInt(request.getParameter("z_cre_step"));
			z_cre_level=Integer.parseInt(request.getParameter("z_cre_level"));
			System.out.println("매개변수 확인");
			System.out.println("z_cnumber=>"+z_cnumber+", z_cref=>"+z_cref+", z_cre_step=>"+z_cre_step+", z_cre_level=>"+z_cre_level);
		}else {
			System.out.println("z_cnumber가 null일 때-->> z_cnumber=>"+z_cnumber+", z_cref=>"+z_cref+", z_cre_step=>"+z_cre_step+", z_cre_level=>"+z_cre_level);
		}
		
		/*
		request.setAttribute("z_cnumber", z_cnumber);
		request.setAttribute("z_cref", z_cref);
		request.setAttribute("z_cre_step", z_cre_step);
		request.setAttribute("z_cre_level", z_cre_level);
		*/
		
		//댓글처리
		List<ZCommentDTO> commentList=zCommentDao.getComment(z_number);
		
		//댓글 수
		int cocount=0;
		cocount=zCommentDao.getCommentCount(z_number);
		System.out.println("댓글 수=>"+cocount);
		
		
		
		//return new ModelAndView("/board/zzang/Z_Content", "article", article);
		ModelAndView mav=new ModelAndView("/board/zzang/Z_Content");
		mav.addObject("article", article);
		mav.addObject("commentList", commentList);
		mav.addObject("z_cnumber", z_cnumber);
		mav.addObject("z_cref", z_cref);
		mav.addObject("z_cre_step", z_cre_step);
		mav.addObject("z_cre_level", z_cre_level);
		mav.addObject("cocount", cocount);
		return mav;
	}
}

package com.board.ghibli.controller;

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
import org.springframework.web.servlet.ModelAndView;

import com.board.ghibli.dao.GBoardDao;
import com.board.ghibli.dao.GCommentDao;
import com.board.ghibli.domain.GCommentDTO;
import com.member.dao.MemberDao;
import com.member.domain.MemberDTO;

@Component
@Controller
public class G_CommentDeleteController {

	private Logger log=Logger.getLogger(this.getClass());
	
	@Autowired
	private GCommentDao gCommentDao;
	@Autowired
	private MemberDao memberDao;
	@Autowired
	private GBoardDao gBoardDao;
	
	//1. 댓글삭제 폼으로 이동
	@RequestMapping(value="/G_CommentDelete.do", method=RequestMethod.GET)
	public ModelAndView form(HttpServletRequest request) {
		String g_cmem_id=request.getParameter("g_cmem_id");
		System.out.println("G_Content에서 넘어온 g_cmem_id=>"+g_cmem_id);
		//request.setAttribute("g_cmem_id", g_cmem_id);
		ModelAndView mav=new ModelAndView("/board/ghibli/G_CommentDelete");
		mav.addObject("g_cmem_id", g_cmem_id);
		return mav;
	}
	
	@ModelAttribute("command")
	public GCommentDTO forBacking() {
		System.out.println("초기화");
		System.out.println("forBacking()에서 만들어진 new GCommentDTO()=>"+new GCommentDTO());
		return new GCommentDTO();
	}
	
	@RequestMapping(value="/G_CommentDeleteProc.do", method=RequestMethod.GET)
	public String submit(@ModelAttribute("command") GCommentDTO com,
						 BindingResult result,
						 HttpServletRequest request) {
		
		if (log.isDebugEnabled()) {
			System.out.println("/G_CommentDeleteProc.do 요청중");
			log.debug("GCommentDTO : "+com);
		}
		
		String mem_id=request.getParameter("g_cmem_id");
		System.out.println("G_CommentDeleteController의 form()에서 넘어온 g_cmem_id=>"+mem_id);
		request.setAttribute("mem_id", mem_id);
		int g_number=com.getG_number();
		System.out.println("G_CommentDeleteController submit의 g_cnumber=>"+com.getG_cnumber());
		
		//일단 대댓글이 딸린 댓글인지 알아봐야 함
		int lower=gCommentDao.lowerComment(com);
		System.out.println("대댓글 딸림 여부(lower)=>"+lower);
		
		int delete;
		if (lower > 0) { //대댓글이 딸린 댓글
			delete=gCommentDao.upDeleteComment(com.getG_cnumber());
			System.out.println("lower>0");
		}else {
			delete=gCommentDao.deleteComment(com.getG_cnumber());
			System.out.println("lower=0");
		}
		System.out.println("댓글 삭제 성공 여부(delete)=>"+delete);
		
		if (delete > 0) {
			int pointdown=gCommentDao.pointdownC(mem_id);
			System.out.println("댓글 삭제시 포인트 회수 성공 여부(pointdown)=>"+pointdown);
			int point=gBoardDao.getPoint(mem_id);
			
			if (pointdown > 0) {
				System.out.println("등급 변동 전 현재 등급=>"+gBoardDao.getGrade(mem_id));
				int gradeup=0;
				if (point < 100) {
					gradeup=gBoardDao.changeGrade1(mem_id);
				} else if (point >= 100 && point < 500) {
					gradeup=gBoardDao.changeGrade2(mem_id);
				} else if (point >= 500 && point < 2000) {
					gradeup=gBoardDao.changeGrade3(mem_id);
				} else if (point >= 2000 && point < 10000) {
					gradeup=gBoardDao.changeGrade4(mem_id);
				} else if (point >= 10000) {
					gradeup=gBoardDao.changeGrade5(mem_id);
				}
				System.out.println("댓글 삭제 후 등급 변동 여부(gradeup)=>"+gradeup);
				System.out.println("등급 변동 후 현재 등급=>"+gBoardDao.getGrade(mem_id));
			}
			MemberDTO mcom=memberDao.getNPG(mem_id);
			int log_grade=mcom.getLog_grade();
			System.out.println("log_grade=>"+log_grade);
			
			MemberDTO mcom2=memberDao.selectPG(mem_id);
			memberDao.syncPG(mcom2);
			
			
			HttpSession session=request.getSession();
			session.setAttribute("mem_grade", log_grade);
		}
		
		return "redirect:/G_Content.do?g_number="+g_number;
	}
}

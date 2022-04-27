package com.board.marvel.controller;

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

import com.board.marvel.dao.MBoardDao;
import com.board.marvel.dao.MCommentDao;
import com.board.marvel.domain.MCommentDTO;
import com.member.dao.MemberDao;
import com.member.domain.MemberDTO;

@Component
@Controller
public class M_CommentDeleteController {

	private Logger log=Logger.getLogger(this.getClass());
	
	@Autowired
	private MCommentDao mCommentDao;
	@Autowired
	private MemberDao memberDao;
	@Autowired
	private MBoardDao mBoardDao;
	
	//1. 댓글삭제 폼으로 이동
	@RequestMapping(value="/M_CommentDelete.do", method=RequestMethod.GET)
	public ModelAndView form(HttpServletRequest request) {
		String m_cmem_id=request.getParameter("m_cmem_id");
		System.out.println("M_Content에서 넘어온 m_cmem_id=>"+m_cmem_id);
		//request.setAttribute("m_cmem_id", m_cmem_id);
		ModelAndView mav=new ModelAndView("/board/marvel/M_CommentDelete");
		mav.addObject("m_cmem_id", m_cmem_id);
		return mav;
	}
	
	@ModelAttribute("command")
	public MCommentDTO forBacking() {
		System.out.println("초기화");
		System.out.println("forBacking()에서 만들어진 new MCommentDTO()=>"+new MCommentDTO());
		return new MCommentDTO();
	}
	
	@RequestMapping(value="/M_CommentDeleteProc.do", method=RequestMethod.GET)
	public String submit(@ModelAttribute("command") MCommentDTO com,
						 BindingResult result,
						 HttpServletRequest request) {
		
		if (log.isDebugEnabled()) {
			System.out.println("/M_CommentDeleteProc.do 요청중");
			log.debug("MCommentDTO : "+com);
		}
		
		String mem_id=request.getParameter("m_cmem_id");
		System.out.println("M_CommentDeleteController의 form()에서 넘어온 m_cmem_id=>"+mem_id);
		request.setAttribute("mem_id", mem_id);
		int m_number=com.getM_number();
		System.out.println("M_CommentDeleteController submit의 m_cnumber=>"+com.getM_cnumber());
		
		//일단 대댓글이 딸린 댓글인지 알아봐야 함
		int lower=mCommentDao.lowerComment(com);
		System.out.println("대댓글 딸림 여부(lower)=>"+lower);
		
		int delete;
		if (lower > 0) { //대댓글이 딸린 댓글
			delete=mCommentDao.upDeleteComment(com.getM_cnumber());
			System.out.println("lower>0");
		}else {
			delete=mCommentDao.deleteComment(com.getM_cnumber());
			System.out.println("lower=0");
		}
		System.out.println("댓글 삭제 성공 여부(delete)=>"+delete);
		
		if (delete > 0) {
			int pointdown=mCommentDao.pointdownC(mem_id);
			System.out.println("댓글 삭제시 포인트 회수 성공 여부(pointdown)=>"+pointdown);
			int point=mBoardDao.getPoint(mem_id);
			
			if (pointdown > 0) {
				System.out.println("등급 변동 전 현재 등급=>"+mBoardDao.getGrade(mem_id));
				int gradeup=0;
				if (point < 100) {
					gradeup=mBoardDao.changeGrade1(mem_id);
				} else if (point >= 100 && point < 500) {
					gradeup=mBoardDao.changeGrade2(mem_id);
				} else if (point >= 500 && point < 2000) {
					gradeup=mBoardDao.changeGrade3(mem_id);
				} else if (point >= 2000 && point < 10000) {
					gradeup=mBoardDao.changeGrade4(mem_id);
				} else if (point >= 10000) {
					gradeup=mBoardDao.changeGrade5(mem_id);
				}
				System.out.println("댓글 삭제 후 등급 변동 여부(gradeup)=>"+gradeup);
				System.out.println("등급 변동 후 현재 등급=>"+mBoardDao.getGrade(mem_id));
			}
			MemberDTO mcom=memberDao.getNPG(mem_id);
			int log_grade=mcom.getLog_grade();
			System.out.println("log_grade=>"+log_grade);
			
			MemberDTO mcom2=memberDao.selectPG(mem_id);
			memberDao.syncPG(mcom2);
			
			
			HttpSession session=request.getSession();
			session.setAttribute("mem_grade", log_grade);
		}
		
		return "redirect:/M_Content.do?m_number="+m_number;
	}
}

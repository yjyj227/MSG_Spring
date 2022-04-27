package com.board.zzang.controller;

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

import com.board.zzang.dao.ZBoardDao;
import com.board.zzang.dao.ZCommentDao;
import com.board.zzang.domain.ZCommentDTO;
import com.member.dao.MemberDao;
import com.member.domain.MemberDTO;

@Component
@Controller
public class Z_CommentDeleteController {

	private Logger log=Logger.getLogger(this.getClass());
	
	@Autowired
	private ZCommentDao zCommentDao;
	@Autowired
	private MemberDao memberDao;
	@Autowired
	private ZBoardDao zBoardDao;
	
	//1. 댓글삭제 폼으로 이동
	@RequestMapping(value="/Z_CommentDelete.do", method=RequestMethod.GET)
	public ModelAndView form(HttpServletRequest request) {
		String z_cmem_id=request.getParameter("z_cmem_id");
		System.out.println("Z_Content에서 넘어온 z_cmem_id=>"+z_cmem_id);
		//request.setAttribute("z_cmem_id", z_cmem_id);
		ModelAndView mav=new ModelAndView("/board/zzang/Z_CommentDelete");
		mav.addObject("z_cmem_id", z_cmem_id);
		return mav;
	}
	
	@ModelAttribute("command")
	public ZCommentDTO forBacking() {
		System.out.println("초기화");
		System.out.println("forBacking()에서 만들어진 new ZCommentDTO()=>"+new ZCommentDTO());
		return new ZCommentDTO();
	}
	
	@RequestMapping(value="/Z_CommentDeleteProc.do", method=RequestMethod.GET)
	public String submit(@ModelAttribute("command") ZCommentDTO com,
						 BindingResult result,
						 HttpServletRequest request) {
		
		if (log.isDebugEnabled()) {
			System.out.println("/Z_CommentDeleteProc.do 요청중");
			log.debug("ZCommentDTO : "+com);
		}
		
		String mem_id=request.getParameter("z_cmem_id");
		System.out.println("Z_CommentDeleteController의 form()에서 넘어온 z_cmem_id=>"+mem_id);
		request.setAttribute("mem_id", mem_id);
		int z_number=com.getZ_number();
		System.out.println("Z_CommentDeleteController submit의 z_cnumber=>"+com.getZ_cnumber());
		
		//일단 대댓글이 딸린 댓글인지 알아봐야 함
		int lower=zCommentDao.lowerComment(com);
		System.out.println("대댓글 딸림 여부(lower)=>"+lower);
		
		int delete;
		if (lower > 0) { //대댓글이 딸린 댓글
			delete=zCommentDao.upDeleteComment(com.getZ_cnumber());
			System.out.println("lower>0");
		}else {
			delete=zCommentDao.deleteComment(com.getZ_cnumber());
			System.out.println("lower=0");
		}
		System.out.println("댓글 삭제 성공 여부(delete)=>"+delete);
		
		if (delete > 0) {
			int pointdown=zCommentDao.pointdownC(mem_id);
			System.out.println("댓글 삭제시 포인트 회수 성공 여부(pointdown)=>"+pointdown);
			int point=zBoardDao.getPoint(mem_id);
			
			if (pointdown > 0) {
				System.out.println("등급 변동 전 현재 등급=>"+zBoardDao.getGrade(mem_id));
				int gradeup=0;
				if (point < 100) {
					gradeup=zBoardDao.changeGrade1(mem_id);
				} else if (point >= 100 && point < 500) {
					gradeup=zBoardDao.changeGrade2(mem_id);
				} else if (point >= 500 && point < 2000) {
					gradeup=zBoardDao.changeGrade3(mem_id);
				} else if (point >= 2000 && point < 10000) {
					gradeup=zBoardDao.changeGrade4(mem_id);
				} else if (point >= 10000) {
					gradeup=zBoardDao.changeGrade5(mem_id);
				}
				System.out.println("댓글 삭제 후 등급 변동 여부(gradeup)=>"+gradeup);
				System.out.println("등급 변동 후 현재 등급=>"+zBoardDao.getGrade(mem_id));
			}
			MemberDTO mcom=memberDao.getNPG(mem_id);
			int log_grade=mcom.getLog_grade();
			System.out.println("log_grade=>"+log_grade);
			
			MemberDTO mcom2=memberDao.selectPG(mem_id);
			memberDao.syncPG(mcom2);
			
			
			HttpSession session=request.getSession();
			session.setAttribute("mem_grade", log_grade);
		}
		
		return "redirect:/Z_Content.do?z_number="+z_number;
	}
}

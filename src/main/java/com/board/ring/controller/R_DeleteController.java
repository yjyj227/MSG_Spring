package com.board.ring.controller;

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

import com.board.ring.dao.RBoardDao;
import com.board.ring.domain.RBoardDTO;
import com.board.ring.util.R_FileUtil;
import com.board.validator.BoardDeleteValidator;
import com.member.dao.MemberDao;
import com.member.domain.MemberDTO;

@Component
@Controller
public class R_DeleteController {

	private Logger log=Logger.getLogger(this.getClass());
	
	@Autowired
	private RBoardDao rBoardDao;
	@Autowired
	private MemberDao memberDao;
	
	//1. 글삭제 폼으로 이동
	@RequestMapping(value="/R_Delete.do", method=RequestMethod.GET)
	public String form(HttpServletRequest request) {
		String content_mem_id=request.getParameter("content_mem_id");
		System.out.println("R_Content에서 넘어온 content_mem_id=>"+content_mem_id);
		request.setAttribute("content_mem_id", content_mem_id);
		return "/board/ring/R_Delete";
	}
	
	@ModelAttribute("command")
	public RBoardDTO forBacking() {
		System.out.println("초기화... 왜 하는지 모르겠음");
		System.out.println("forBacking()에서 만들어진 new RBoardDTO()=>"+new RBoardDTO());
		return new RBoardDTO();
	}
	
	@RequestMapping(value="/R_Delete.do")
	public String submit1(@ModelAttribute("command") RBoardDTO com,
						 BindingResult result,
						 HttpServletRequest request) {
		
		if (log.isDebugEnabled()) {
			System.out.println("/R_Delete.do 요청중");
			log.debug("RBoardCommand : "+com);
		}
		
		//유효성검사
		
		
		RBoardDTO rBoard=null;
		rBoard=rBoardDao.getBoard(com.getR_number());
		//rBoardDao.delete(com.getR_number());
		
		request.setAttribute("r_number", com.getR_number());
		
		return "redirect:/R_Delete.do";
	}
	
	@RequestMapping(value="/R_DeleteProc.do", method=RequestMethod.GET)
	public String submit2(@ModelAttribute("command") RBoardDTO com,
						 BindingResult result,
						 HttpServletRequest request) {
		
		if (log.isDebugEnabled()) {
			System.out.println("/R_DeleteProc.do 요청중");
			log.debug("RBoardDTO : "+com);
		}
		
		//String mem_id=com.getMem_id();
		//String r_nickname=com.getR_nickname();
		//int r_number=com.getR_number();
		//System.out.println("R_DeleteController submit2의 mem_id=>"+mem_id+", r_nickname=>"+r_nickname+", r_number=>"+r_number);

		String content_mem_id=request.getParameter("content_mem_id");
		System.out.println("R_DeleteController의 form()에서 넘어온 content_mem_id=>"+content_mem_id);
		request.setAttribute("content_mem_id", content_mem_id);
		
		RBoardDTO hboard=null;
		hboard=rBoardDao.getBoard(com.getR_number());
		
		com.setR_title(request.getParameter("r_title"));
		System.out.println("r_title=>"+com.getR_title());
		
		System.out.println("R_DeleteController submit2의 r_number=>"+com.getR_number());
		int delete=rBoardDao.delete(com.getR_number());
		System.out.println("글 삭제 성공 여부(delete)=>"+delete);
		if (hboard.getR_filename() != null) {
			R_FileUtil.removeFile(hboard.getR_filename());
		}
		int delete2=rBoardDao.deleteComment(com.getR_number());
		System.out.println("글 삭제 후 댓글 삭제 성공 여부(delete2)=>"+delete2);
		int delete3=rBoardDao.deleteScrap(com);
		System.out.println("글 삭제 후 스크랩 삭제 성공 여부(delete3)=>"+delete3);
		
		if (delete > 0) {
			
			int pointdown=rBoardDao.pointdownA(content_mem_id);
			System.out.println("글 삭제시 포인트 회수 성공 여부(pointdown)=>"+pointdown);
			int point=rBoardDao.getPoint(content_mem_id);
			
			if (pointdown > 0) {
				System.out.println("등급 변동 전 현재 등급=>"+rBoardDao.getGrade(content_mem_id));
				int gradeup=0;
				if (point < 100) {
					gradeup=rBoardDao.changeGrade1(content_mem_id);
				} else if (point >= 100 && point < 500) {
					gradeup=rBoardDao.changeGrade2(content_mem_id);
				} else if (point >= 500 && point < 2000) {
					gradeup=rBoardDao.changeGrade3(content_mem_id);
				} else if (point >= 2000 && point < 10000) {
					gradeup=rBoardDao.changeGrade4(content_mem_id);
				} else if (point >= 10000) {
					gradeup=rBoardDao.changeGrade5(content_mem_id);
				}
				System.out.println("글 삭제 후 등급 변동 여부(gradeup)=>"+gradeup);
				System.out.println("등급 변동 후 현재 등급=>"+rBoardDao.getGrade(content_mem_id));
			}
			MemberDTO mcom=memberDao.getNPG(content_mem_id);
			int log_grade=mcom.getLog_grade();
			System.out.println("log_grade=>"+log_grade);
			
			MemberDTO mcom2=memberDao.selectPG(content_mem_id);
			memberDao.syncPG(mcom2);
			
			HttpSession session=request.getSession();
			session.setAttribute("mem_grade", log_grade);
		}
		
		
		return "redirect:/R_List.do";
	}
	
	
	
	
	
}

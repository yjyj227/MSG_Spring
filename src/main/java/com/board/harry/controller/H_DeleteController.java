package com.board.harry.controller;

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

import com.board.harry.dao.HBoardDao;
import com.board.harry.domain.HBoardDTO;
import com.board.harry.util.H_FileUtil;
import com.board.validator.BoardDeleteValidator;
import com.member.dao.MemberDao;
import com.member.domain.MemberDTO;

@Component
@Controller
public class H_DeleteController {

	private Logger log=Logger.getLogger(this.getClass());
	
	@Autowired
	private HBoardDao hBoardDao;
	@Autowired
	private MemberDao memberDao;
	
	//1. 글삭제 폼으로 이동
	@RequestMapping(value="/H_Delete.do", method=RequestMethod.GET)
	public String form(HttpServletRequest request) {
		String content_mem_id=request.getParameter("content_mem_id");
		System.out.println("H_Content에서 넘어온 content_mem_id=>"+content_mem_id);
		request.setAttribute("content_mem_id", content_mem_id);
		return "/board/harry/H_Delete";
	}
	
	@ModelAttribute("command")
	public HBoardDTO forBacking() {
		System.out.println("초기화... 왜 하는지 모르겠음");
		System.out.println("forBacking()에서 만들어진 new HBoardDTO()=>"+new HBoardDTO());
		return new HBoardDTO();
	}
	
	@RequestMapping(value="/H_Delete.do")
	public String submit1(@ModelAttribute("command") HBoardDTO com,
						 BindingResult result,
						 HttpServletRequest request) {
		
		if (log.isDebugEnabled()) {
			System.out.println("/H_Delete.do 요청중");
			log.debug("HBoardCommand : "+com);
		}
		
		//유효성검사
		
		
		HBoardDTO hBoard=null;
		hBoard=hBoardDao.getBoard(com.getH_number());
		//hBoardDao.delete(com.getH_number());
		
		request.setAttribute("h_number", com.getH_number());
		
		return "redirect:/H_Delete.do";
	}
	
	@RequestMapping(value="/H_DeleteProc.do", method=RequestMethod.GET)
	public String submit2(@ModelAttribute("command") HBoardDTO com,
						 BindingResult result,
						 HttpServletRequest request) {
		
		if (log.isDebugEnabled()) {
			System.out.println("/H_DeleteProc.do 요청중");
			log.debug("HBoardDTO : "+com);
		}
		
		//String mem_id=com.getMem_id();
		//String h_nickname=com.getH_nickname();
		//int h_number=com.getH_number();
		//System.out.println("H_DeleteController submit2의 mem_id=>"+mem_id+", h_nickname=>"+h_nickname+", h_number=>"+h_number);

		String content_mem_id=request.getParameter("content_mem_id");
		System.out.println("H_DeleteController의 form()에서 넘어온 content_mem_id=>"+content_mem_id);
		request.setAttribute("content_mem_id", content_mem_id);
		
		HBoardDTO hboard=null;
		hboard=hBoardDao.getBoard(com.getH_number());
		
		com.setH_title(request.getParameter("h_title"));
		System.out.println("h_title=>"+com.getH_title());
		
		System.out.println("H_DeleteController submit2의 h_number=>"+com.getH_number());
		int delete=hBoardDao.delete(com.getH_number());
		System.out.println("글 삭제 성공 여부(delete)=>"+delete);
		if (hboard.getH_filename() != null) {
			H_FileUtil.removeFile(hboard.getH_filename());
		}
		int delete2=hBoardDao.deleteComment(com.getH_number());
		System.out.println("글 삭제 후 댓글 삭제 성공 여부(delete2)=>"+delete2);
		int delete3=hBoardDao.deleteScrap(com);
		System.out.println("글 삭제 후 스크랩 삭제 성공 여부(delete3)=>"+delete3);
		
		if (delete > 0) {
			int pointdown=hBoardDao.pointdownA(content_mem_id);
			System.out.println("글 삭제시 포인트 회수 성공 여부(pointdown)=>"+pointdown);
			int point=hBoardDao.getPoint(content_mem_id);
			
			if (pointdown > 0) {
				System.out.println("등급 변동 전 현재 등급=>"+hBoardDao.getGrade(content_mem_id));
				int gradeup=0;
				if (point < 100) {
					gradeup=hBoardDao.changeGrade1(content_mem_id);
				} else if (point >= 100 && point < 500) {
					gradeup=hBoardDao.changeGrade2(content_mem_id);
				} else if (point >= 500 && point < 2000) {
					gradeup=hBoardDao.changeGrade3(content_mem_id);
				} else if (point >= 2000 && point < 10000) {
					gradeup=hBoardDao.changeGrade4(content_mem_id);
				} else if (point >= 10000) {
					gradeup=hBoardDao.changeGrade5(content_mem_id);
				}
				System.out.println("글 삭제 후 등급 변동 여부(gradeup)=>"+gradeup);
				System.out.println("등급 변동 후 현재 등급=>"+hBoardDao.getGrade(content_mem_id));
			}
			MemberDTO mcom=memberDao.getNPG(content_mem_id);
			int log_grade=mcom.getLog_grade();
			System.out.println("log_grade=>"+log_grade);
			
			MemberDTO mcom2=memberDao.selectPG(content_mem_id);
			memberDao.syncPG(mcom2);
			
			HttpSession session=request.getSession();
			session.setAttribute("mem_grade", log_grade);
		}
		
		
		return "redirect:/H_List.do";
	}
	
	
	
	
	
}

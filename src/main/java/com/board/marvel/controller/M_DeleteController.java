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

import com.board.marvel.dao.MBoardDao;
import com.board.marvel.domain.MBoardDTO;
import com.board.marvel.util.M_FileUtil;
import com.board.validator.BoardDeleteValidator;
import com.member.dao.MemberDao;
import com.member.domain.MemberDTO;

@Component
@Controller
public class M_DeleteController {

	private Logger log=Logger.getLogger(this.getClass());
	
	@Autowired
	private MBoardDao mBoardDao;
	@Autowired
	private MemberDao memberDao;
	
	//1. 글삭제 폼으로 이동
	@RequestMapping(value="/M_Delete.do", method=RequestMethod.GET)
	public String form(HttpServletRequest request) {
		String content_mem_id=request.getParameter("content_mem_id");
		System.out.println("M_Content에서 넘어온 content_mem_id=>"+content_mem_id);
		request.setAttribute("content_mem_id", content_mem_id);
		return "/board/marvel/M_Delete";
	}
	
	@ModelAttribute("command")
	public MBoardDTO forBacking() {
		System.out.println("초기화... 왜 하는지 모르겠음");
		System.out.println("forBacking()에서 만들어진 new MBoardDTO()=>"+new MBoardDTO());
		return new MBoardDTO();
	}
	
	@RequestMapping(value="/M_Delete.do")
	public String submit1(@ModelAttribute("command") MBoardDTO com,
						 BindingResult result,
						 HttpServletRequest request) {
		
		if (log.isDebugEnabled()) {
			System.out.println("/M_Delete.do 요청중");
			log.debug("MBoardCommand : "+com);
		}
		
		//유효성검사
		
		
		MBoardDTO mBoard=null;
		mBoard=mBoardDao.getBoard(com.getM_number());
		//mBoardDao.delete(com.getM_number());
		
		request.setAttribute("m_number", com.getM_number());
		
		return "redirect:/M_Delete.do";
	}
	
	@RequestMapping(value="/M_DeleteProc.do", method=RequestMethod.GET)
	public String submit2(@ModelAttribute("command") MBoardDTO com,
						 BindingResult result,
						 HttpServletRequest request) {
		
		if (log.isDebugEnabled()) {
			System.out.println("/M_DeleteProc.do 요청중");
			log.debug("MBoardDTO : "+com);
		}
		
		//String mem_id=com.getMem_id();
		//String m_nickname=com.getM_nickname();
		//int m_number=com.getM_number();
		//System.out.println("M_DeleteController submit2의 mem_id=>"+mem_id+", m_nickname=>"+m_nickname+", m_number=>"+m_number);

		String content_mem_id=request.getParameter("content_mem_id");
		System.out.println("M_DeleteController의 form()에서 넘어온 content_mem_id=>"+content_mem_id);
		request.setAttribute("content_mem_id", content_mem_id);
		
		MBoardDTO hboard=null;
		hboard=mBoardDao.getBoard(com.getM_number());
		
		com.setM_title(request.getParameter("m_title"));
		System.out.println("m_title=>"+com.getM_title());
		
		System.out.println("M_DeleteController submit2의 m_number=>"+com.getM_number());
		int delete=mBoardDao.delete(com.getM_number());
		System.out.println("글 삭제 성공 여부(delete)=>"+delete);
		if (hboard.getM_filename() != null) {
			M_FileUtil.removeFile(hboard.getM_filename());
		}
		int delete2=mBoardDao.deleteComment(com.getM_number());
		System.out.println("글 삭제 후 댓글 삭제 성공 여부(delete2)=>"+delete2);
		int delete3=mBoardDao.deleteScrap(com);
		System.out.println("글 삭제 후 스크랩 삭제 성공 여부(delete3)=>"+delete3);
		
		if (delete > 0) {
			
			int pointdown=mBoardDao.pointdownA(content_mem_id);
			System.out.println("글 삭제시 포인트 회수 성공 여부(pointdown)=>"+pointdown);
			int point=mBoardDao.getPoint(content_mem_id);
			
			if (pointdown > 0) {
				System.out.println("등급 변동 전 현재 등급=>"+mBoardDao.getGrade(content_mem_id));
				int gradeup=0;
				if (point < 100) {
					gradeup=mBoardDao.changeGrade1(content_mem_id);
				} else if (point >= 100 && point < 500) {
					gradeup=mBoardDao.changeGrade2(content_mem_id);
				} else if (point >= 500 && point < 2000) {
					gradeup=mBoardDao.changeGrade3(content_mem_id);
				} else if (point >= 2000 && point < 10000) {
					gradeup=mBoardDao.changeGrade4(content_mem_id);
				} else if (point >= 10000) {
					gradeup=mBoardDao.changeGrade5(content_mem_id);
				}
				System.out.println("글 삭제 후 등급 변동 여부(gradeup)=>"+gradeup);
				System.out.println("등급 변동 후 현재 등급=>"+mBoardDao.getGrade(content_mem_id));
			}
			MemberDTO mcom=memberDao.getNPG(content_mem_id);
			int log_grade=mcom.getLog_grade();
			System.out.println("log_grade=>"+log_grade);
			
			MemberDTO mcom2=memberDao.selectPG(content_mem_id);
			memberDao.syncPG(mcom2);
			
			HttpSession session=request.getSession();
			session.setAttribute("mem_grade", log_grade);
		}
		
		
		return "redirect:/M_List.do";
	}
	
	
	
	
	
}

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

import com.board.ghibli.dao.GBoardDao;
import com.board.ghibli.domain.GBoardDTO;
import com.board.ghibli.util.G_FileUtil;
import com.board.validator.BoardDeleteValidator;
import com.member.dao.MemberDao;
import com.member.domain.MemberDTO;

@Component
@Controller
public class G_DeleteController {

	private Logger log=Logger.getLogger(this.getClass());
	
	@Autowired
	private GBoardDao gBoardDao;
	@Autowired
	private MemberDao memberDao;
	
	//1. 글삭제 폼으로 이동
	@RequestMapping(value="/G_Delete.do", method=RequestMethod.GET)
	public String form(HttpServletRequest request) {
		String content_mem_id=request.getParameter("content_mem_id");
		System.out.println("G_Content에서 넘어온 content_mem_id=>"+content_mem_id);
		request.setAttribute("content_mem_id", content_mem_id);
		return "/board/ghibli/G_Delete";
	}
	
	@ModelAttribute("command")
	public GBoardDTO forBacking() {
		System.out.println("초기화... 왜 하는지 모르겠음");
		System.out.println("forBacking()에서 만들어진 new GBoardDTO()=>"+new GBoardDTO());
		return new GBoardDTO();
	}
	
	@RequestMapping(value="/G_Delete.do")
	public String submit1(@ModelAttribute("command") GBoardDTO com,
						 BindingResult result,
						 HttpServletRequest request) {
		
		if (log.isDebugEnabled()) {
			System.out.println("/G_Delete.do 요청중");
			log.debug("GBoardCommand : "+com);
		}
		
		//유효성검사
		
		
		GBoardDTO gBoard=null;
		gBoard=gBoardDao.getBoard(com.getG_number());
		//gBoardDao.delete(com.getG_number());
		
		request.setAttribute("g_number", com.getG_number());
		
		return "redirect:/G_Delete.do";
	}
	
	@RequestMapping(value="/G_DeleteProc.do", method=RequestMethod.GET)
	public String submit2(@ModelAttribute("command") GBoardDTO com,
						 BindingResult result,
						 HttpServletRequest request) {
		
		if (log.isDebugEnabled()) {
			System.out.println("/G_DeleteProc.do 요청중");
			log.debug("GBoardDTO : "+com);
		}
		
		//String mem_id=com.getMem_id();
		//String g_nickname=com.getG_nickname();
		//int g_number=com.getG_number();
		//System.out.println("G_DeleteController submit2의 mem_id=>"+mem_id+", g_nickname=>"+g_nickname+", g_number=>"+g_number);

		String content_mem_id=request.getParameter("content_mem_id");
		System.out.println("G_DeleteController의 form()에서 넘어온 content_mem_id=>"+content_mem_id);
		request.setAttribute("content_mem_id", content_mem_id);
		
		GBoardDTO hboard=null;
		hboard=gBoardDao.getBoard(com.getG_number());
		
		com.setG_title(request.getParameter("g_title"));
		System.out.println("g_title=>"+com.getG_title());
		
		System.out.println("G_DeleteController submit2의 g_number=>"+com.getG_number());
		int delete=gBoardDao.delete(com.getG_number());
		System.out.println("글 삭제 성공 여부(delete)=>"+delete);
		if (hboard.getG_filename() != null) {
			G_FileUtil.removeFile(hboard.getG_filename());
		}
		int delete2=gBoardDao.deleteComment(com.getG_number());
		System.out.println("글 삭제 후 댓글 삭제 성공 여부(delete2)=>"+delete2);
		int delete3=gBoardDao.deleteScrap(com);
		System.out.println("글 삭제 후 스크랩 삭제 성공 여부(delete3)=>"+delete3);
		
		if (delete > 0) {
			
			int pointdown=gBoardDao.pointdownA(content_mem_id);
			System.out.println("글 삭제시 포인트 회수 성공 여부(pointdown)=>"+pointdown);
			int point=gBoardDao.getPoint(content_mem_id);
			
			if (pointdown > 0) {
				System.out.println("등급 변동 전 현재 등급=>"+gBoardDao.getGrade(content_mem_id));
				int gradeup=0;
				if (point < 100) {
					gradeup=gBoardDao.changeGrade1(content_mem_id);
				} else if (point >= 100 && point < 500) {
					gradeup=gBoardDao.changeGrade2(content_mem_id);
				} else if (point >= 500 && point < 2000) {
					gradeup=gBoardDao.changeGrade3(content_mem_id);
				} else if (point >= 2000 && point < 10000) {
					gradeup=gBoardDao.changeGrade4(content_mem_id);
				} else if (point >= 10000) {
					gradeup=gBoardDao.changeGrade5(content_mem_id);
				}
				System.out.println("글 삭제 후 등급 변동 여부(gradeup)=>"+gradeup);
				System.out.println("등급 변동 후 현재 등급=>"+gBoardDao.getGrade(content_mem_id));
			}
			MemberDTO mcom=memberDao.getNPG(content_mem_id);
			int log_grade=mcom.getLog_grade();
			System.out.println("log_grade=>"+log_grade);
			
			MemberDTO mcom2=memberDao.selectPG(content_mem_id);
			memberDao.syncPG(mcom2);
			
			HttpSession session=request.getSession();
			session.setAttribute("mem_grade", log_grade);
		}
		
		
		return "redirect:/G_List.do";
	}
	
	
	
	
	
}

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

import com.board.zzang.dao.ZBoardDao;
import com.board.zzang.domain.ZBoardDTO;
import com.board.zzang.util.Z_FileUtil;
import com.board.validator.BoardDeleteValidator;
import com.member.dao.MemberDao;
import com.member.domain.MemberDTO;

@Component
@Controller
public class Z_DeleteController {

	private Logger log=Logger.getLogger(this.getClass());
	
	@Autowired
	private ZBoardDao zBoardDao;
	@Autowired
	private MemberDao memberDao;
	
	//1. 글삭제 폼으로 이동
	@RequestMapping(value="/Z_Delete.do", method=RequestMethod.GET)
	public String form(HttpServletRequest request) {
		String content_mem_id=request.getParameter("content_mem_id");
		System.out.println("Z_Content에서 넘어온 content_mem_id=>"+content_mem_id);
		request.setAttribute("content_mem_id", content_mem_id);
		return "/board/zzang/Z_Delete";
	}
	
	@ModelAttribute("command")
	public ZBoardDTO forBacking() {
		System.out.println("초기화... 왜 하는지 모르겠음");
		System.out.println("forBacking()에서 만들어진 new ZBoardDTO()=>"+new ZBoardDTO());
		return new ZBoardDTO();
	}
	
	@RequestMapping(value="/Z_Delete.do")
	public String submit1(@ModelAttribute("command") ZBoardDTO com,
						 BindingResult result,
						 HttpServletRequest request) {
		
		if (log.isDebugEnabled()) {
			System.out.println("/Z_Delete.do 요청중");
			log.debug("ZBoardCommand : "+com);
		}
		
		//유효성검사
		
		
		ZBoardDTO zBoard=null;
		zBoard=zBoardDao.getBoard(com.getZ_number());
		//zBoardDao.delete(com.getZ_number());
		
		request.setAttribute("z_number", com.getZ_number());
		
		return "redirect:/Z_Delete.do";
	}
	
	@RequestMapping(value="/Z_DeleteProc.do", method=RequestMethod.GET)
	public String submit2(@ModelAttribute("command") ZBoardDTO com,
						 BindingResult result,
						 HttpServletRequest request) {
		
		if (log.isDebugEnabled()) {
			System.out.println("/Z_DeleteProc.do 요청중");
			log.debug("ZBoardDTO : "+com);
		}
		
		//String mem_id=com.getMem_id();
		//String z_nickname=com.getZ_nickname();
		//int z_number=com.getZ_number();
		//System.out.println("Z_DeleteController submit2의 mem_id=>"+mem_id+", z_nickname=>"+z_nickname+", z_number=>"+z_number);

		String content_mem_id=request.getParameter("content_mem_id");
		System.out.println("Z_DeleteController의 form()에서 넘어온 content_mem_id=>"+content_mem_id);
		request.setAttribute("content_mem_id", content_mem_id);
		
		ZBoardDTO hboard=null;
		hboard=zBoardDao.getBoard(com.getZ_number());
		
		com.setZ_title(request.getParameter("z_title"));
		System.out.println("z_title=>"+com.getZ_title());
		
		System.out.println("Z_DeleteController submit2의 z_number=>"+com.getZ_number());
		int delete=zBoardDao.delete(com.getZ_number());
		System.out.println("글 삭제 성공 여부(delete)=>"+delete);
		if (hboard.getZ_filename() != null) {
			Z_FileUtil.removeFile(hboard.getZ_filename());
		}
		int delete2=zBoardDao.deleteComment(com.getZ_number());
		System.out.println("글 삭제 후 댓글 삭제 성공 여부(delete2)=>"+delete2);
		int delete3=zBoardDao.deleteScrap(com);
		System.out.println("글 삭제 후 스크랩 삭제 성공 여부(delete3)=>"+delete3);
		
		if (delete > 0) {
			
			int pointdown=zBoardDao.pointdownA(content_mem_id);
			System.out.println("글 삭제시 포인트 회수 성공 여부(pointdown)=>"+pointdown);
			int point=zBoardDao.getPoint(content_mem_id);
			
			if (pointdown > 0) {
				System.out.println("등급 변동 전 현재 등급=>"+zBoardDao.getGrade(content_mem_id));
				int gradeup=0;
				if (point < 100) {
					gradeup=zBoardDao.changeGrade1(content_mem_id);
				} else if (point >= 100 && point < 500) {
					gradeup=zBoardDao.changeGrade2(content_mem_id);
				} else if (point >= 500 && point < 2000) {
					gradeup=zBoardDao.changeGrade3(content_mem_id);
				} else if (point >= 2000 && point < 10000) {
					gradeup=zBoardDao.changeGrade4(content_mem_id);
				} else if (point >= 10000) {
					gradeup=zBoardDao.changeGrade5(content_mem_id);
				}
				System.out.println("글 삭제 후 등급 변동 여부(gradeup)=>"+gradeup);
				System.out.println("등급 변동 후 현재 등급=>"+zBoardDao.getGrade(content_mem_id));
			}
			MemberDTO mcom=memberDao.getNPG(content_mem_id);
			int log_grade=mcom.getLog_grade();
			System.out.println("log_grade=>"+log_grade);
			
			MemberDTO mcom2=memberDao.selectPG(content_mem_id);
			memberDao.syncPG(mcom2);
			
			HttpSession session=request.getSession();
			session.setAttribute("mem_grade", log_grade);
		}
		
		
		return "redirect:/Z_List.do";
	}
	
	
	
	
	
}

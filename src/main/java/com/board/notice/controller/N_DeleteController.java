package com.board.notice.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.board.notice.dao.NoticeDao;
import com.board.notice.domain.NoticeDTO;
import com.board.notice.util.N_FileUtil;
import com.member.dao.MemberDao;

@Component
@Controller
public class N_DeleteController {

private Logger log=Logger.getLogger(this.getClass());
	
	@Autowired
	private NoticeDao noticeDao;
	@Autowired
	private MemberDao memberDao;
	
	//1. 글삭제 폼으로 이동
	@RequestMapping(value="/N_Delete.do", method=RequestMethod.GET)
	public String form(HttpServletRequest request) {
		return "/board/notice/N_Delete";
	}
	
	@ModelAttribute("command")
	public NoticeDTO forBacking() {
		System.out.println("초기화... 왜 하는지 모르겠음");
		System.out.println("forBacking()에서 만들어진 new NoticeDTO()=>"+new NoticeDTO());
		return new NoticeDTO();
	}
	
	@RequestMapping(value="/N_Delete.do")
	public String submit1(@ModelAttribute("command") NoticeDTO com,
						 BindingResult result,
						 HttpServletRequest request) {
		
		if (log.isDebugEnabled()) {
			System.out.println("/N_Delete.do 요청중");
			log.debug("NoticeDTO : "+com);
		}
		
		//유효성검사
		
		
		NoticeDTO hBoard=null;
		hBoard=noticeDao.getBoard(com.getNotice_number());
		//noticeDao.delete(com.getNotice_number());
		
		request.setAttribute("notice_number", com.getNotice_number());
		
		return "redirect:/N_Delete.do";
	}
	
	@RequestMapping(value="/N_DeleteProc.do", method=RequestMethod.GET)
	public String submit2(@ModelAttribute("command") NoticeDTO com,
						 BindingResult result,
						 HttpServletRequest request) {
		
		if (log.isDebugEnabled()) {
			System.out.println("/N_DeleteProc.do 요청중");
			log.debug("NoticeDTO : "+com);
		}
		
		NoticeDTO notice=null;
		notice=noticeDao.getBoard(com.getNotice_number());
		
		System.out.println("N_DeleteController submit2의 notice_number=>"+com.getNotice_number());
		int delete=noticeDao.delete(com.getNotice_number());
		System.out.println("글 삭제 성공 여부(delete)=>"+delete);
		if (notice.getNot_filename() != null) {
			N_FileUtil.removeFile(notice.getNot_filename());
		}
		
		return "redirect:/N_List.do";
	}
	
	
	
	
	
}

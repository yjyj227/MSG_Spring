package com.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.board.harry.dao.HBoardDao;
import com.member.dao.MemberDao;
import com.member.domain.MemberDTO;

@Component
@Controller
public class MyPageController {

	private Logger log=Logger.getLogger(this.getClass());
	
	@Autowired
	private HBoardDao hBoardDao;
	@Autowired
	private MemberDao memberDao;
	
	@ModelAttribute("command")
	public MemberDTO forBacking() {
		System.out.println("초기화... 왜 하는지 모르겠음");
		System.out.println("forBacking()에서 만들어진 new MemberDTO()=>"+new MemberDTO());
		return new MemberDTO();
	}
	
	@RequestMapping("/MyPage.do")
	public ModelAndView process(@ModelAttribute("command") MemberDTO com,
								HttpServletRequest request) {
		
		HttpSession session=request.getSession();
		String mem_id=(String)session.getAttribute("idKey");
		//String mem_id=request.getParameter("mem_id");
		System.out.println("MyPageController의 mem_id=>"+mem_id);
		
		/*
		MemberDTO mem=memberDao.selectPG(mem_id);
		int point=mem.getLog_point();
		int grade=mem.getLog_grade();
		System.out.println("MyPageController의 point=>"+point+", grade=>"+grade);
		memberDao.syncPG(mem);
		*/
		ModelAndView mav=new ModelAndView("/member/MyPage");
		return mav;
	}
}

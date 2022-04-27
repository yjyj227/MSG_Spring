package com.member.controller;

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

import com.member.dao.MemberDao;
import com.member.domain.MemberDTO;

@Component
@Controller
public class MemberUpdateController {

	private Logger log=Logger.getLogger(this.getClass());
	
	@Autowired
	private MemberDao memberDao;
	
	//1. 회원정보 조회 및 수정 페이지로 이동
	@RequestMapping(value="/MemberUpdate.do", method=RequestMethod.GET)
	public ModelAndView form(HttpServletRequest request) {
		System.out.println("MemberUpdateController의 form() 호출됨");
		HttpSession session=request.getSession();
		String mem_id=(String)session.getAttribute("idKey");
		MemberDTO memberDTO=memberDao.getMember(mem_id);
		System.out.println("mem_id=>"+mem_id+", memberDTO=>"+memberDTO);
		return new ModelAndView("/member/MemberUpdate", "mem", memberDTO);
	}
	
	@ModelAttribute("memCommand")
	public MemberDTO forBacking() {
		System.out.println("forBacking() 호출됨");
		return new MemberDTO();
	}
	
	//2. 정보 수정
	@RequestMapping(value="/MemberUpdate.do", method=RequestMethod.POST)
	public String submit(@ModelAttribute("memCommand") MemberDTO mem,
						 BindingResult result,
						 HttpServletRequest request) {
		
		if (log.isDebugEnabled()) {
			System.out.println("/MemberUpdate.do 요청중");
			log.debug("MemberDTO : "+mem);
		}
		
		//유효성검사
		
		MemberDTO member=null;
		member=memberDao.getMember(mem.getMem_id());
		
		int check1=memberDao.memberUpdate(mem);
		int check2=memberDao.loginUpdate(mem);
		System.out.println("회원정보수정여부=>"+check1+", "+check2);
		//String mem_id=request.getParameter("mem_id");
		HttpSession session=request.getSession();
		String mem_id=(String)session.getAttribute("idKey");
		System.out.println("MemberUpdateController의 mem_id=>"+mem_id);
		
		return "redirect:/MemberUpdate.do?mem_id="+mem_id;
	}
	
	
	
	
	
	
	
	
	
	
	
}

package com.member.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.member.dao.MemberDao;
import com.member.domain.MemberDTO;

@Component
@Controller
public class QuitController {

	private Logger log=Logger.getLogger(this.getClass());
	
	@Autowired
	private MemberDao memberDao;
	
	//1. 탈퇴 폼으로 이동
	@RequestMapping(value="/Quit.do", method=RequestMethod.GET)
	public String form(HttpServletRequest request) {
		String mem_id=request.getParameter("mem_id");
		System.out.println("Quit.do로 넘어온 mem_id=>"+mem_id);
		request.setAttribute("mem_id", mem_id);
		return "/member/Quit";
	}
	
	@ModelAttribute("command")
	public MemberDTO forBacking() {
		System.out.println("forBacking()에서 만들어진 new MemberDTO()=>"+new MemberDTO());
		return new MemberDTO();
	}
	
	@RequestMapping(value="/Quit.do", method=RequestMethod.POST)
	public String submit(HttpServletRequest request) {
		
		if (log.isDebugEnabled()) {
			System.out.println("/Quit.do 요청중");
		}
		
		int x=-1;
		int beforeQuit=0;
		int delete=0;
		
		String mem_id=request.getParameter("mem_id");
		String passwd=request.getParameter("passwd");
		System.out.println("QuitController submit()의 mem_id=>"+mem_id+", passwd=>"+passwd);
		request.setAttribute("mem_id", mem_id);
		request.setAttribute("passwd", passwd);
		
		int check=memberDao.matchPw(mem_id, passwd);
		System.out.println("비밀번호 일치여부(check)=>"+check);
		
		if (check > 0) {
			beforeQuit=memberDao.beforeQuit(mem_id);
			System.out.println("beforeQuit=>"+beforeQuit);
			if (beforeQuit > 0) {
				delete=memberDao.quit(mem_id);
				System.out.println("회원 탈퇴 성공여부(delete)=>"+delete);
			}
		}
		
		request.setAttribute("delete", delete);
		
		return "redirect:/QuitProc.do?delete="+delete;
	}
	
	@RequestMapping("/QuitProc.do")
	public ModelAndView submit2(HttpServletRequest request) {
		
		String mem_id=request.getParameter("mem_id");
		int delete=Integer.parseInt(request.getParameter("delete"));
		System.out.println("submit2의 delete값=>"+delete);
		
		ModelAndView mav=new ModelAndView("/member/QuitProc");
		mav.addObject("delete", delete);
		mav.addObject("mem_id", mem_id);
		
		return mav;
	}
	
}

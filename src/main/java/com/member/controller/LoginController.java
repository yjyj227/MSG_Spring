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

import com.board.validator.BoardValidator;
import com.member.dao.MemberDao;
import com.member.domain.MemberDTO;

@Component
@Controller
public class LoginController {

	private Logger log=Logger.getLogger(this.getClass());
	
	@Autowired
	private MemberDao memberDao;
	
	@RequestMapping(value="/Login.do", method=RequestMethod.GET)
	public String form() {
		System.out.println("로그인 폼으로 가기");
		return "/member/Login";
	}
	
	@ModelAttribute("loginCommand")
	public MemberDTO forBacking() {
		System.out.println("forBacking() 호출됨");
		return new MemberDTO();
	}
	
	@RequestMapping(value="/Login.do", method=RequestMethod.POST)
	public String submit(@ModelAttribute("loginCommand") MemberDTO com,
						 BindingResult result,
						 HttpServletRequest request) {
		
		if (log.isDebugEnabled()) {
			System.out.println("/Login.do 요청중");
			log.debug("MemberDTO : "+com);
			log.debug("mem_id=>"+com.getMem_id());
			log.debug("log_passwd=>"+com.getLog_passwd());
		}
		/*
		 * new BoardValidator().validate(com, result); if (result.hasErrors()) { return
		 * form(); }
		 */
		
		String mem_id=com.getMem_id();
		String log_passwd=com.getLog_passwd();
		System.out.println("mem_id=>"+mem_id+", log_passwd=>"+log_passwd);
		
		boolean check=memberDao.loginCheck(com);
		System.out.println("check=>"+check);
		
		if (check) {
			
			MemberDTO mcom=memberDao.getNPG(mem_id);
			
			String log_nickname=mcom.getLog_nickname();
			int log_point=mcom.getLog_point();
			int log_grade=mcom.getLog_grade();
			System.out.println("log_nickname=>"+log_nickname+", log_point=>"+log_point+", log_grade=>"+log_grade);
			
			HttpSession session=request.getSession();
			
			session.setAttribute("check", check);
			session.setAttribute("idKey", mem_id);
			session.setAttribute("pwKey", log_passwd);
			session.setAttribute("mem_nickname", log_nickname);
			session.setAttribute("mem_point", log_point);
			session.setAttribute("mem_grade", log_grade);
			
			/*
			System.out.println("check=>"+session.getAttribute("check"));
			System.out.println("idKey=>"+session.getAttribute("idKey"));
			System.out.println("pwKey=>"+session.getAttribute("pwKey"));
			System.out.println("mem_nickname=>"+session.getAttribute("mem_nickname"));
			System.out.println("mem_point=>"+session.getAttribute("mem_point"));
			System.out.println("mem_grade=>"+session.getAttribute("mem_grade"));
			*/
			
			return "redirect:/LogSuccess.do";
		}else {
			return "redirect:/LogError.do";
		}
		
	}
	
	@RequestMapping("/LogSuccess.do")
	public ModelAndView process1(HttpServletRequest request) {
		HttpSession session=request.getSession();
		/*
		System.out.println("=====================================");
		System.out.println("check=>"+session.getAttribute("check"));
		System.out.println("idKey=>"+session.getAttribute("idKey"));
		System.out.println("pwKey=>"+session.getAttribute("pwKey"));
		System.out.println("mem_nickname=>"+session.getAttribute("mem_nickname"));
		System.out.println("mem_point=>"+session.getAttribute("mem_point"));
		System.out.println("mem_grade=>"+session.getAttribute("mem_grade"));
		*/
		
		request.setAttribute("check", session.getAttribute("check"));
		request.setAttribute("idKey", session.getAttribute("idKey"));
		request.setAttribute("pwKey", session.getAttribute("pwKey"));
		request.setAttribute("mem_nickname", session.getAttribute("mem_nickname"));
		request.setAttribute("mem_point", session.getAttribute("mem_point"));
		request.setAttribute("mem_grade", session.getAttribute("mem_grade"));
		
		ModelAndView mav=new ModelAndView("/member/LogSuccess");
		return mav;
	}
	
	@RequestMapping("/LogError.do")
	public ModelAndView process2(HttpServletRequest request) {
		
		ModelAndView mav=new ModelAndView("/member/LogError");
		return mav;
	}
	
}

package com.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.member.dao.MemberDao;

@Component
@Controller
public class LogoutController {

	private Logger log=Logger.getLogger(this.getClass());
	
	@Autowired
	private MemberDao memberDao;
	
	@RequestMapping("/Logout.do")
	public ModelAndView process(HttpServletRequest request) {
		HttpSession session=request.getSession();
		session.invalidate();
		ModelAndView mav=new ModelAndView("/member/Logout");
		return mav;
	}
}

package com.admin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.admin.dao.AdminDao;
import com.admin.domain.AdminDTO;

@Component
@Controller
public class AdminLoginController {

	private Logger log=Logger.getLogger(this.getClass());
	
	@Autowired
	private AdminDao adminDao;
	
	@RequestMapping(value="/AdminLogin.do", method=RequestMethod.GET)
	public String form() {
		System.out.println("관리자 로그인 폼으로 가기");
		return "/admin/AdminLogin";
	}
	
	@ModelAttribute("adminLoginCommand")
	public AdminDTO forBacking() {
		System.out.println("forBacking()호출됨");
		return new AdminDTO();
	}
	
	@RequestMapping(value="/AdminLogin.do", method=RequestMethod.POST)
	public String submit(@ModelAttribute("adminLoginCommand") AdminDTO admin,
						 HttpServletRequest request) {
		
		if (log.isDebugEnabled()) {
			System.out.println("/AdminLogin.do 요청중");
			log.debug("AdminDTO : "+admin);
		}
		
		String admin_id=admin.getAdmin_id();
		String admin_passwd=admin.getAdmin_passwd();
		System.out.println("admin_id=>"+admin_id+", admin_passwd=>"+admin_passwd);
		
		boolean check=adminDao.adminLoginCheck(admin);
		System.out.println("check=>"+check);
		
		if (check) {
			String admin_nickname=adminDao.getAdminN(admin_id);
			System.out.println("admin_nickname=>"+admin_nickname);
			
			HttpSession session=request.getSession();
			
			session.setAttribute("check", check);
			session.setAttribute("idKey", admin_id);
			session.setAttribute("pwKey", admin_passwd);
			session.setAttribute("admin_nickname", admin_nickname);
			
			return "redirect:/LogSuccess.do";
		}else {
			return "redirect:/LogError.do";
		}
		
	}
	
}

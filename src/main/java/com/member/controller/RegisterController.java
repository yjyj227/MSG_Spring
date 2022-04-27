package com.member.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.member.dao.MemberDao;
import com.member.domain.MemberDTO;

@Component
@Controller
public class RegisterController {

	private Logger log=Logger.getLogger(this.getClass());
	
	@Autowired
	private MemberDao memberDao;
	
	//1. 가입폼으로 이동
	@RequestMapping(value="/Register.do", method=RequestMethod.GET)
	public String form() {
		System.out.println("RegisterController의 form() 호출됨");
		return "/member/Register";
	}
	
	//2. 에러메시지 출력
	@ModelAttribute("command")
	public MemberDTO forBacking() {
		System.out.println("RegisterController의 forBacking() 호출됨");
		return new MemberDTO();
	}
	
	//3-1. id 중복 체크
	@RequestMapping(value="/IdCheck.do")
	public ModelAndView processId(@RequestParam(value="mem_id") String mem_id,
								HttpServletRequest request) {
		if (log.isDebugEnabled()) {
			System.out.println("/Register.do 요청중");
			log.debug("mem_id : "+mem_id);
		}
		
		System.out.println(mem_id);
		int check1=memberDao.checkId(mem_id);
		request.setAttribute("check1", check1);
		
		ModelAndView mav=new ModelAndView("/member/IdCheck");
		return mav;
	}
	
	//3-2. 닉네임 중복 체크
	@RequestMapping(value="/NicknameCheck.do")
	public ModelAndView processNickname(@RequestParam(value="mem_nickname") String mem_nickname,
								HttpServletRequest request) {
		if (log.isDebugEnabled()) {
			System.out.println("/Register.do 요청중");
			log.debug("mem_nickname : "+mem_nickname);
		}
		
		System.out.println(mem_nickname);
		int check2=memberDao.checkNickname(mem_nickname);
		request.setAttribute("check2", check2);
		
		ModelAndView mav=new ModelAndView("/member/NicknameCheck");
		return mav;
	}
	
	//3-3. 입력
	@RequestMapping(value="/Register.do", method=RequestMethod.POST)
	public String submit(@ModelAttribute("command") MemberDTO mem,
						 BindingResult result,
						 HttpServletRequest request) {
		
		if (log.isDebugEnabled()) {
			System.out.println("/Register.do 요청중");
			log.debug("MemberDTO : "+mem);
		}
		
		
		int insert1=memberDao.memberInsert(mem);
		System.out.println("회원가입 성공 여부(insert1)=>"+insert1);
		int insert2=memberDao.loginInsert(mem);
		System.out.println("로그인 테이블 데이터 입력 성공 여부(insert2)=>"+insert2);
		request.setAttribute("insert1", insert1);
		request.setAttribute("insert2", insert2);
		
		return "redirect:/MemberInsert.do?insert1="+insert1+"&insert2="+insert2; //alert창 띄울 페이지로
	}
	
	//3-4. 가입 처리
	@RequestMapping(value="/MemberInsert.do")
	public ModelAndView processMemberInsert(@RequestParam(value="insert1") int insert1,
											@RequestParam(value="insert2") int insert2,
											HttpServletRequest request) {
		if (log.isDebugEnabled()) {
			System.out.println("/MemberInsert.do 요청중");
			log.debug("insert1 : "+insert1+", insert2 : "+insert2);
		}
		//int insert1=Integer.parseInt(request.getParameter("insert1"));
		System.out.println("insert1=>"+insert1+", insert2=>"+insert2);
		request.setAttribute("insert1", insert1);
		request.setAttribute("insert2", insert2);
		
		ModelAndView mav=new ModelAndView("/member/MemberInsert");
		return mav;
	}
	
	
	
	
	
	
	
	
	
	
}

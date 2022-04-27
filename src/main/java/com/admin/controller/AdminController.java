package com.admin.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

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

import com.admin.dao.AdminDao;
import com.member.dao.MemberDao;
import com.member.domain.MemberDTO;

@Component
@Controller
public class AdminController {

	private Logger log=Logger.getLogger(this.getClass());
	
	@Autowired
	private AdminDao adminDao;
	
	
	@RequestMapping("/AdminPage.do")
	public ModelAndView process(
			@RequestParam(value="pageNum", defaultValue="1") String currentPage,
			@RequestParam(value="search", defaultValue="") String search,
			@RequestParam(value="searchtext", defaultValue="") String searchtext,
			HttpServletRequest request) {
		
		if (log.isDebugEnabled()) {
			System.out.println("/AdminPage.do(MemberList.do) 요청중");
			log.debug("currentPage:"+currentPage);
			log.debug("search=>"+search);
			log.debug("searchtext=>"+searchtext);
		}
		
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("search", search);
		map.put("searchtext", searchtext);
		
		int count=adminDao.getMemberCount(map);
		System.out.println("회원 수(count)=>"+count);
		
		Hashtable<String, Object> pgList=adminDao.pageList(currentPage, count);
		
		map.put("start", pgList.get("startRow"));
		map.put("end", pgList.get("endRow"));
		
		List<MemberDTO> memberList=null;
		
		if (count > 0) {
			memberList=adminDao.getMembers(map);
		}else {
			memberList=Collections.EMPTY_LIST;
		}
		
		ModelAndView mav=new ModelAndView("/admin/MemberList");
		mav.addObject("count", count);
		mav.addObject("memberList", memberList);
		mav.addObject("pgList", pgList);
		
		return mav;
	}
	
	
	
	//-----------------------------------------------------------
	
	@Autowired
	private MemberDao memberDao;
	
	
	
	//회원정보 조회 및 수정 페이지로 이동
	@RequestMapping(value="/MemberModify.do", method=RequestMethod.GET)
	public ModelAndView form(@RequestParam("mem_id") String mem_id) {
		System.out.println("AdminController의 MemberModify~ form() 호출됨");
		System.out.println("넘어온 mem_id=>"+mem_id);
		MemberDTO mem=adminDao.getMember_admin(mem_id);
		System.out.println("mem_id=>"+mem_id+", memberDTO=>"+mem);
		ModelAndView mav=new ModelAndView("/admin/MemberModify");
		mav.addObject("mem", mem);
		return mav;
	}
	
	@ModelAttribute("memCommand")
	public MemberDTO forBacking() {
		System.out.println("forBacking() 호출됨");
		return new MemberDTO();
	}
	
	//정보 수정
	
	@RequestMapping(value="/MemberModify.do", method=RequestMethod.POST)
	public String submit(@ModelAttribute("memCommand") MemberDTO mem,
						 BindingResult result,
						 HttpServletRequest request) {
		
		if (log.isDebugEnabled()) {
			System.out.println("/MemberModify.do 요청중");
			log.debug("MemberDTO : "+mem);
		}
		
		String mem_id=mem.getMem_id();
		System.out.println("MemberModifyController의 mem_id=>"+mem_id);
		int point=mem.getMem_point();
		int grade=mem.getMem_grade();
		System.out.println("point=>"+point+", grade=>"+grade);
		
		//MemberDTO member=null;
		//member=memberDao.getMember(mem_id);
		
		int check1=adminDao.memberModify(mem);
		int check2=adminDao.loginModify(mem);
		System.out.println("회원정보수정여부=>"+check1+", "+check2);
		System.out.println("#####");
		System.out.println(mem.getMem_point());
		System.out.println(mem.getMem_grade());
		
		//return "redirect:/MemberModify.do?mem_id="+mem_id;
		return "redirect:/AdminPage.do";
	}
	
	
	//-----------------------------------------------------------------
	
	@RequestMapping(value="MemberDelete.do", method=RequestMethod.GET)
	public String deleteForm(HttpServletRequest request) {
		String member_id=request.getParameter("member_id");
		System.out.println("MemberDelete.do로 넘어온 member_id=>"+member_id);
		request.setAttribute("member_id", member_id);
		return "/admin/MemberDelete";
	}
	
	@ModelAttribute("memberDeleteCommand")
	public MemberDTO forBacking_memberdelete() {
		System.out.println("forBacking_memberdelete()에서 만들어진 new MemberDTO()=>"+new MemberDTO());
		return new MemberDTO();
	}
	
	@RequestMapping(value="/MemberDelete.do", method=RequestMethod.POST)
	public String deleteSubmit(HttpServletRequest request) {
		
		if (log.isDebugEnabled()) {
			System.out.println("/MemberDelete.do 요청중");
		}
		
		int x=-1;
		int beforeQuit=0;
		int delete=0;
		
		String member_id=request.getParameter("member_id");
		String passwd=request.getParameter("passwd");
		System.out.println("AdminController deleteSubmit()의 member_id=>"+member_id+", passwd=>"+passwd);
		request.setAttribute("member_id", member_id);
		request.setAttribute("passwd", passwd);
		
		int check=adminDao.matchPw_admin(member_id, passwd);
		System.out.println("비밀번호 일치여부(check)=>"+check);
		
		if (check > 0) {
			beforeQuit=adminDao.beforeQuit_admin(member_id);
			System.out.println("beforeQuit=>"+beforeQuit);
			if (beforeQuit > 0) {
				delete=adminDao.memberDelete(member_id);
				System.out.println("회원 삭제 성공여부(delete)=>"+delete);
			}
		}
		
		request.setAttribute("delete", delete);
		
		return "redirect:/MemberDeleteProc.do?delete="+delete;
	}
	
	@RequestMapping("/MemberDeleteProc.do")
	public ModelAndView deleteSubmit2(HttpServletRequest request) {
		
		String member_id=request.getParameter("member_id");
		int delete=Integer.parseInt(request.getParameter("delete"));
		System.out.println("deleteSubmit2의 delete값=>"+delete);
		
		ModelAndView mav=new ModelAndView("/admin/MemberDeleteProc");
		mav.addObject("delete", delete);
		mav.addObject("member_id", member_id);
		
		return mav;
	}
	
	
	
	
	
	
	
	
}

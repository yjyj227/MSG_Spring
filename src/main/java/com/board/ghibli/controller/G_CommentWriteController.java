package com.board.ghibli.controller;

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

import com.board.ghibli.dao.GBoardDao;
import com.board.ghibli.dao.GCommentDao;
import com.board.ghibli.domain.GCommentDTO;
import com.member.dao.MemberDao;
import com.member.domain.MemberDTO;

@Component
@Controller
public class G_CommentWriteController {

	private Logger log=Logger.getLogger(this.getClass());
	
	@Autowired
	private GCommentDao gCommentDao;
	@Autowired
	private GBoardDao gBoardDao;
	@Autowired
	private MemberDao memberDao;
	
	@RequestMapping(value="/G_CommentWrite.do", method=RequestMethod.GET)
	public String form(HttpServletRequest request) {
		System.out.println("form() 호출됨");
		request.setAttribute("g_number", request.getParameter("g_number"));
		System.out.println("request.getParameter('g_number')=>"+request.getParameter("g_number"));
		return "/board/ghibli/G_CommentWrite";
	}
	
	//2. 에러메시지 출력
	@ModelAttribute("command")
	public GCommentDTO forBacking() {
		System.out.println("forBacking() 호출됨");
		return new GCommentDTO();
	}
	
	//3. 입력
	@RequestMapping(value="/G_CommentWrite.do", method=RequestMethod.POST)
	public ModelAndView submit(@ModelAttribute("command") GCommentDTO com,
						 BindingResult result,
						 HttpServletRequest request) {
		
		if (log.isDebugEnabled()) {
			System.out.println("/G_CommentWrite.do 요청중");
			log.debug("GCommentDTO : "+com);
		}
		
		int g_number=com.getG_number();
		System.out.println("g_number=>"+g_number);
		
		
		
		try {
			int g_cnumber = com.getG_cnumber();
			int g_cref = com.getG_cref();
			int g_cre_step = com.getG_cre_step();
			int g_cre_level = com.getG_cre_level();
			System.out.println("g_cnumber=>"+g_cnumber+", g_cref=>" + g_cref + ", g_cre_step=>" + g_cre_step + ", g_cre_level=>" + g_cre_level);
			
			int number=gCommentDao.getNewCommentNum()+1;
			System.out.println("number=>"+number);
			com.setG_cnumber(number);
			
			if  (g_cnumber != 0) { //대댓글
				System.out.println("update 이전 g_cre_step=>"+g_cre_step);
				g_cre_step=gCommentDao.getSuperStep(g_cref)+1;
				System.out.println("g_cre_step+1=>"+g_cre_step);
				g_cre_level=g_cre_level+1;
				System.out.println("update 이후 g_cre_step=>"+g_cre_step+", g_cre_level=>"+g_cre_level);
			}else { //신규글
				g_cref=number;
				g_cre_step=0;
				g_cre_level=0;
			}
			
			com.setG_cref(g_cref);
			com.setG_cre_step(g_cre_step);
			com.setG_cre_level(g_cre_level);
			int insert=gCommentDao.insertComment(com);
			System.out.println("댓글 작성 성공 여부(insert)=>"+insert);
			if (insert > 0) {
				String mem_id=com.getMem_id();
				System.out.println("G_CommentWriteController의 mem_id=>"+mem_id);
				int pointup=gCommentDao.pointupC(mem_id);
				System.out.println("댓글 작성시 포인트 적립 성공 여부(pointup)=>"+pointup);
				int point=gBoardDao.getPoint(mem_id);
				
				if (pointup > 0) {
					System.out.println("등급 변동 전 현재 등급=>"+gBoardDao.getGrade(mem_id));
					int gradeup=0;
					if (point < 100) {
						gradeup=gBoardDao.changeGrade1(mem_id);
					} else if (point >= 100 && point < 500) {
						gradeup=gBoardDao.changeGrade2(mem_id);
					} else if (point >= 500 && point < 2000) {
						gradeup=gBoardDao.changeGrade3(mem_id);
					} else if (point >= 2000 && point < 10000) {
						gradeup=gBoardDao.changeGrade4(mem_id);
					} else if (point >= 10000) {
						gradeup=gBoardDao.changeGrade5(mem_id);
					}
					System.out.println("댓글 작성 후 등급 변동 여부(gradeup)=>"+gradeup);
					System.out.println("등급 변동 후 현재 등급=>"+gBoardDao.getGrade(mem_id));
				}
				MemberDTO mcom=memberDao.getNPG(mem_id);
				int log_grade=mcom.getLog_grade();
				System.out.println("log_grade=>"+log_grade);
				
				MemberDTO mcom2=memberDao.selectPG(mem_id);
				memberDao.syncPG(mcom2);
				
				HttpSession session=request.getSession();
				session.setAttribute("mem_grade", log_grade);
			}
			
			
			
			
		}catch (Exception e2) {
			e2.printStackTrace();
		}
	
		
		//return "redirect:/G_CommentWrite.do?g_number="+g_number;
		ModelAndView mav=new ModelAndView("/board/ghibli/G_CommentWrite");
		mav.addObject("g_number", g_number);
		return mav;
	}
	
}

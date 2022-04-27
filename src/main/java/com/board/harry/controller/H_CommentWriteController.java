package com.board.harry.controller;

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

import com.board.harry.dao.HBoardDao;
import com.board.harry.dao.HCommentDao;
import com.board.harry.domain.HCommentDTO;
import com.member.dao.MemberDao;
import com.member.domain.MemberDTO;

@Component
@Controller
public class H_CommentWriteController {

	private Logger log=Logger.getLogger(this.getClass());
	
	@Autowired
	private HCommentDao hCommentDao;
	@Autowired
	private HBoardDao hBoardDao;
	@Autowired
	private MemberDao memberDao;
	
	@RequestMapping(value="/H_CommentWrite.do", method=RequestMethod.GET)
	public String form(HttpServletRequest request) {
		System.out.println("form() 호출됨");
		request.setAttribute("h_number", request.getParameter("h_number"));
		System.out.println("request.getParameter('h_number')=>"+request.getParameter("h_number"));
		return "/board/harry/H_CommentWrite";
	}
	
	//2. 에러메시지 출력
	@ModelAttribute("command")
	public HCommentDTO forBacking() {
		System.out.println("forBacking() 호출됨");
		return new HCommentDTO();
	}
	
	//3. 입력
	@RequestMapping(value="/H_CommentWrite.do", method=RequestMethod.POST)
	public ModelAndView submit(@ModelAttribute("command") HCommentDTO com,
						 BindingResult result,
						 HttpServletRequest request) {
		
		if (log.isDebugEnabled()) {
			System.out.println("/H_CommentWrite.do 요청중");
			log.debug("HCommentDTO : "+com);
		}
		
		int h_number=com.getH_number();
		System.out.println("h_number=>"+h_number);
		
		
		
		try {
			int h_cnumber = com.getH_cnumber();
			int h_cref = com.getH_cref();
			int h_cre_step = com.getH_cre_step();
			int h_cre_level = com.getH_cre_level();
			System.out.println("h_cnumber=>"+h_cnumber+", h_cref=>" + h_cref + ", h_cre_step=>" + h_cre_step + ", h_cre_level=>" + h_cre_level);
			
			int number=hCommentDao.getNewCommentNum()+1;
			System.out.println("number=>"+number);
			com.setH_cnumber(number);
			
			if  (h_cnumber != 0) { //대댓글
				System.out.println("update 이전 h_cre_step=>"+h_cre_step);
				h_cre_step=hCommentDao.getSuperStep(h_cref)+1;
				System.out.println("h_cre_step+1=>"+h_cre_step);
				h_cre_level=h_cre_level+1;
				System.out.println("update 이후 h_cre_step=>"+h_cre_step+", h_cre_level=>"+h_cre_level);
			}else { //신규글
				h_cref=number;
				h_cre_step=0;
				h_cre_level=0;
			}
			
			com.setH_cref(h_cref);
			com.setH_cre_step(h_cre_step);
			com.setH_cre_level(h_cre_level);
			int insert=hCommentDao.insertComment(com);
			System.out.println("댓글 작성 성공 여부(insert)=>"+insert);
			if (insert > 0) {
				String mem_id=com.getMem_id();
				System.out.println("H_CommentWriteController의 mem_id=>"+mem_id);
				int pointup=hCommentDao.pointupC(mem_id);
				System.out.println("댓글 작성시 포인트 적립 성공 여부(pointup)=>"+pointup);
				int point=hBoardDao.getPoint(mem_id);
				
				if (pointup > 0) {
					System.out.println("등급 변동 전 현재 등급=>"+hBoardDao.getGrade(mem_id));
					int gradeup=0;
					if (point < 100) {
						gradeup=hBoardDao.changeGrade1(mem_id);
					} else if (point >= 100 && point < 500) {
						gradeup=hBoardDao.changeGrade2(mem_id);
					} else if (point >= 500 && point < 2000) {
						gradeup=hBoardDao.changeGrade3(mem_id);
					} else if (point >= 2000 && point < 10000) {
						gradeup=hBoardDao.changeGrade4(mem_id);
					} else if (point >= 10000) {
						gradeup=hBoardDao.changeGrade5(mem_id);
					}
					System.out.println("댓글 작성 후 등급 변동 여부(gradeup)=>"+gradeup);
					System.out.println("등급 변동 후 현재 등급=>"+hBoardDao.getGrade(mem_id));
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
	
		
		//return "redirect:/H_CommentWrite.do?h_number="+h_number;
		ModelAndView mav=new ModelAndView("/board/harry/H_CommentWrite");
		mav.addObject("h_number", h_number);
		return mav;
	}
	
}

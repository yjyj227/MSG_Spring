package com.board.ring.controller;

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

import com.board.ring.dao.RBoardDao;
import com.board.ring.dao.RCommentDao;
import com.board.ring.domain.RCommentDTO;
import com.member.dao.MemberDao;
import com.member.domain.MemberDTO;

@Component
@Controller
public class R_CommentWriteController {

	private Logger log=Logger.getLogger(this.getClass());
	
	@Autowired
	private RCommentDao rCommentDao;
	@Autowired
	private RBoardDao rBoardDao;
	@Autowired
	private MemberDao memberDao;
	
	@RequestMapping(value="/R_CommentWrite.do", method=RequestMethod.GET)
	public String form(HttpServletRequest request) {
		System.out.println("form() 호출됨");
		request.setAttribute("r_number", request.getParameter("r_number"));
		System.out.println("request.getParameter('r_number')=>"+request.getParameter("r_number"));
		return "/board/ring/R_CommentWrite";
	}
	
	//2. 에러메시지 출력
	@ModelAttribute("command")
	public RCommentDTO forBacking() {
		System.out.println("forBacking() 호출됨");
		return new RCommentDTO();
	}
	
	//3. 입력
	@RequestMapping(value="/R_CommentWrite.do", method=RequestMethod.POST)
	public ModelAndView submit(@ModelAttribute("command") RCommentDTO com,
						 BindingResult result,
						 HttpServletRequest request) {
		
		if (log.isDebugEnabled()) {
			System.out.println("/R_CommentWrite.do 요청중");
			log.debug("RCommentDTO : "+com);
		}
		
		int r_number=com.getR_number();
		System.out.println("r_number=>"+r_number);
		
		
		
		try {
			int r_cnumber = com.getR_cnumber();
			int r_cref = com.getR_cref();
			int r_cre_step = com.getR_cre_step();
			int r_cre_level = com.getR_cre_level();
			System.out.println("r_cnumber=>"+r_cnumber+", r_cref=>" + r_cref + ", r_cre_step=>" + r_cre_step + ", r_cre_level=>" + r_cre_level);
			
			int number=rCommentDao.getNewCommentNum()+1;
			System.out.println("number=>"+number);
			com.setR_cnumber(number);
			
			if  (r_cnumber != 0) { //대댓글
				System.out.println("update 이전 r_cre_step=>"+r_cre_step);
				r_cre_step=rCommentDao.getSuperStep(r_cref)+1;
				System.out.println("r_cre_step+1=>"+r_cre_step);
				r_cre_level=r_cre_level+1;
				System.out.println("update 이후 r_cre_step=>"+r_cre_step+", r_cre_level=>"+r_cre_level);
			}else { //신규글
				r_cref=number;
				r_cre_step=0;
				r_cre_level=0;
			}
			
			com.setR_cref(r_cref);
			com.setR_cre_step(r_cre_step);
			com.setR_cre_level(r_cre_level);
			int insert=rCommentDao.insertComment(com);
			System.out.println("댓글 작성 성공 여부(insert)=>"+insert);
			if (insert > 0) {
				String mem_id=com.getMem_id();
				System.out.println("R_CommentWriteController의 mem_id=>"+mem_id);
				int pointup=rCommentDao.pointupC(mem_id);
				System.out.println("댓글 작성시 포인트 적립 성공 여부(pointup)=>"+pointup);
				int point=rBoardDao.getPoint(mem_id);
				
				if (pointup > 0) {
					System.out.println("등급 변동 전 현재 등급=>"+rBoardDao.getGrade(mem_id));
					int gradeup=0;
					if (point < 100) {
						gradeup=rBoardDao.changeGrade1(mem_id);
					} else if (point >= 100 && point < 500) {
						gradeup=rBoardDao.changeGrade2(mem_id);
					} else if (point >= 500 && point < 2000) {
						gradeup=rBoardDao.changeGrade3(mem_id);
					} else if (point >= 2000 && point < 10000) {
						gradeup=rBoardDao.changeGrade4(mem_id);
					} else if (point >= 10000) {
						gradeup=rBoardDao.changeGrade5(mem_id);
					}
					System.out.println("댓글 작성 후 등급 변동 여부(gradeup)=>"+gradeup);
					System.out.println("등급 변동 후 현재 등급=>"+rBoardDao.getGrade(mem_id));
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
	
		
		//return "redirect:/R_CommentWrite.do?r_number="+r_number;
		ModelAndView mav=new ModelAndView("/board/ring/R_CommentWrite");
		mav.addObject("r_number", r_number);
		return mav;
	}
	
}

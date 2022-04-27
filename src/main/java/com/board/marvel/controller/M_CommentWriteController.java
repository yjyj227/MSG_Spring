package com.board.marvel.controller;

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

import com.board.marvel.dao.MBoardDao;
import com.board.marvel.dao.MCommentDao;
import com.board.marvel.domain.MCommentDTO;
import com.member.dao.MemberDao;
import com.member.domain.MemberDTO;

@Component
@Controller
public class M_CommentWriteController {

	private Logger log=Logger.getLogger(this.getClass());
	
	@Autowired
	private MCommentDao mCommentDao;
	@Autowired
	private MBoardDao mBoardDao;
	@Autowired
	private MemberDao memberDao;
	
	@RequestMapping(value="/M_CommentWrite.do", method=RequestMethod.GET)
	public String form(HttpServletRequest request) {
		System.out.println("form() 호출됨");
		request.setAttribute("m_number", request.getParameter("m_number"));
		System.out.println("request.getParameter('m_number')=>"+request.getParameter("m_number"));
		return "/board/marvel/M_CommentWrite";
	}
	
	//2. 에러메시지 출력
	@ModelAttribute("command")
	public MCommentDTO forBacking() {
		System.out.println("forBacking() 호출됨");
		return new MCommentDTO();
	}
	
	//3. 입력
	@RequestMapping(value="/M_CommentWrite.do", method=RequestMethod.POST)
	public ModelAndView submit(@ModelAttribute("command") MCommentDTO com,
						 BindingResult result,
						 HttpServletRequest request) {
		
		if (log.isDebugEnabled()) {
			System.out.println("/M_CommentWrite.do 요청중");
			log.debug("MCommentDTO : "+com);
		}
		
		int m_number=com.getM_number();
		System.out.println("m_number=>"+m_number);
		
		
		
		try {
			int m_cnumber = com.getM_cnumber();
			int m_cref = com.getM_cref();
			int m_cre_step = com.getM_cre_step();
			int m_cre_level = com.getM_cre_level();
			System.out.println("m_cnumber=>"+m_cnumber+", m_cref=>" + m_cref + ", m_cre_step=>" + m_cre_step + ", m_cre_level=>" + m_cre_level);
			
			int number=mCommentDao.getNewCommentNum()+1;
			System.out.println("number=>"+number);
			com.setM_cnumber(number);
			
			if  (m_cnumber != 0) { //대댓글
				System.out.println("update 이전 m_cre_step=>"+m_cre_step);
				m_cre_step=mCommentDao.getSuperStep(m_cref)+1;
				System.out.println("m_cre_step+1=>"+m_cre_step);
				m_cre_level=m_cre_level+1;
				System.out.println("update 이후 m_cre_step=>"+m_cre_step+", m_cre_level=>"+m_cre_level);
			}else { //신규글
				m_cref=number;
				m_cre_step=0;
				m_cre_level=0;
			}
			
			com.setM_cref(m_cref);
			com.setM_cre_step(m_cre_step);
			com.setM_cre_level(m_cre_level);
			int insert=mCommentDao.insertComment(com);
			System.out.println("댓글 작성 성공 여부(insert)=>"+insert);
			if (insert > 0) {
				String mem_id=com.getMem_id();
				System.out.println("M_CommentWriteController의 mem_id=>"+mem_id);
				int pointup=mCommentDao.pointupC(mem_id);
				System.out.println("댓글 작성시 포인트 적립 성공 여부(pointup)=>"+pointup);
				int point=mBoardDao.getPoint(mem_id);
				
				if (pointup > 0) {
					System.out.println("등급 변동 전 현재 등급=>"+mBoardDao.getGrade(mem_id));
					int gradeup=0;
					if (point < 100) {
						gradeup=mBoardDao.changeGrade1(mem_id);
					} else if (point >= 100 && point < 500) {
						gradeup=mBoardDao.changeGrade2(mem_id);
					} else if (point >= 500 && point < 2000) {
						gradeup=mBoardDao.changeGrade3(mem_id);
					} else if (point >= 2000 && point < 10000) {
						gradeup=mBoardDao.changeGrade4(mem_id);
					} else if (point >= 10000) {
						gradeup=mBoardDao.changeGrade5(mem_id);
					}
					System.out.println("댓글 작성 후 등급 변동 여부(gradeup)=>"+gradeup);
					System.out.println("등급 변동 후 현재 등급=>"+mBoardDao.getGrade(mem_id));
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
	
		
		//return "redirect:/M_CommentWrite.do?m_number="+m_number;
		ModelAndView mav=new ModelAndView("/board/marvel/M_CommentWrite");
		mav.addObject("m_number", m_number);
		return mav;
	}
	
}

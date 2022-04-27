package com.board.zzang.controller;

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

import com.board.zzang.dao.ZBoardDao;
import com.board.zzang.dao.ZCommentDao;
import com.board.zzang.domain.ZCommentDTO;
import com.member.dao.MemberDao;
import com.member.domain.MemberDTO;

@Component
@Controller
public class Z_CommentWriteController {

	private Logger log=Logger.getLogger(this.getClass());
	
	@Autowired
	private ZCommentDao zCommentDao;
	@Autowired
	private ZBoardDao zBoardDao;
	@Autowired
	private MemberDao memberDao;
	
	@RequestMapping(value="/Z_CommentWrite.do", method=RequestMethod.GET)
	public String form(HttpServletRequest request) {
		System.out.println("form() 호출됨");
		request.setAttribute("z_number", request.getParameter("z_number"));
		System.out.println("request.getParameter('z_number')=>"+request.getParameter("z_number"));
		return "/board/zzang/Z_CommentWrite";
	}
	
	//2. 에러메시지 출력
	@ModelAttribute("command")
	public ZCommentDTO forBacking() {
		System.out.println("forBacking() 호출됨");
		return new ZCommentDTO();
	}
	
	//3. 입력
	@RequestMapping(value="/Z_CommentWrite.do", method=RequestMethod.POST)
	public ModelAndView submit(@ModelAttribute("command") ZCommentDTO com,
						 BindingResult result,
						 HttpServletRequest request) {
		
		if (log.isDebugEnabled()) {
			System.out.println("/Z_CommentWrite.do 요청중");
			log.debug("ZCommentDTO : "+com);
		}
		
		int z_number=com.getZ_number();
		System.out.println("z_number=>"+z_number);
		
		
		
		try {
			int z_cnumber = com.getZ_cnumber();
			int z_cref = com.getZ_cref();
			int z_cre_step = com.getZ_cre_step();
			int z_cre_level = com.getZ_cre_level();
			System.out.println("z_cnumber=>"+z_cnumber+", z_cref=>" + z_cref + ", z_cre_step=>" + z_cre_step + ", z_cre_level=>" + z_cre_level);
			
			int number=zCommentDao.getNewCommentNum()+1;
			System.out.println("number=>"+number);
			com.setZ_cnumber(number);
			
			if  (z_cnumber != 0) { //대댓글
				System.out.println("update 이전 z_cre_step=>"+z_cre_step);
				z_cre_step=zCommentDao.getSuperStep(z_cref)+1;
				System.out.println("z_cre_step+1=>"+z_cre_step);
				z_cre_level=z_cre_level+1;
				System.out.println("update 이후 z_cre_step=>"+z_cre_step+", z_cre_level=>"+z_cre_level);
			}else { //신규글
				z_cref=number;
				z_cre_step=0;
				z_cre_level=0;
			}
			
			com.setZ_cref(z_cref);
			com.setZ_cre_step(z_cre_step);
			com.setZ_cre_level(z_cre_level);
			int insert=zCommentDao.insertComment(com);
			System.out.println("댓글 작성 성공 여부(insert)=>"+insert);
			if (insert > 0) {
				String mem_id=com.getMem_id();
				System.out.println("Z_CommentWriteController의 mem_id=>"+mem_id);
				int pointup=zCommentDao.pointupC(mem_id);
				System.out.println("댓글 작성시 포인트 적립 성공 여부(pointup)=>"+pointup);
				int point=zBoardDao.getPoint(mem_id);
				
				if (pointup > 0) {
					System.out.println("등급 변동 전 현재 등급=>"+zBoardDao.getGrade(mem_id));
					int gradeup=0;
					if (point < 100) {
						gradeup=zBoardDao.changeGrade1(mem_id);
					} else if (point >= 100 && point < 500) {
						gradeup=zBoardDao.changeGrade2(mem_id);
					} else if (point >= 500 && point < 2000) {
						gradeup=zBoardDao.changeGrade3(mem_id);
					} else if (point >= 2000 && point < 10000) {
						gradeup=zBoardDao.changeGrade4(mem_id);
					} else if (point >= 10000) {
						gradeup=zBoardDao.changeGrade5(mem_id);
					}
					System.out.println("댓글 작성 후 등급 변동 여부(gradeup)=>"+gradeup);
					System.out.println("등급 변동 후 현재 등급=>"+zBoardDao.getGrade(mem_id));
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
	
		
		//return "redirect:/Z_CommentWrite.do?z_number="+z_number;
		ModelAndView mav=new ModelAndView("/board/zzang/Z_CommentWrite");
		mav.addObject("z_number", z_number);
		return mav;
	}
	
}

package com.board.harry.controller;

import java.io.File;

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

import com.board.harry.dao.HBoardDao;
import com.board.harry.domain.HBoardDTO;
import com.board.harry.util.H_FileUtil;
import com.board.validator.BoardValidator;
import com.member.dao.MemberDao;
import com.member.domain.MemberDTO;

@Component
@Controller
public class H_WriteController {

	private Logger log=Logger.getLogger(this.getClass());
	
	@Autowired
	private HBoardDao hBoardDao;
	@Autowired
	private MemberDao memberDao;
	
	//1. 글쓰기폼으로 이동
	@RequestMapping(value="/H_Write.do", method=RequestMethod.GET)
	public String form() {
		System.out.println("초기화 form() 호출됨");
		return "/board/harry/H_Write";
	}
	
	
	//2. 에러메시지 출력
	@ModelAttribute("command")
	public HBoardDTO forBacking() {
		System.out.println("forbacking() 호출됨");
		return new HBoardDTO();
	}
	
	
	//3. 입력해서 유효성검사->에러 발생
	@RequestMapping(value="/H_Write.do", method=RequestMethod.POST)
	public String submit(@ModelAttribute("command") HBoardDTO com,
						  BindingResult result,
						  HttpServletRequest request) {
		
		
		
		if (log.isDebugEnabled()) {
			System.out.println("/H_Write.do 요청중");
			log.debug("HBoardDTO : "+com);
		}
		
		/*
		//유효성검사
		new BoardValidator().validate(com, result);
		//에러정보가 있다면
		if (result.hasErrors()) {
			return form(); //"H_Write" -> "H_Write.jsp"로 이동해라
		}
		*/
		
		//글쓰기 및 업로드=>입출력=>예외처리
		int newNum=0;
		
		try {
			//-----파일 업로드-----
			String newName="";
			if (!com.getUpload().isEmpty()) {
				newName=H_FileUtil.rename(com.getUpload().getOriginalFilename());
				System.out.println("newName=>"+newName);
				com.setH_filename(newName);
			}
			//-----파일 업로드-----
			//최댓값+1
			newNum=hBoardDao.getNewNum()+1;
			System.out.println("newNum=>"+newNum);
			//게시물번호 저장
			com.setH_number(newNum);
			//글쓰기 호출
			int insert=hBoardDao.insert(com);
			if (!com.getUpload().isEmpty()) {
				File file=new File(H_FileUtil.UPLOAD_PATH+"/"+newName);
				com.getUpload().transferTo(file);
			}
			System.out.println("글 작성 성공 여부(insert)=>"+insert);
			if (insert > 0) {
				String mem_id=com.getMem_id();
				System.out.println("H_WriteController의 mem_id=>"+mem_id);
				int pointup=hBoardDao.pointupA(mem_id);
				System.out.println("글 작성시 포인트 적립 성공 여부(pointup)=>"+pointup);
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
					System.out.println("글 작성 후 등급 변동 여부(gradeup)=>"+gradeup);
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
		
		return "redirect:/H_Content.do?h_number="+newNum;
	}
	
	
	
	
	
	
	
	
	
	
}

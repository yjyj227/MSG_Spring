package com.board.notice.controller;

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

import com.board.notice.dao.NoticeDao;
import com.board.notice.domain.NoticeDTO;
import com.board.notice.util.N_FileUtil;
import com.member.dao.MemberDao;
import com.member.domain.MemberDTO;

@Component
@Controller
public class N_WriteController {

private Logger log=Logger.getLogger(this.getClass());
	
	@Autowired
	private NoticeDao noticeDao;
	
	//1. 글쓰기폼으로 이동
	@RequestMapping(value="/N_Write.do", method=RequestMethod.GET)
	public String form() {
		System.out.println("초기화 form() 호출됨");
		return "/board/notice/N_Write";
	}
	
	
	//2. 에러메시지 출력
	@ModelAttribute("command")
	public NoticeDTO forBacking() {
		System.out.println("forbacking() 호출됨");
		return new NoticeDTO();
	}
	
	
	//3. 입력해서 유효성검사->에러 발생
	@RequestMapping(value="/N_Write.do", method=RequestMethod.POST)
	public String submit(@ModelAttribute("command") NoticeDTO com,
						  BindingResult result,
						  HttpServletRequest request) {
		
		if (log.isDebugEnabled()) {
			System.out.println("/N_Write.do 요청중");
			log.debug("NoticeDTO : "+com);
		}
		
		/*
		//유효성검사
		new BoardValidator().validate(com, result);
		//에러정보가 있다면
		if (result.hasErrors()) {
			return form(); //"N_Write" -> "N_Write.jsp"로 이동해라
		}
		*/
		
		//글쓰기 및 업로드=>입출력=>예외처리
		int newNum=0;
		
		try {
			//-----파일 업로드-----
			String newName="";
			if (!com.getUpload().isEmpty()) {
				newName=N_FileUtil.rename(com.getUpload().getOriginalFilename());
				System.out.println("newName=>"+newName);
				com.setNot_filename(newName);
			}
			//-----파일 업로드-----
			//최댓값+1
			newNum=noticeDao.getNewNum()+1;
			System.out.println("newNum=>"+newNum);
			//게시물번호 저장
			com.setNotice_number(newNum);
			//글쓰기 호출
			int insert=noticeDao.insert(com);
			if (!com.getUpload().isEmpty()) {
				File file=new File(N_FileUtil.UPLOAD_PATH+"/"+newName);
				com.getUpload().transferTo(file);
			}
			System.out.println("글 작성 성공 여부(insert)=>"+insert);
			
		}catch (Exception e2) {
			e2.printStackTrace();
		}
		
		return "redirect:/N_Content.do?notice_number="+newNum;
	}
	
	
	
	
	
	
	
	
	
	
}

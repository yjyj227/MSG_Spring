package com.board.notice.controller;

import java.io.File;
import java.io.IOException;

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

import com.board.notice.dao.NoticeDao;
import com.board.notice.domain.NoticeDTO;
import com.board.notice.util.N_FileUtil;

@Component
@Controller
public class N_UpdateController {

	private Logger log=Logger.getLogger(this.getClass());
	
	@Autowired
	private NoticeDao noticeDao;
	
	//1. 글수정 폼으로 이동
	@RequestMapping(value="/N_Update.do", method=RequestMethod.GET)
	public ModelAndView form(@RequestParam("notice_number") int notice_number) {
		System.out.println("form() 호출됨");
		NoticeDTO noticeDTO=noticeDao.getBoard(notice_number);
		return new ModelAndView("/board/notice/N_Update", "article", noticeDTO);
	}
	
	//2. 입력
	@RequestMapping(value="/N_Update.do", method=RequestMethod.POST)
	public String submit(@ModelAttribute("command") NoticeDTO com,
						 BindingResult result) {
		
		if (log.isDebugEnabled()) {
			System.out.println("/N_Update.do 요청중");
			log.debug("NoticeDTO : "+com);
		}
		
		//유효성검사
		int notice_number=com.getNotice_number();
		
		NoticeDTO notice=null;
		String oldFileName="";
		
		notice=noticeDao.getBoard(com.getNotice_number());
		
		oldFileName=notice.getNot_filename();
		System.out.println("oldFileName=>"+oldFileName);
		System.out.println("새 파일 이름=>"+com.getUpload());
		if (com.getUpload() != null) {
			try {
			com.setNot_filename(N_FileUtil.rename(com.getUpload().getOriginalFilename()));
			}catch (Exception e) {
				e.printStackTrace();
			}
		}else {
			com.setNot_filename(oldFileName);
		}
		
		if (com.getUpload() != null) {
			try {
				File file=new File(N_FileUtil.UPLOAD_PATH+"/"+com.getNot_filename());
				com.getUpload().transferTo(file);
			}catch (IOException e1) {
				e1.printStackTrace();
			}catch (Exception e2) {
				e2.printStackTrace();
			}
			if (oldFileName != null) {
				N_FileUtil.removeFile(oldFileName);
			}
		}
		
		noticeDao.update(com);
		
		return "redirect:/N_Content.do?notice_number="+notice_number;
		
	}
	
}

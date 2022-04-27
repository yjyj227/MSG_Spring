package com.board.marvel.controller;

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

import com.board.marvel.dao.MBoardDao;
import com.board.marvel.domain.MBoardDTO;
import com.board.marvel.util.M_FileUtil;

@Component
@Controller
public class M_UpdateController {

	private Logger log=Logger.getLogger(this.getClass());
	
	@Autowired
	private MBoardDao mBoardDao;
	
	//1. 글수정 폼으로 이동
	@RequestMapping(value="/M_Update.do", method=RequestMethod.GET)
	public ModelAndView form(@RequestParam("m_number") int m_number) {
		System.out.println("form() 호출됨");
		MBoardDTO mBoardDTO=mBoardDao.getBoard(m_number);
		return new ModelAndView("/board/marvel/M_Update", "article", mBoardDTO);
	}
	
	//2. 입력
	@RequestMapping(value="/M_Update.do", method=RequestMethod.POST)
	public String submit(@ModelAttribute("command") MBoardDTO com,
						 BindingResult result) {
		
		if (log.isDebugEnabled()) {
			System.out.println("/M_Update.do 요청중");
			log.debug("MBoardDTO : "+com);
		}
		
		//유효성검사
		int m_number=com.getM_number();
		
		MBoardDTO mBoard=null;
		String oldFileName="";
		
		mBoard=mBoardDao.getBoard(com.getM_number());
		
		oldFileName=mBoard.getM_filename();
		System.out.println("oldFileName=>"+oldFileName);
		System.out.println("새 파일 이름=>"+com.getUpload());
		if (com.getUpload() != null) {
			try {
			com.setM_filename(M_FileUtil.rename(com.getUpload().getOriginalFilename()));
			}catch (Exception e) {
				e.printStackTrace();
			}
		}else {
			com.setM_filename(oldFileName);
		}
		
		if (com.getUpload() != null) {
			try {
				File file=new File(M_FileUtil.UPLOAD_PATH+"/"+com.getM_filename());
				com.getUpload().transferTo(file);
			}catch (IOException e1) {
				e1.printStackTrace();
			}catch (Exception e2) {
				e2.printStackTrace();
			}
			if (oldFileName != null) {
				M_FileUtil.removeFile(oldFileName);
			}
		}
		
		mBoardDao.update(com);
		
		
		
		return "redirect:/M_Content.do?m_number="+m_number;
		
	}
	
	
}

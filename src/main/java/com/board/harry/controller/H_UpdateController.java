package com.board.harry.controller;

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

import com.board.harry.dao.HBoardDao;
import com.board.harry.domain.HBoardDTO;
import com.board.harry.util.H_FileUtil;

@Component
@Controller
public class H_UpdateController {

	private Logger log=Logger.getLogger(this.getClass());
	
	@Autowired
	private HBoardDao hBoardDao;
	
	//1. 글수정 폼으로 이동
	@RequestMapping(value="/H_Update.do", method=RequestMethod.GET)
	public ModelAndView form(@RequestParam("h_number") int h_number) {
		System.out.println("form() 호출됨");
		HBoardDTO hBoardDTO=hBoardDao.getBoard(h_number);
		return new ModelAndView("/board/harry/H_Update", "article", hBoardDTO);
	}
	
	//2. 입력
	@RequestMapping(value="/H_Update.do", method=RequestMethod.POST)
	public String submit(@ModelAttribute("command") HBoardDTO com,
						 BindingResult result) {
		
		if (log.isDebugEnabled()) {
			System.out.println("/H_Update.do 요청중");
			log.debug("HBoardDTO : "+com);
		}
		
		//유효성검사
		int h_number=com.getH_number();
		
		HBoardDTO hBoard=null;
		String oldFileName="";
		
		hBoard=hBoardDao.getBoard(com.getH_number());
		
		oldFileName=hBoard.getH_filename();
		System.out.println("oldFileName=>"+oldFileName);
		System.out.println("새 파일 이름=>"+com.getUpload());
		if (com.getUpload() != null) {
			try {
			com.setH_filename(H_FileUtil.rename(com.getUpload().getOriginalFilename()));
			}catch (Exception e) {
				e.printStackTrace();
			}
		}else {
			com.setH_filename(oldFileName);
		}
		
		if (com.getUpload() != null) {
			try {
				File file=new File(H_FileUtil.UPLOAD_PATH+"/"+com.getH_filename());
				com.getUpload().transferTo(file);
			}catch (IOException e1) {
				e1.printStackTrace();
			}catch (Exception e2) {
				e2.printStackTrace();
			}
			if (oldFileName != null) {
				H_FileUtil.removeFile(oldFileName);
			}
		}
		
		hBoardDao.update(com);
		
		
		
		return "redirect:/H_Content.do?h_number="+h_number;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

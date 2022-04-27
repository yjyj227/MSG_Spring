package com.board.ring.controller;

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

import com.board.ring.dao.RBoardDao;
import com.board.ring.domain.RBoardDTO;
import com.board.ring.util.R_FileUtil;

@Component
@Controller
public class R_UpdateController {

	private Logger log=Logger.getLogger(this.getClass());
	
	@Autowired
	private RBoardDao rBoardDao;
	
	//1. 글수정 폼으로 이동
	@RequestMapping(value="/R_Update.do", method=RequestMethod.GET)
	public ModelAndView form(@RequestParam("r_number") int r_number) {
		System.out.println("form() 호출됨");
		RBoardDTO rBoardDTO=rBoardDao.getBoard(r_number);
		return new ModelAndView("/board/ring/R_Update", "article", rBoardDTO);
	}
	
	//2. 입력
	@RequestMapping(value="/R_Update.do", method=RequestMethod.POST)
	public String submit(@ModelAttribute("command") RBoardDTO com,
						 BindingResult result) {
		
		if (log.isDebugEnabled()) {
			System.out.println("/R_Update.do 요청중");
			log.debug("RBoardDTO : "+com);
		}
		
		//유효성검사
		int r_number=com.getR_number();
		
		RBoardDTO rBoard=null;
		String oldFileName="";
		
		rBoard=rBoardDao.getBoard(com.getR_number());
		
		oldFileName=rBoard.getR_filename();
		System.out.println("oldFileName=>"+oldFileName);
		System.out.println("새 파일 이름=>"+com.getUpload());
		if (com.getUpload() != null) {
			try {
			com.setR_filename(R_FileUtil.rename(com.getUpload().getOriginalFilename()));
			}catch (Exception e) {
				e.printStackTrace();
			}
		}else {
			com.setR_filename(oldFileName);
		}
		
		if (com.getUpload() != null) {
			try {
				File file=new File(R_FileUtil.UPLOAD_PATH+"/"+com.getR_filename());
				com.getUpload().transferTo(file);
			}catch (IOException e1) {
				e1.printStackTrace();
			}catch (Exception e2) {
				e2.printStackTrace();
			}
			if (oldFileName != null) {
				R_FileUtil.removeFile(oldFileName);
			}
		}
		
		rBoardDao.update(com);
		
		
		
		return "redirect:/R_Content.do?r_number="+r_number;
		
	}
	
	
	
}

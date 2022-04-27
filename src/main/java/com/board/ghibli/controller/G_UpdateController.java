package com.board.ghibli.controller;

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

import com.board.ghibli.dao.GBoardDao;
import com.board.ghibli.domain.GBoardDTO;
import com.board.ghibli.util.G_FileUtil;

@Component
@Controller
public class G_UpdateController {

	private Logger log=Logger.getLogger(this.getClass());
	
	@Autowired
	private GBoardDao gBoardDao;
	
	//1. 글수정 폼으로 이동
	@RequestMapping(value="/G_Update.do", method=RequestMethod.GET)
	public ModelAndView form(@RequestParam("g_number") int g_number) {
		System.out.println("form() 호출됨");
		GBoardDTO gBoardDTO=gBoardDao.getBoard(g_number);
		return new ModelAndView("/board/ghibli/G_Update", "article", gBoardDTO);
	}
	
	//2. 입력
	@RequestMapping(value="/G_Update.do", method=RequestMethod.POST)
	public String submit(@ModelAttribute("command") GBoardDTO com,
						 BindingResult result) {
		
		if (log.isDebugEnabled()) {
			System.out.println("/G_Update.do 요청중");
			log.debug("GBoardDTO : "+com);
		}
		
		//유효성검사
		int g_number=com.getG_number();
		
		GBoardDTO gBoard=null;
		String oldFileName="";
		
		gBoard=gBoardDao.getBoard(com.getG_number());
		
		oldFileName=gBoard.getG_filename();
		System.out.println("oldFileName=>"+oldFileName);
		System.out.println("새 파일 이름=>"+com.getUpload());
		if (com.getUpload() != null) {
			try {
			com.setG_filename(G_FileUtil.rename(com.getUpload().getOriginalFilename()));
			}catch (Exception e) {
				e.printStackTrace();
			}
		}else {
			com.setG_filename(oldFileName);
		}
		
		if (com.getUpload() != null) {
			try {
				File file=new File(G_FileUtil.UPLOAD_PATH+"/"+com.getG_filename());
				com.getUpload().transferTo(file);
			}catch (IOException e1) {
				e1.printStackTrace();
			}catch (Exception e2) {
				e2.printStackTrace();
			}
			if (oldFileName != null) {
				G_FileUtil.removeFile(oldFileName);
			}
		}
		
		gBoardDao.update(com);
		
		
		
		return "redirect:/G_Content.do?g_number="+g_number;
		
	}
	
	
	
}

package com.board.zzang.controller;

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

import com.board.zzang.dao.ZBoardDao;
import com.board.zzang.domain.ZBoardDTO;
import com.board.zzang.util.Z_FileUtil;

@Component
@Controller
public class Z_UpdateController {

	private Logger log=Logger.getLogger(this.getClass());
	
	@Autowired
	private ZBoardDao zBoardDao;
	
	//1. 글수정 폼으로 이동
	@RequestMapping(value="/Z_Update.do", method=RequestMethod.GET)
	public ModelAndView form(@RequestParam("z_number") int z_number) {
		System.out.println("form() 호출됨");
		ZBoardDTO zBoardDTO=zBoardDao.getBoard(z_number);
		return new ModelAndView("/board/zzang/Z_Update", "article", zBoardDTO);
	}
	
	//2. 입력
	@RequestMapping(value="/Z_Update.do", method=RequestMethod.POST)
	public String submit(@ModelAttribute("command") ZBoardDTO com,
						 BindingResult result) {
		
		if (log.isDebugEnabled()) {
			System.out.println("/Z_Update.do 요청중");
			log.debug("ZBoardDTO : "+com);
		}
		
		//유효성검사
		int z_number=com.getZ_number();
		
		ZBoardDTO zBoard=null;
		String oldFileName="";
		
		zBoard=zBoardDao.getBoard(com.getZ_number());
		
		oldFileName=zBoard.getZ_filename();
		System.out.println("oldFileName=>"+oldFileName);
		System.out.println("새 파일 이름=>"+com.getUpload());
		if (com.getUpload() != null) {
			try {
			com.setZ_filename(Z_FileUtil.rename(com.getUpload().getOriginalFilename()));
			}catch (Exception e) {
				e.printStackTrace();
			}
		}else {
			com.setZ_filename(oldFileName);
		}
		
		if (com.getUpload() != null) {
			try {
				File file=new File(Z_FileUtil.UPLOAD_PATH+"/"+com.getZ_filename());
				com.getUpload().transferTo(file);
			}catch (IOException e1) {
				e1.printStackTrace();
			}catch (Exception e2) {
				e2.printStackTrace();
			}
			if (oldFileName != null) {
				Z_FileUtil.removeFile(oldFileName);
			}
		}
		
		zBoardDao.update(com);
		
		
		
		return "redirect:/Z_Content.do?z_number="+z_number;
		
	}
	
	
}

package com.board.notice.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.board.notice.dao.NoticeDao;
import com.board.notice.domain.NoticeDTO;
import com.board.util.StringUtil;

@Component
@Controller
public class N_ContentController {

	private Logger log=Logger.getLogger(this.getClass());
	
	@Autowired
	private NoticeDao noticeDao;
	
	@RequestMapping("/N_Content.do")
	public ModelAndView process(@RequestParam("notice_number") int notice_number,
								HttpServletRequest request) {
		if (log.isDebugEnabled()) {
			System.out.println("/N_Content.do 요청중");
			log.debug("notice_number=>"+notice_number);
		}
		
		noticeDao.updateHit(notice_number);
		NoticeDTO article=noticeDao.getBoard(notice_number);
		article.setNot_body(StringUtil.parseBr(article.getNot_body()));
		
		String admin_id=article.getAdmin_id();
		System.out.println("N_ContentController의 admin_id=>"+admin_id);
		
		request.setAttribute("article", article);
		
		ModelAndView mav=new ModelAndView("/board/notice/N_Content");
		mav.addObject("article", article);
		return mav;
	}
}

package com.board.harry.controller;

import java.util.HashMap;
import java.util.Map;

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

import com.board.dao.BoardDao;
import com.board.domain.BoardDTO;
import com.board.harry.dao.HBoardDao;
import com.board.harry.domain.HBoardDTO;

@Component
@Controller
public class H_RateController {

	private Logger log=Logger.getLogger(this.getClass());
	
	@Autowired
	private HBoardDao hBoardDao;
	@Autowired
	private BoardDao boardDao;
	
	//1. 별점 폼으로 이동
	@RequestMapping(value="/H_Rate.do", method=RequestMethod.GET)
	public String form(HttpServletRequest request) {
		System.out.println("초기화 form() 호출됨");
		
		HttpSession session=request.getSession();
		String mem_id=(String)session.getAttribute("idKey");
		//String mem_id=request.getParameter("mem_id");
		System.out.println("H_RateController form()의 mem_id=>"+mem_id);
		
		
			double avg11=boardDao.avgStar(11);
			double avg12=boardDao.avgStar(12);
			double avg13=boardDao.avgStar(13);
			double avg14=boardDao.avgStar(14);
			double avg15=boardDao.avgStar(15);
			double avg16=boardDao.avgStar(16);
			double avg17=boardDao.avgStar(17);
			double avg18=boardDao.avgStar(18);
			
			int sum11=boardDao.countLikes(11);
			int sum12=boardDao.countLikes(12);
			int sum13=boardDao.countLikes(13);
			int sum14=boardDao.countLikes(14);
			int sum15=boardDao.countLikes(15);
			int sum16=boardDao.countLikes(16);
			int sum17=boardDao.countLikes(17);
			int sum18=boardDao.countLikes(18);
			
			request.setAttribute("avg11", avg11);
			request.setAttribute("avg12", avg12);
			request.setAttribute("avg13", avg13);
			request.setAttribute("avg14", avg14);
			request.setAttribute("avg15", avg15);
			request.setAttribute("avg16", avg16);
			request.setAttribute("avg17", avg17);
			request.setAttribute("avg18", avg18);
			
			request.setAttribute("sum11", sum11);
			request.setAttribute("sum12", sum12);
			request.setAttribute("sum13", sum13);
			request.setAttribute("sum14", sum14);
			request.setAttribute("sum15", sum15);
			request.setAttribute("sum16", sum16);
			request.setAttribute("sum17", sum17);
			request.setAttribute("sum18", sum18);
			
		if (mem_id!=null) {
			Map<String, Object> map11=new HashMap<String, Object>();
			map11.put("mem_id", mem_id);
			map11.put("v_movie", 11);
			BoardDTO article11=boardDao.getMyRate(map11);
			Map<String, Object> map12=new HashMap<String, Object>();
			map12.put("mem_id", mem_id);
			map12.put("v_movie", 12);
			BoardDTO article12=boardDao.getMyRate(map12);
			Map<String, Object> map13=new HashMap<String, Object>();
			map13.put("mem_id", mem_id);
			map13.put("v_movie", 13);
			BoardDTO article13=boardDao.getMyRate(map13);
			Map<String, Object> map14=new HashMap<String, Object>();
			map14.put("mem_id", mem_id);
			map14.put("v_movie", 14);
			BoardDTO article14=boardDao.getMyRate(map14);
			Map<String, Object> map15=new HashMap<String, Object>();
			map15.put("mem_id", mem_id);
			map15.put("v_movie", 15);
			BoardDTO article15=boardDao.getMyRate(map15);
			Map<String, Object> map16=new HashMap<String, Object>();
			map16.put("mem_id", mem_id);
			map16.put("v_movie", 16);
			BoardDTO article16=boardDao.getMyRate(map16);
			Map<String, Object> map17=new HashMap<String, Object>();
			map17.put("mem_id", mem_id);
			map17.put("v_movie", 17);
			BoardDTO article17=boardDao.getMyRate(map17);
			Map<String, Object> map18=new HashMap<String, Object>();
			map18.put("mem_id", mem_id);
			map18.put("v_movie", 18);
			BoardDTO article18=boardDao.getMyRate(map18);
			
			request.setAttribute("article11", article11);
			request.setAttribute("article12", article12);
			request.setAttribute("article13", article13);
			request.setAttribute("article14", article14);
			request.setAttribute("article15", article15);
			request.setAttribute("article16", article16);
			request.setAttribute("article17", article17);
			request.setAttribute("article18", article18);
		}
		return "/board/harry/H_Rate";
	}
	
	//2. 에러메시지 출력
	@ModelAttribute("command")
	public BoardDTO forBacking() {
		System.out.println("forBacking() 호출됨");
		return new BoardDTO();
	}
	
	//3. 입력
	@RequestMapping(value="/H_Rate.do", method=RequestMethod.POST)
	public String submit(@ModelAttribute("command") BoardDTO com,
						 BindingResult result,
						 HttpServletRequest request) {
		
		if (log.isDebugEnabled()) {
			System.out.println("/H_Rate.do 요청중");
			log.debug("BoardDTO : "+com);
		}
		
		HttpSession session=request.getSession();
		String mem_id=(String)session.getAttribute("idKey");
		System.out.println("H_RateController submit()의 mem_id=>"+mem_id);
		
		int update=0;
		int insert=0;
		
		Map<String, Object> mapF=new HashMap<String, Object>();
		mapF.put("mem_id", mem_id);
		System.out.println("com.getV_movie()=>"+com.getV_movie());
		mapF.put("v_movie", com.getV_movie());
		int find=boardDao.findRate(mapF);
		
		if(find > 0) {
			update=boardDao.updateRate(com);
		}else {
			int maxnum=boardDao.getMaxNum()+1;
			com.setV_number(maxnum);
			insert=boardDao.insertRate(com);
		}
		
		return "redirect:/H_Rate.do";
	}
	
	
	
	
	
	
}

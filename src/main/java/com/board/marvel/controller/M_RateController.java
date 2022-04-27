package com.board.marvel.controller;

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
import com.board.marvel.dao.MBoardDao;
import com.board.marvel.domain.MBoardDTO;

@Component
@Controller
public class M_RateController {

	private Logger log=Logger.getLogger(this.getClass());
	
	@Autowired
	private MBoardDao mBoardDao;
	@Autowired
	private BoardDao boardDao;
	
	//1. 별점 폼으로 이동
	@RequestMapping(value="/M_Rate.do", method=RequestMethod.GET)
	public String form(HttpServletRequest request) {
		System.out.println("초기화 form() 호출됨");
		
		HttpSession session=request.getSession();
		String mem_id=(String)session.getAttribute("idKey");
		//String mem_id=request.getParameter("mem_id");
		System.out.println("M_RateController form()의 mem_id=>"+mem_id);
		
		
			double avg31=boardDao.avgStar(31);
			double avg32=boardDao.avgStar(32);
			double avg33=boardDao.avgStar(33);
			double avg34=boardDao.avgStar(34);
			double avg35=boardDao.avgStar(35);
			
			int sum31=boardDao.countLikes(31);
			int sum32=boardDao.countLikes(32);
			int sum33=boardDao.countLikes(33);
			int sum34=boardDao.countLikes(34);
			int sum35=boardDao.countLikes(35);
			
			request.setAttribute("avg31", avg31);
			request.setAttribute("avg32", avg32);
			request.setAttribute("avg33", avg33);
			request.setAttribute("avg34", avg34);
			request.setAttribute("avg35", avg35);
			
			request.setAttribute("sum31", sum31);
			request.setAttribute("sum32", sum32);
			request.setAttribute("sum33", sum33);
			request.setAttribute("sum34", sum34);
			request.setAttribute("sum35", sum35);
			
		if (mem_id!=null) {
			Map<String, Object> map31=new HashMap<String, Object>();
			map31.put("mem_id", mem_id);
			map31.put("v_movie", 31);
			BoardDTO article31=boardDao.getMyRate(map31);
			Map<String, Object> map32=new HashMap<String, Object>();
			map32.put("mem_id", mem_id);
			map32.put("v_movie", 32);
			BoardDTO article32=boardDao.getMyRate(map32);
			Map<String, Object> map33=new HashMap<String, Object>();
			map33.put("mem_id", mem_id);
			map33.put("v_movie", 33);
			BoardDTO article33=boardDao.getMyRate(map33);
			Map<String, Object> map34=new HashMap<String, Object>();
			map34.put("mem_id", mem_id);
			map34.put("v_movie", 34);
			BoardDTO article34=boardDao.getMyRate(map34);
			Map<String, Object> map35=new HashMap<String, Object>();
			map35.put("mem_id", mem_id);
			map35.put("v_movie", 35);
			BoardDTO article35=boardDao.getMyRate(map35);
			
			request.setAttribute("article31", article31);
			request.setAttribute("article32", article32);
			request.setAttribute("article33", article33);
			request.setAttribute("article34", article34);
			request.setAttribute("article35", article35);
		}
		return "/board/marvel/M_Rate";
	}
	
	//2. 에러메시지 출력
	@ModelAttribute("command")
	public BoardDTO forBacking() {
		System.out.println("forBacking() 호출됨");
		return new BoardDTO();
	}
	
	//3. 입력
	@RequestMapping(value="/M_Rate.do", method=RequestMethod.POST)
	public String submit(@ModelAttribute("command") BoardDTO com,
						 BindingResult result,
						 HttpServletRequest request) {
		
		if (log.isDebugEnabled()) {
			System.out.println("/M_Rate.do 요청중");
			log.debug("BoardDTO : "+com);
		}
		
		HttpSession session=request.getSession();
		String mem_id=(String)session.getAttribute("idKey");
		System.out.println("M_RateController submit()의 mem_id=>"+mem_id);
		
		int update=0;
		int insert=0;
		
		Map<String, Object> mapF=new HashMap<String, Object>();
		mapF.put("mem_id", mem_id);
		System.out.println("com.getV_movie()=>"+com.getV_movie());
		mapF.put("v_movie", com.getV_movie());
		int find=boardDao.findRate(mapF);
		
		if(find > 0) {
			update=boardDao.updateRate(com);
			System.out.println("별점 or 좋아요 수정 성공 여부(update)=>"+update);
		}else {
			int maxnum=boardDao.getMaxNum()+1;
			com.setV_number(maxnum);
			insert=boardDao.insertRate(com);
			System.out.println("별점 or 좋아요 등록 성공 여부(insert)=>"+insert);
		}
		
		return "redirect:/M_Rate.do";
	}
	
	
	
	
	
	
}

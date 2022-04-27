package com.board.ghibli.controller;

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
import com.board.ghibli.dao.GBoardDao;
import com.board.ghibli.domain.GBoardDTO;

@Component
@Controller
public class G_RateController {

	private Logger log=Logger.getLogger(this.getClass());
	
	@Autowired
	private GBoardDao gBoardDao;
	@Autowired
	private BoardDao boardDao;
	
	//1. 별점 폼으로 이동
	@RequestMapping(value="/G_Rate.do", method=RequestMethod.GET)
	public String form(HttpServletRequest request) {
		System.out.println("초기화 form() 호출됨");
		
		HttpSession session=request.getSession();
		String mem_id=(String)session.getAttribute("idKey");
		//String mem_id=request.getParameter("mem_id");
		System.out.println("G_RateController form()의 mem_id=>"+mem_id);
		
		
			double avg41=boardDao.avgStar(41);
			double avg42=boardDao.avgStar(42);
			double avg43=boardDao.avgStar(43);
			double avg44=boardDao.avgStar(44);
			double avg45=boardDao.avgStar(45);
			double avg46=boardDao.avgStar(46);
			double avg47=boardDao.avgStar(47);
			
			int sum41=boardDao.countLikes(41);
			int sum42=boardDao.countLikes(42);
			int sum43=boardDao.countLikes(43);
			int sum44=boardDao.countLikes(44);
			int sum45=boardDao.countLikes(45);
			int sum46=boardDao.countLikes(46);
			int sum47=boardDao.countLikes(47);
			
			request.setAttribute("avg41", avg41);
			request.setAttribute("avg42", avg42);
			request.setAttribute("avg43", avg43);
			request.setAttribute("avg44", avg44);
			request.setAttribute("avg45", avg45);
			request.setAttribute("avg46", avg46);
			request.setAttribute("avg47", avg47);
			
			request.setAttribute("sum41", sum41);
			request.setAttribute("sum42", sum42);
			request.setAttribute("sum43", sum43);
			request.setAttribute("sum44", sum44);
			request.setAttribute("sum45", sum45);
			request.setAttribute("sum46", sum46);
			request.setAttribute("sum47", sum47);
			
		if (mem_id!=null) {
			Map<String, Object> map41=new HashMap<String, Object>();
			map41.put("mem_id", mem_id);
			map41.put("v_movie", 41);
			BoardDTO article41=boardDao.getMyRate(map41);
			Map<String, Object> map42=new HashMap<String, Object>();
			map42.put("mem_id", mem_id);
			map42.put("v_movie", 42);
			BoardDTO article42=boardDao.getMyRate(map42);
			Map<String, Object> map43=new HashMap<String, Object>();
			map43.put("mem_id", mem_id);
			map43.put("v_movie", 43);
			BoardDTO article43=boardDao.getMyRate(map43);
			Map<String, Object> map44=new HashMap<String, Object>();
			map44.put("mem_id", mem_id);
			map44.put("v_movie", 44);
			BoardDTO article44=boardDao.getMyRate(map44);
			Map<String, Object> map45=new HashMap<String, Object>();
			map45.put("mem_id", mem_id);
			map45.put("v_movie", 45);
			BoardDTO article45=boardDao.getMyRate(map45);
			Map<String, Object> map46=new HashMap<String, Object>();
			map46.put("mem_id", mem_id);
			map46.put("v_movie", 46);
			BoardDTO article46=boardDao.getMyRate(map46);
			Map<String, Object> map47=new HashMap<String, Object>();
			map47.put("mem_id", mem_id);
			map47.put("v_movie", 47);
			BoardDTO article47=boardDao.getMyRate(map47);
			
			request.setAttribute("article41", article41);
			request.setAttribute("article42", article42);
			request.setAttribute("article43", article43);
			request.setAttribute("article44", article44);
			request.setAttribute("article45", article45);
			request.setAttribute("article46", article46);
			request.setAttribute("article47", article47);
		}
		return "/board/ghibli/G_Rate";
	}
	
	//2. 에러메시지 출력
	@ModelAttribute("command")
	public BoardDTO forBacking() {
		System.out.println("forBacking() 호출됨");
		return new BoardDTO();
	}
	
	//3. 입력
	@RequestMapping(value="/G_Rate.do", method=RequestMethod.POST)
	public String submit(@ModelAttribute("command") BoardDTO com,
						 BindingResult result,
						 HttpServletRequest request) {
		
		if (log.isDebugEnabled()) {
			System.out.println("/G_Rate.do 요청중");
			log.debug("BoardDTO : "+com);
		}
		
		HttpSession session=request.getSession();
		String mem_id=(String)session.getAttribute("idKey");
		System.out.println("G_RateController submit()의 mem_id=>"+mem_id);
		
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
		
		return "redirect:/G_Rate.do";
	}
	
	
	
	
	
	
}

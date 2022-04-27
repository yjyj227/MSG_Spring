package com.board.ring.controller;

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
import com.board.ring.dao.RBoardDao;
import com.board.ring.domain.RBoardDTO;

@Component
@Controller
public class R_RateController {

	private Logger log=Logger.getLogger(this.getClass());
	
	@Autowired
	private RBoardDao rBoardDao;
	@Autowired
	private BoardDao boardDao;
	
	//1. 별점 폼으로 이동
	@RequestMapping(value="/R_Rate.do", method=RequestMethod.GET)
	public String form(HttpServletRequest request) {
		System.out.println("초기화 form() 호출됨");
		
		HttpSession session=request.getSession();
		String mem_id=(String)session.getAttribute("idKey");
		//String mem_id=request.getParameter("mem_id");
		System.out.println("R_RateController form()의 mem_id=>"+mem_id);
		
		
			double avg51=boardDao.avgStar(51);
			double avg52=boardDao.avgStar(52);
			double avg53=boardDao.avgStar(53);
			double avg54=boardDao.avgStar(54);
			double avg55=boardDao.avgStar(55);
			double avg56=boardDao.avgStar(56);
			double avg57=boardDao.avgStar(57);
			double avg58=boardDao.avgStar(58);
			
			int sum51=boardDao.countLikes(51);
			int sum52=boardDao.countLikes(52);
			int sum53=boardDao.countLikes(53);
			int sum54=boardDao.countLikes(54);
			int sum55=boardDao.countLikes(55);
			int sum56=boardDao.countLikes(56);
			int sum57=boardDao.countLikes(57);
			int sum58=boardDao.countLikes(58);
			
			request.setAttribute("avg51", avg51);
			request.setAttribute("avg52", avg52);
			request.setAttribute("avg53", avg53);
			request.setAttribute("avg54", avg54);
			request.setAttribute("avg55", avg55);
			request.setAttribute("avg56", avg56);
			request.setAttribute("avg57", avg57);
			request.setAttribute("avg58", avg58);
			
			request.setAttribute("sum51", sum51);
			request.setAttribute("sum52", sum52);
			request.setAttribute("sum53", sum53);
			request.setAttribute("sum54", sum54);
			request.setAttribute("sum55", sum55);
			request.setAttribute("sum56", sum56);
			request.setAttribute("sum57", sum57);
			request.setAttribute("sum58", sum58);
			
		if (mem_id!=null) {
			Map<String, Object> map51=new HashMap<String, Object>();
			map51.put("mem_id", mem_id);
			map51.put("v_movie", 51);
			BoardDTO article51=boardDao.getMyRate(map51);
			Map<String, Object> map52=new HashMap<String, Object>();
			map52.put("mem_id", mem_id);
			map52.put("v_movie", 52);
			BoardDTO article52=boardDao.getMyRate(map52);
			Map<String, Object> map53=new HashMap<String, Object>();
			map53.put("mem_id", mem_id);
			map53.put("v_movie", 53);
			BoardDTO article53=boardDao.getMyRate(map53);
			Map<String, Object> map54=new HashMap<String, Object>();
			map54.put("mem_id", mem_id);
			map54.put("v_movie", 54);
			BoardDTO article54=boardDao.getMyRate(map54);
			Map<String, Object> map55=new HashMap<String, Object>();
			map55.put("mem_id", mem_id);
			map55.put("v_movie", 55);
			BoardDTO article55=boardDao.getMyRate(map55);
			Map<String, Object> map56=new HashMap<String, Object>();
			map56.put("mem_id", mem_id);
			map56.put("v_movie", 56);
			BoardDTO article56=boardDao.getMyRate(map56);
			Map<String, Object> map57=new HashMap<String, Object>();
			map57.put("mem_id", mem_id);
			map57.put("v_movie", 57);
			BoardDTO article57=boardDao.getMyRate(map57);
			Map<String, Object> map58=new HashMap<String, Object>();
			map58.put("mem_id", mem_id);
			map58.put("v_movie", 58);
			BoardDTO article58=boardDao.getMyRate(map58);
			
			request.setAttribute("article51", article51);
			request.setAttribute("article52", article52);
			request.setAttribute("article53", article53);
			request.setAttribute("article54", article54);
			request.setAttribute("article55", article55);
			request.setAttribute("article56", article56);
			request.setAttribute("article57", article57);
			request.setAttribute("article58", article58);
		}
		return "/board/ring/R_Rate";
	}
	
	//2. 에러메시지 출력
	@ModelAttribute("command")
	public BoardDTO forBacking() {
		System.out.println("forBacking() 호출됨");
		return new BoardDTO();
	}
	
	//3. 입력
	@RequestMapping(value="/R_Rate.do", method=RequestMethod.POST)
	public String submit(@ModelAttribute("command") BoardDTO com,
						 BindingResult result,
						 HttpServletRequest request) {
		
		if (log.isDebugEnabled()) {
			System.out.println("/R_Rate.do 요청중");
			log.debug("BoardDTO : "+com);
		}
		
		HttpSession session=request.getSession();
		String mem_id=(String)session.getAttribute("idKey");
		System.out.println("R_RateController submit()의 mem_id=>"+mem_id);
		
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
		
		return "redirect:/R_Rate.do";
	}
	
	
	
	
	
	
}

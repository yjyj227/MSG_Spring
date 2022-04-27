package com.board.zzang.controller;

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
import com.board.zzang.dao.ZBoardDao;
import com.board.zzang.domain.ZBoardDTO;

@Component
@Controller
public class Z_RateController {

	private Logger log=Logger.getLogger(this.getClass());
	
	@Autowired
	private ZBoardDao zBoardDao;
	@Autowired
	private BoardDao boardDao;
	
	//1. 별점 폼으로 이동
	@RequestMapping(value="/Z_Rate.do", method=RequestMethod.GET)
	public String form(HttpServletRequest request) {
		System.out.println("초기화 form() 호출됨");
		
		HttpSession session=request.getSession();
		String mem_id=(String)session.getAttribute("idKey");
		//String mem_id=request.getParameter("mem_id");
		System.out.println("Z_RateController form()의 mem_id=>"+mem_id);
		
		
			double avg21=boardDao.avgStar(21);
			double avg22=boardDao.avgStar(22);
			double avg23=boardDao.avgStar(23);
			double avg24=boardDao.avgStar(24);
			double avg25=boardDao.avgStar(25);
			double avg26=boardDao.avgStar(26);
			double avg27=boardDao.avgStar(27);
			double avg28=boardDao.avgStar(28);
			
			int sum21=boardDao.countLikes(21);
			int sum22=boardDao.countLikes(22);
			int sum23=boardDao.countLikes(23);
			int sum24=boardDao.countLikes(24);
			int sum25=boardDao.countLikes(25);
			int sum26=boardDao.countLikes(26);
			int sum27=boardDao.countLikes(27);
			int sum28=boardDao.countLikes(28);
			
			request.setAttribute("avg21", avg21);
			request.setAttribute("avg22", avg22);
			request.setAttribute("avg23", avg23);
			request.setAttribute("avg24", avg24);
			request.setAttribute("avg25", avg25);
			request.setAttribute("avg26", avg26);
			request.setAttribute("avg27", avg27);
			request.setAttribute("avg28", avg28);
			
			request.setAttribute("sum21", sum21);
			request.setAttribute("sum22", sum22);
			request.setAttribute("sum23", sum23);
			request.setAttribute("sum24", sum24);
			request.setAttribute("sum25", sum25);
			request.setAttribute("sum26", sum26);
			request.setAttribute("sum27", sum27);
			request.setAttribute("sum28", sum28);
			
		if (mem_id!=null) {
			Map<String, Object> map21=new HashMap<String, Object>();
			map21.put("mem_id", mem_id);
			map21.put("v_movie", 21);
			BoardDTO article21=boardDao.getMyRate(map21);
			Map<String, Object> map22=new HashMap<String, Object>();
			map22.put("mem_id", mem_id);
			map22.put("v_movie", 22);
			BoardDTO article22=boardDao.getMyRate(map22);
			Map<String, Object> map23=new HashMap<String, Object>();
			map23.put("mem_id", mem_id);
			map23.put("v_movie", 23);
			BoardDTO article23=boardDao.getMyRate(map23);
			Map<String, Object> map24=new HashMap<String, Object>();
			map24.put("mem_id", mem_id);
			map24.put("v_movie", 24);
			BoardDTO article24=boardDao.getMyRate(map24);
			Map<String, Object> map25=new HashMap<String, Object>();
			map25.put("mem_id", mem_id);
			map25.put("v_movie", 25);
			BoardDTO article25=boardDao.getMyRate(map25);
			Map<String, Object> map26=new HashMap<String, Object>();
			map26.put("mem_id", mem_id);
			map26.put("v_movie", 26);
			BoardDTO article26=boardDao.getMyRate(map26);
			Map<String, Object> map27=new HashMap<String, Object>();
			map27.put("mem_id", mem_id);
			map27.put("v_movie", 27);
			BoardDTO article27=boardDao.getMyRate(map27);
			Map<String, Object> map28=new HashMap<String, Object>();
			map28.put("mem_id", mem_id);
			map28.put("v_movie", 28);
			BoardDTO article28=boardDao.getMyRate(map28);
			
			request.setAttribute("article21", article21);
			request.setAttribute("article22", article22);
			request.setAttribute("article23", article23);
			request.setAttribute("article24", article24);
			request.setAttribute("article25", article25);
			request.setAttribute("article26", article26);
			request.setAttribute("article27", article27);
			request.setAttribute("article28", article28);
		}
		return "/board/zzang/Z_Rate";
	}
	
	//2. 에러메시지 출력
	@ModelAttribute("command")
	public BoardDTO forBacking() {
		System.out.println("forBacking() 호출됨");
		return new BoardDTO();
	}
	
	//3. 입력
	@RequestMapping(value="/Z_Rate.do", method=RequestMethod.POST)
	public String submit(@ModelAttribute("command") BoardDTO com,
						 BindingResult result,
						 HttpServletRequest request) {
		
		if (log.isDebugEnabled()) {
			System.out.println("/Z_Rate.do 요청중");
			log.debug("BoardDTO : "+com);
		}
		
		HttpSession session=request.getSession();
		String mem_id=(String)session.getAttribute("idKey");
		System.out.println("Z_RateController submit()의 mem_id=>"+mem_id);
		
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
		
		return "redirect:/Z_Rate.do";
	}
	
	
	
	
	
	
}

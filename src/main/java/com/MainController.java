package com;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.board.util.PagingUtil;

@Component
@Controller
public class MainController {

	private Logger log=Logger.getLogger(this.getClass());
	
	@RequestMapping("/Main.do")
	public ModelAndView process(HttpServletRequest request) {
		
		request.setAttribute("check", request.getParameter("check"));
		request.setAttribute("idKey", request.getParameter("idKey"));
		request.setAttribute("pwKey", request.getParameter("pwKey"));
		request.setAttribute("mem_nickname", request.getParameter("mem_nickname"));
		request.setAttribute("mem_point", request.getParameter("mem_point"));
		request.setAttribute("mem_grade", request.getParameter("mem_grade"));
		
		
		ModelAndView mav=new ModelAndView("Main");
		return mav;
	}
}

package com.member.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Component
@Controller
public class AgreementController {
	
	private Logger log=Logger.getLogger(this.getClass());
	
	@RequestMapping("/Agreement.do")
	public ModelAndView process() {
		return new ModelAndView("/member/Agreement");
	}
}

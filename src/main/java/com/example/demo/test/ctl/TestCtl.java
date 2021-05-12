package com.example.demo.test.ctl;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class TestCtl {
	
	@RequestMapping(value = "/home")
	public ModelAndView home() {
		ModelAndView mav = new ModelAndView("home");
		return mav;
	}
}

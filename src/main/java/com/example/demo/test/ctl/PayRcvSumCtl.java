package com.example.demo.test.ctl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.test.svc.PayRcvSumSvc;
import com.example.demo.test.vo.PayRcvSumVo;

@RestController
@RequestMapping("/payRcvSum")
public class PayRcvSumCtl {

	@Autowired
	PayRcvSumSvc payRcvSumSvc;
	
	@RequestMapping("/ini")
	public ModelAndView ini() throws Exception{
		ModelAndView mav = new ModelAndView("payRcvSum");
		return mav;
	}
	
	@RequestMapping("/lst")
	public ModelAndView lst(PayRcvSumVo payRcvSumVo) throws Exception{
		
		ModelAndView mav = new ModelAndView("payRcvSum");
		
		List<PayRcvSumVo> rstLst = payRcvSumSvc.lst(payRcvSumVo);
		mav.addObject("rstLst", rstLst);
		return mav;
	}
}

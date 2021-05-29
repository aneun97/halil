package com.example.demo.test.ctl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.test.svc.TestSvc;
import com.example.demo.test.vo.TestVo;


@RestController
@RequestMapping("/home")
public class TestCtl {
	
	@Autowired
	TestSvc testSvc;
	
	@RequestMapping("/ini")
	public ModelAndView ini() {
		ModelAndView mav = new ModelAndView("home");
		return mav;
	}
	
	@RequestMapping("/lstHalil")
	public String lstHalil(Model model) {
		return null;
	}
	
	@RequestMapping("/lstCash")
	public List<TestVo> lstCash(HttpServletRequest req) {
		TestVo testVo = new TestVo();
		testVo.setST_DT(req.getParameter("startDate"));
		testVo.setED_DT(req.getParameter("endDate"));
		List<TestVo> chkList = testSvc.lstMonthAsset(testVo);
//		for(TestVo chkVo:chkList) {
//			System.out.println("=======================================");
//			System.out.println("날짜::"+chkVo.getSTD_DT());
//			System.out.println("금액::"+chkVo.getDEBIT());
//			System.out.println("=======================================");
//		}
		return chkList;
	}
}

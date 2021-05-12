package com.example.demo.test.ctl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.test.svc.EvntAssetSelSvc;
import com.example.demo.test.vo.EvntAssetSelVo;

@RestController
@RequestMapping(value = "/evntAssetSel")
public class EvntAssetSelCtl {

	@Autowired
	EvntAssetSelSvc evntAssetSelSvc;
	
	@RequestMapping(value = "/init")
	public ModelAndView init() {
		ModelAndView mav = new ModelAndView("evntAssetSel");
		return mav;
	}
	
	@RequestMapping(value = "/list")
	public ModelAndView list(EvntAssetSelVo evntAssetSelVo) {
		ModelAndView mav = new ModelAndView("evntAssetSel");
		List<EvntAssetSelVo> evntAssetSelLs = evntAssetSelSvc.list(evntAssetSelVo);
		mav.addObject("LIST", evntAssetSelLs);
		mav.addObject("FROM_DT", evntAssetSelVo.getFROM_DT());
		mav.addObject("TO_DT", evntAssetSelVo.getTO_DT());
		return mav;
	}
}

package com.example.demo.test.ctl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.test.svc.EvntAssetSelSvc;
import com.example.demo.test.vo.EvntAssetSelVo;
import com.example.demo.test.vo.InsEvntAssetVo;

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

	@RequestMapping("/lst")
	public List<EvntAssetSelVo> lst(EvntAssetSelVo evntAssetSelVo) {
		List<EvntAssetSelVo> evntAssetSelLs = evntAssetSelSvc.lst(evntAssetSelVo);
		return evntAssetSelLs;
	}
	
	@RequestMapping("/viw")
	public ModelAndView viw(InsEvntAssetVo insEvntAssetVo) throws Exception{
		ModelAndView mav = new ModelAndView("insEvntAsset");
		mav.addObject("EVNT_NO", insEvntAssetVo.getEVNT_NO());
		return mav;
	}

	@RequestMapping("/ins")
	public void ins(EvntAssetSelVo evntAssetSelVo) {
		evntAssetSelSvc.ins(evntAssetSelVo);
	}
	
}

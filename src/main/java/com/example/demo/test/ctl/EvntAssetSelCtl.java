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

	@RequestMapping("/list")
	public List<EvntAssetSelVo> list(EvntAssetSelVo evntAssetSelVo) {
		List<EvntAssetSelVo> evntAssetSelLs = evntAssetSelSvc.list(evntAssetSelVo);
		return evntAssetSelLs;
	}
}

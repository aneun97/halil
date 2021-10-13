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

	@RequestMapping("/lst")
	public List<EvntAssetSelVo> lst(EvntAssetSelVo evntAssetSelVo) {
		List<EvntAssetSelVo> evntAssetSelLs = evntAssetSelSvc.lst(evntAssetSelVo);
		return evntAssetSelLs;
	}
	
	@RequestMapping("/viw")
	public EvntAssetSelVo viw(EvntAssetSelVo evntAssetSelVo) {
		EvntAssetSelVo resultVo = evntAssetSelSvc.viw(evntAssetSelVo);
		return resultVo;		
	}

	@RequestMapping("/ins")
	public void ins(EvntAssetSelVo evntAssetSelVo) throws Exception {
		evntAssetSelSvc.ins(evntAssetSelVo);
	}

	@RequestMapping("/upd")
	public void upd(EvntAssetSelVo evntAssetSelVo) throws Exception {
		evntAssetSelSvc.upd(evntAssetSelVo);
	}

	@RequestMapping("/del")
	public void del(EvntAssetSelVo evntAssetSelVo) throws Exception {
		evntAssetSelSvc.del(evntAssetSelVo);
	}
	
}

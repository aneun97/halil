package com.example.demo.test.ctl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.test.svc.EvntAssetSelSvc;
import com.example.demo.test.svc.InsEvntAssetSvc;
import com.example.demo.test.vo.EvntAssetSelVo;
import com.example.demo.test.vo.InsEvntAssetVo;

@RestController
@RequestMapping(value = "/evntAssetSel")
public class EvntAssetSelCtl {

	@Autowired
	EvntAssetSelSvc evntAssetSelSvc;
	@Autowired
	InsEvntAssetSvc insEvntAssetSvc;
	
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
	
	@RequestMapping("/viw")
	public ModelAndView viw(InsEvntAssetVo insEvntAssetVo) throws Exception{
		ModelAndView mav = new ModelAndView("insEvntAsset");
		mav.addObject("EVNT_NO", insEvntAssetVo.getEVNT_NO());
		return mav;
	}
	
	@RequestMapping("/ins")
	public ModelAndView ins(InsEvntAssetVo insEvntAssetVo) throws Exception{
		ModelAndView mav = new ModelAndView("insEvntAsset");
		return mav;
	}
	
	@RequestMapping("/lstEvnt")
	public List<InsEvntAssetVo> lstEvnt(HttpServletRequest req) {
		List<InsEvntAssetVo> evntLs = insEvntAssetSvc.lstEvnt();
		return evntLs;
	}
	@RequestMapping("/lstAsset")
	public List<InsEvntAssetVo> lstAsset(HttpServletRequest req) {
		List<InsEvntAssetVo> assetLs = insEvntAssetSvc.lstAsset();
		return assetLs;
	}
	@RequestMapping("/lstEvntTycd")
	public List<InsEvntAssetVo> lstEvntTycd(HttpServletRequest req) {
		List<InsEvntAssetVo> evntHclsLs = insEvntAssetSvc.lstEvntTycd();
		return evntHclsLs;
	}
}

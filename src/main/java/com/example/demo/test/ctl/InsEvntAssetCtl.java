package com.example.demo.test.ctl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.test.svc.InsEvntAssetSvc;
import com.example.demo.test.vo.InsEvntAssetVo;

@RestController
@RequestMapping("/insEvntAsset")
public class InsEvntAssetCtl {

	@Autowired
	InsEvntAssetSvc insEvntAssetSvc;
	
	@RequestMapping("/init")
	public ModelAndView init() throws Exception{
		ModelAndView mav = new ModelAndView("insEvntAsset");
				
		List<InsEvntAssetVo> evntLs = insEvntAssetSvc.lstEvnt();
		List<InsEvntAssetVo> assetLs = insEvntAssetSvc.lstAsset();
		mav.addObject("EVNT", evntLs);
		mav.addObject("ASSET", assetLs);
		return mav;
	}
	
	@RequestMapping("/isrt")
	public ModelAndView isrt(InsEvntAssetVo insEvntAssetVo) throws Exception{
		insEvntAssetSvc.isrt(insEvntAssetVo);
		ModelAndView mav = new ModelAndView("insEvntAsset");
		
		List<InsEvntAssetVo> evntLs = insEvntAssetSvc.lstEvnt();
		List<InsEvntAssetVo> assetLs = insEvntAssetSvc.lstAsset();
		mav.addObject("EVNT", evntLs);
		mav.addObject("ASSET", assetLs);
		return mav;
	}
}

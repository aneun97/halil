package com.example.demo.test.ctl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

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

	@RequestMapping("/isrt")
	public void isrt(InsEvntAssetVo insEvntAssetVo) {
		insEvntAssetSvc.isrt(insEvntAssetVo);
	}
	

}

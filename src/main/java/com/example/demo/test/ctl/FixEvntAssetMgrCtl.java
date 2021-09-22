package com.example.demo.test.ctl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.test.svc.FixEvntAssetMgrSvc;
import com.example.demo.test.vo.FixEvntAssetMgrVo;

@RestController
@RequestMapping(value = "/fixEvntAssetMgr")
public class FixEvntAssetMgrCtl {

	@Autowired
	FixEvntAssetMgrSvc fixEvntAssetMgrSvc;
	
	@RequestMapping(value = "/ini")
	public ModelAndView init() {
		ModelAndView mav = new ModelAndView("fixEvntAssetMgr");
		return mav;
	}

	@RequestMapping("/lst")
	public List<FixEvntAssetMgrVo> lst(FixEvntAssetMgrVo fixEvntAssetMgrVo) {
		List<FixEvntAssetMgrVo> fixEvntAssetMgrLs = fixEvntAssetMgrSvc.lst(fixEvntAssetMgrVo);
		return fixEvntAssetMgrLs;
	}

	@RequestMapping("/ins")
	public void ins(FixEvntAssetMgrVo fixEvntAssetMgrVo) throws Exception {
		fixEvntAssetMgrSvc.ins(fixEvntAssetMgrVo);
	}
	
}

package com.example.demo.test.ctl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.test.svc.EvntAssetStatSvc;
import com.example.demo.test.vo.EvntAssetStatVo;


@RestController
@RequestMapping("/evntAssetStat")
public class EvntAssetStatCtl {
	
	@Autowired
	EvntAssetStatSvc evntAssetStatSvc;
	
	@RequestMapping("/ini")
	public ModelAndView ini() {
		ModelAndView mav = new ModelAndView("evntAssetStat");
		return mav;
	}

	@RequestMapping("/lst")
	public List<EvntAssetStatVo> lst(EvntAssetStatVo evntAssetStatVo) {
		List<EvntAssetStatVo> evntAssetStatLs = evntAssetStatSvc.lst(evntAssetStatVo);
		return evntAssetStatLs;
	}
}

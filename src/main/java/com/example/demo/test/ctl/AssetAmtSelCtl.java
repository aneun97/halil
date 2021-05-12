package com.example.demo.test.ctl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.test.svc.AssetAmtSelSvc;
import com.example.demo.test.vo.AssetAmtSelVo;

@RestController
@RequestMapping("/assetAmtSel")
public class AssetAmtSelCtl {

	@Autowired
	AssetAmtSelSvc assetAmtSelSvc;
	
	@RequestMapping("/init")
	public ModelAndView init() throws Exception{
		ModelAndView mav = new ModelAndView("assetAmtSel");
		
		return mav;
	}
	
	@RequestMapping("/list")
	public ModelAndView list(AssetAmtSelVo assetAmtSelVo) throws Exception{
		ModelAndView mav = new ModelAndView("assetAmtSel");
		List<AssetAmtSelVo> assetAmtSelLs = assetAmtSelSvc.viwTotal(assetAmtSelVo);
		mav.addObject("LIST", assetAmtSelLs);
		return mav;
	}
}

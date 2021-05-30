package com.example.demo.test.ctl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
	public List<AssetAmtSelVo> list(HttpServletRequest req) {
		AssetAmtSelVo assetAmtSelVo = new AssetAmtSelVo();
		assetAmtSelVo.setWK_DT(req.getParameter("WK_DT"));
		List<AssetAmtSelVo> chkList = assetAmtSelSvc.viwTotal(assetAmtSelVo);
//		for(TestVo chkVo:chkList) {
//			System.out.println("=======================================");
//			System.out.println("날짜::"+chkVo.getSTD_DT());
//			System.out.println("금액::"+chkVo.getDEBIT());
//			System.out.println("=======================================");
//		}
		return chkList;
	}
}

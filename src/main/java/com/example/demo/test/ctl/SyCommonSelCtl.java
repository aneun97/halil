package com.example.demo.test.ctl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.test.svc.SyCommonSelSvc;
import com.example.demo.test.vo.SyCommonSelVo;

@RestController
@RequestMapping("/syCommonsel")
public class SyCommonSelCtl {

	@Autowired
	SyCommonSelSvc syCommonSelSvc;
	
	@RequestMapping("/lstEvnt")
	public List<SyCommonSelVo> lstEvnt(HttpServletRequest req) {
		List<SyCommonSelVo> evntLs = syCommonSelSvc.lstEvnt();
		return evntLs;
	}
	@RequestMapping("/lstAsset")
	public List<SyCommonSelVo> lstAsset(SyCommonSelVo syCommonSelVo) {
		List<SyCommonSelVo> assetLs = syCommonSelSvc.lstAsset(syCommonSelVo);
		return assetLs;
	}
	@RequestMapping("/lstAssetKind")
	public List<SyCommonSelVo> lstAssetKind(HttpServletRequest req) {
		List<SyCommonSelVo> assetLs = syCommonSelSvc.lstAssetKind();
		return assetLs;
	}
	@RequestMapping("/lstEvntTycd")
	public List<SyCommonSelVo> lstEvntTycd(HttpServletRequest req) {
		List<SyCommonSelVo> evntHclsLs = syCommonSelSvc.lstEvntTycd();
		return evntHclsLs;
	}	

}

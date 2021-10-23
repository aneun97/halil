package com.example.demo.test.svc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.test.dao.SyCommonSelDao;
import com.example.demo.test.vo.SyCommonSelVo;

@Service
public class SyCommonSelSvc {

	@Autowired
	public SyCommonSelDao syCommonSelDao;

	public List<SyCommonSelVo> lstEvnt(){
		return syCommonSelDao.lstEvnt();
	}

	public List<SyCommonSelVo> lstAsset(SyCommonSelVo syCommonSelVo){
		return syCommonSelDao.lstAsset(syCommonSelVo);
	}

	public List<SyCommonSelVo> lstAssetKind(){
		return syCommonSelDao.lstAssetKind();
	}

	public List<SyCommonSelVo> lstEvntHcls(){
		return syCommonSelDao.lstEvntHcls();
	}

	public List<SyCommonSelVo> lstEvntLcls(SyCommonSelVo syCommonSelVo){
		return syCommonSelDao.lstEvntLcls(syCommonSelVo);
	}
}

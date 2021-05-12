package com.example.demo.test.svc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.test.dao.EvntAssetSelDao;
import com.example.demo.test.vo.EvntAssetSelVo;

@Service
public class EvntAssetSelSvc {

	@Autowired
	public EvntAssetSelDao evntAssetSelDao;
	
	public List<EvntAssetSelVo> list(EvntAssetSelVo evntAssetSelVo){
		return evntAssetSelDao.list(evntAssetSelVo);
	}
}

package com.example.demo.test.svc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.test.dao.EvntAssetStatDao;
import com.example.demo.test.vo.EvntAssetStatVo;


@Service
public class EvntAssetStatSvc {

	@Autowired
	public EvntAssetStatDao evntAssetStatDao;
	
	public List<EvntAssetStatVo> lst(EvntAssetStatVo evntAssetStatVo){
		return evntAssetStatDao.lst(evntAssetStatVo);
	}

}

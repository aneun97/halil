package com.example.demo.test.svc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.test.dao.AssetAmtSelDao;
import com.example.demo.test.vo.AssetAmtSelVo;

@Service
public class AssetAmtSelSvc {

	@Autowired
	public AssetAmtSelDao assetAmtSelDao;
	
	public List<AssetAmtSelVo> viwTotal(AssetAmtSelVo assetAmtSelVo){
		return assetAmtSelDao.viwTotal(assetAmtSelVo);
	}

	/**
	 *  자산 신규 입력
	**/
	public void ins(AssetAmtSelVo assetAmtSelVo){
		assetAmtSelDao.ins(assetAmtSelVo);	
	}
}

package com.example.demo.test.svc;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.example.demo.test.biz.MastAssetBiz;
import com.example.demo.test.dao.EvntAssetSelDao;
import com.example.demo.test.vo.EvntAssetSelVo;
import com.example.demo.test.vo.MastAssetVo;

@Service
public class EvntAssetSelSvc {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	public EvntAssetSelDao evntAssetSelDao;

	@Autowired
	public MastAssetBiz mastAssetBiz;
	
	public List<EvntAssetSelVo> lst(EvntAssetSelVo evntAssetSelVo){
		return evntAssetSelDao.lst(evntAssetSelVo);
	}

	public void ins(EvntAssetSelVo evntAssetSelVo) {


		//화면에 보내줄때 신용카드면 결제일같이보내줘서 이미 받아옴
		// 이벤트내역 입력
		logger.info("이벤트내역 입력 시작={WK_DT::"+evntAssetSelVo.getWK_DT()+", EVNT::"+evntAssetSelVo.getEVNT()+", PAY_ASSET::"+evntAssetSelVo.getPAY_ASSET()+", RCV_ASSET::"+evntAssetSelVo.getRCV_ASSET()+", AMT::"+evntAssetSelVo.getAMT()+"}");
		evntAssetSelVo.setCHK_YN("Y");
		evntAssetSelDao.ins(evntAssetSelVo);
		logger.info("이벤트내역 입력 완료");

		MastAssetVo oAsset = new MastAssetVo();
		oAsset.setWK_DT(evntAssetSelVo.getWK_DT());
		oAsset.setAMT(evntAssetSelVo.getAMT());
		// 출금처가 있으면 출금처 원장 수정
		if (!ObjectUtils.isEmpty(evntAssetSelVo.getPAY_ASSET())) {
			oAsset.setASSET(evntAssetSelVo.getPAY_ASSET());			
			mastAssetBiz.recMastAsset(oAsset, true);
		}

		// 입금처가 있으면 입금처 원장 수정
		if (!ObjectUtils.isEmpty(evntAssetSelVo.getRCV_ASSET())) {
			oAsset.setASSET(evntAssetSelVo.getRCV_ASSET());			
			mastAssetBiz.recMastAsset(oAsset, false);
			
		}		
	}
}

package com.example.demo.job;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import com.example.demo.test.biz.MastAssetBiz;
import com.example.demo.test.dao.EvntAssetSelDao;
import com.example.demo.test.vo.EvntAssetSelVo;
import com.example.demo.test.vo.MastAssetVo;

@Component
public class EvntAssetChkJob {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	public EvntAssetSelDao evntAssetSelDao;

	@Autowired
	public MastAssetBiz mastAssetBiz;
	// 초 분 시 일 월 요일
	@Scheduled(cron = "0 0 0 * * ?")
	public void EvntAssetChk() throws Exception {
		logger.info("스케쥴러 작동");
		
		// 오늘 이전 chk_yn이 n 상태인 경우 d로 변경
		// 1. 조회 
		EvntAssetSelVo condVo = new EvntAssetSelVo();
		List<EvntAssetSelVo> evntAssetSelVoLs = evntAssetSelDao.lstUnChk(condVo);
		// 2. 변경 및 원장수정
		MastAssetVo mastAssetVo = new MastAssetVo();
		for (EvntAssetSelVo evntAssetVo : evntAssetSelVoLs) {
			evntAssetVo.setCHK_YN("D");
			evntAssetSelDao.upd(evntAssetVo);
			
			mastAssetVo.setWK_DT(evntAssetVo.getWK_DT());
			mastAssetVo.setAMT(evntAssetVo.getAMT());
			// 출금처가 있으면 출금처 원장 수정
			if (!ObjectUtils.isEmpty(evntAssetVo.getPAY_ASSET())) {
				mastAssetVo.setASSET(evntAssetVo.getPAY_ASSET());			
				mastAssetBiz.rbkMastAsset(mastAssetVo, true);
			}
			// 입금처가 있으면 입금처 원장 수정
			if (!ObjectUtils.isEmpty(evntAssetVo.getRCV_ASSET())) {
				mastAssetVo.setASSET(evntAssetVo.getRCV_ASSET());			
				mastAssetBiz.rbkMastAsset(mastAssetVo, false);
			}		
			
		}
		
		
		
		
		
		// + 이벤트내역에서 체크상태 변경할수있도록
		// 이벤트내역 수정/삭제 하면 원장도 변경 - 수정은 금액, 입출금처 변경, 기타내역변경 가능// 이벤트종류 변경 불가
		// 원장 수정/삭제 가능 - 수정시 지출,수입으로 이벤트 발생시킴
		
		
		
		
		// 고정변동자산 목록 호출(번호,주기,일)
		// 주기가 1일인 경우 
		// 주기가 주1인 경우
		// 주기가 월1인 경우 - 자산변동 목록에서 오늘+1달 범위로 번호 검색 -> 있으면 넘어가고 없으면 셀렉트인서트 -> 원장 변동 호출
		
		// 오늘날짜 이전중 체크N인 경우 원장원복
		
		//3. 원장수정 클래스 분리
		//4. 고정자산 입출력화면
		//5. 이벤트에서 체크변경 가능
	}
}

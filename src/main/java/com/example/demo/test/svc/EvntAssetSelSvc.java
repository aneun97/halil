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
	
	public EvntAssetSelVo viw(EvntAssetSelVo evntAssetSelVo){
		return evntAssetSelDao.viw(evntAssetSelVo);
	}

	// 신규 이벤트 입력
	public void ins(EvntAssetSelVo evntAssetSelVo) throws Exception {


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
			mastAssetBiz.recMastAsset(oAsset, true, false);
		}

		// 입금처가 있으면 입금처 원장 수정
		if (!ObjectUtils.isEmpty(evntAssetSelVo.getRCV_ASSET())) {
			oAsset.setASSET(evntAssetSelVo.getRCV_ASSET());			
			mastAssetBiz.recMastAsset(oAsset, false, false);
			
		}		
	}

	// 이벤트 수정
	public void upd(EvntAssetSelVo evntAssetSelVo) throws Exception {
		
		// 업데이트 전 정보확인
		EvntAssetSelVo oldVo = new EvntAssetSelVo();
		oldVo = evntAssetSelDao.viw(evntAssetSelVo);
		
		if (!oldVo.getEVNT().equals(evntAssetSelVo.getEVNT())) {
			throw new Exception("이벤트는 변경 불가");
		}
		
		// 미확인, 딜레이건이 변경된다면 확인처리
		if (oldVo.getCHK_YN().equals("D") || oldVo.getCHK_YN().equals("N")) {
			evntAssetSelVo.setCHK_YN("Y");
		}
		
		// 업데이트
		evntAssetSelDao.upd(evntAssetSelVo);
		
		// 입금처 원장 수정
		if (!ObjectUtils.isEmpty(evntAssetSelVo.getRCV_ASSET())){
			// 입금처 원장 VO
			MastAssetVo rcvAssettVo = new MastAssetVo();

			// 원장이 없는 경우 생성
			if (oldVo.getCHK_YN().equals("D")) {
				// 2.신규 입금처 원장 변경
				rcvAssettVo.setWK_DT(oldVo.getWK_DT());
				rcvAssettVo.setASSET(oldVo.getRCV_ASSET());
				rcvAssettVo.setAMT(oldVo.getAMT());				
				mastAssetBiz.recMastAsset(rcvAssettVo, false, false);
			}
			
			// 입금처 변경된 경우
			if (!evntAssetSelVo.getRCV_ASSET().equals(oldVo.getRCV_ASSET())) {
				
				// 1.기존 입금처 원장 원상복구				
				rcvAssettVo.setWK_DT(oldVo.getWK_DT());
				rcvAssettVo.setASSET(oldVo.getRCV_ASSET());
				rcvAssettVo.setAMT(oldVo.getAMT());				
				mastAssetBiz.rbkMastAsset(rcvAssettVo, false);	
				
				// 2.신규 입금처 원장 변경
				rcvAssettVo.setWK_DT(evntAssetSelVo.getWK_DT());
				rcvAssettVo.setASSET(evntAssetSelVo.getRCV_ASSET());
				rcvAssettVo.setAMT(evntAssetSelVo.getAMT());				
				mastAssetBiz.recMastAsset(rcvAssettVo, false, false);
				// 충전이벤트는 따로 분리해서 처리
			}
			else {
				// 날짜 변경된 경우
				if (!evntAssetSelVo.getWK_DT().equals(oldVo.getWK_DT())) {
					// 1.날짜가 앞당겨진 경우
					// 기존일 원장에서 증가액 감소,전금액 증가(기존금액만큼 줄임)
					// 신규일 원장 생성 혹은 조정 
					// 신규일보다 크고 기존일보다 작은 날짜 원장에 전금액,현금액 증가
					
					// 2.날짜가 미뤄진 경우
					// 기존일자 원장에서 증가액 감소, 현금액 감소(기존금액만큼 줄입)
					// 신규일 원장 생성 혹은 조정
					// 기존일보다 크고 신규일보다 작은 날짜 원장에 전금액, 현금액 감소
				}
				
				// 금액 변경된 경우
				if (evntAssetSelVo.getAMT().compareTo(oldVo.getAMT()) != 0) {
					// 발생일 원장에서 증가액, 현금액 조정
					// 발생일 이후 원장에서 전금액, 현금액 조정
					rcvAssettVo.setWK_DT(evntAssetSelVo.getWK_DT());
					rcvAssettVo.setASSET(evntAssetSelVo.getRCV_ASSET());
					rcvAssettVo.setAMT(evntAssetSelVo.getAMT().subtract(oldVo.getAMT()));				
					mastAssetBiz.recMastAsset(rcvAssettVo, false, false);
				}
			}
		}
		
		
		// 출금처 원장 수정
		if (!ObjectUtils.isEmpty(evntAssetSelVo.getPAY_ASSET())){
			// 출금처 원장 VO
			MastAssetVo payAssettVo = new MastAssetVo();

			// 원장이 없는 경우 생성
			if (oldVo.getCHK_YN().equals("D")) {
				// 2.신규 입금처 원장 변경
				payAssettVo.setWK_DT(oldVo.getWK_DT());
				payAssettVo.setASSET(oldVo.getPAY_ASSET());
				payAssettVo.setAMT(oldVo.getAMT());				
				mastAssetBiz.recMastAsset(payAssettVo, true, false);
			}
			
			// 출금처 변경된 경우
			if (!evntAssetSelVo.getPAY_ASSET().equals(oldVo.getPAY_ASSET())) {
				// 1.기존 출금처 원장 원상복구				
				payAssettVo.setWK_DT(oldVo.getWK_DT());
				payAssettVo.setASSET(oldVo.getPAY_ASSET());
				payAssettVo.setAMT(oldVo.getAMT());				
				mastAssetBiz.rbkMastAsset(payAssettVo, true);	
				
				// 2.신규 출금처 원장 변경
				payAssettVo.setWK_DT(evntAssetSelVo.getWK_DT());
				payAssettVo.setASSET(evntAssetSelVo.getPAY_ASSET());
				payAssettVo.setAMT(evntAssetSelVo.getAMT());				
				mastAssetBiz.recMastAsset(payAssettVo, true, false);
			}
			else {
				// 날짜 변경된 경우
				if (!evntAssetSelVo.getWK_DT().equals(oldVo.getWK_DT())) {
					// 1.날짜가 앞당겨진 경우
					// 기존일 원장에서 증가액 감소,전금액 증가(기존금액만큼 줄임)
					// 신규일 원장 생성 혹은 조정 
					// 신규일보다 크고 기존일보다 작은 날짜 원장에 전금액,현금액 증가
					
					// 2.날짜가 미뤄진 경우
					// 기존일자 원장에서 증가액 감소, 현금액 감소(기존금액만큼 줄입)
					// 신규일 원장 생성 혹은 조정
					// 기존일보다 크고 신규일보다 작은 날짜 원장에 전금액, 현금액 감소
				}
				
				// 금액 변경된 경우
				if (evntAssetSelVo.getAMT().compareTo(oldVo.getAMT()) != 0) {
					// 발생일 원장에서 증가액, 현금액 조정
					// 발생일 이후 원장에서 전금액, 현금액 조정
					payAssettVo.setWK_DT(evntAssetSelVo.getWK_DT());
					payAssettVo.setASSET(evntAssetSelVo.getPAY_ASSET());
					payAssettVo.setAMT(evntAssetSelVo.getAMT().subtract(oldVo.getAMT()));				
					mastAssetBiz.recMastAsset(payAssettVo, true, false);
				}
			}
		}		
	}

	// 이벤트 삭제(논리적)
	public void del(EvntAssetSelVo evntAssetSelVo) throws Exception {

		// 업데이트 전 정보확인
		EvntAssetSelVo oldVo = new EvntAssetSelVo();
		oldVo = evntAssetSelDao.viw(evntAssetSelVo);
		
		// 확인여부를 취소로 변경
		evntAssetSelDao.del(evntAssetSelVo);

		// 이미 원장이 없는 경우가 아니면
		if (!oldVo.getCHK_YN().equals("D") && !oldVo.getCHK_YN().equals("R")) {
			// 입금처가 있으면 입금처 원장원복
			if (!ObjectUtils.isEmpty(evntAssetSelVo.getRCV_ASSET())){
				// 입금처 원장 VO
				MastAssetVo rcvAssettVo = new MastAssetVo();
				
				rcvAssettVo.setWK_DT(evntAssetSelVo.getWK_DT());
				rcvAssettVo.setASSET(evntAssetSelVo.getRCV_ASSET());
				rcvAssettVo.setAMT(evntAssetSelVo.getAMT());
				
				mastAssetBiz.rbkMastAsset(rcvAssettVo, false);			
			}
			// 출금처가 있으면 출금처 원장원복
			if (!ObjectUtils.isEmpty(evntAssetSelVo.getPAY_ASSET())){
				// 출금처 원장 VO
				MastAssetVo payAssettVo = new MastAssetVo();
				
				payAssettVo.setWK_DT(evntAssetSelVo.getWK_DT());
				payAssettVo.setASSET(evntAssetSelVo.getPAY_ASSET());
				payAssettVo.setAMT(evntAssetSelVo.getAMT());
				
				mastAssetBiz.rbkMastAsset(payAssettVo, true);			
			}
		}
		
	}
}

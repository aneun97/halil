package com.example.demo.test.biz;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.example.demo.test.dao.MastAssetDao;
import com.example.demo.test.svc.EvntAssetSelSvc;
import com.example.demo.test.vo.EvntAssetSelVo;
import com.example.demo.test.vo.MastAssetVo;


@Service
public class MastAssetBiz {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	public MastAssetDao mastAssetDao;
	
	@Autowired
	public EvntAssetSelSvc evntAssetSelSvc;
	
	// 자산변동입력 후 처리(원장 입력)
	public void recMastAsset(MastAssetVo oEvntAsset, Boolean isPay) {
		
		// 1. 자산원장체크
		MastAssetVo oMastAsset = mastAssetDao.viwMastAsset(oEvntAsset);
		
		// 2. 업데이트/인서트 구분 - 오늘날짜 원장이 이미 있는 경우
		if (oMastAsset.getWK_DT().equals(oMastAsset.getBEF_WK_DT())) {
			updMastAsset(oMastAsset, isPay);
		}
		else {
			insMastAsset(oMastAsset, isPay);
		}
			
		// 3.오늘 이후 원장 존재시 다음원장에 이전날짜 업데이트, 오늘 이후 원장 존재시 금액 증감 업데이트
		if (!ObjectUtils.isEmpty(oMastAsset.getAFT_WK_DT())) {
			logger.info("이벤트 날짜 이후 원장 수정");
			MastAssetVo oMastAssetAft = new MastAssetVo();
			oMastAssetAft.setASSET(oMastAsset.getASSET());
			oMastAssetAft.setWK_DT(oMastAsset.getAFT_WK_DT());
			oMastAssetAft.setBEF_DT(oMastAsset.getWK_DT());
			
			mastAssetDao.updMastAssetAft(oMastAssetAft);

			oMastAssetAft.setWK_DT(oMastAsset.getWK_DT());
			oMastAssetAft.setAMT(oMastAsset.getAMT());
			// 출금처 차대유형이 D면 금액감소로 C면 금액증가
			// 입금처 차대유형이 D면 금액증가로 C면 금액감소
			if (oMastAsset.getDC_TYCD().equals("D") && isPay
					|| oMastAsset.getDC_TYCD().equals("C") && !isPay
				) {
				mastAssetDao.updDecAssetAft(oMastAssetAft);
			}
			else if (oMastAsset.getDC_TYCD().equals("C") && isPay
					|| oMastAsset.getDC_TYCD().equals("D") && !isPay
					) {
				mastAssetDao.updIncAssetAft(oMastAssetAft);
			}
		}
		
		// 4. 층전관련 작업 - 마이너스잔액충전, 충전거래 발생
		
		// 충전자산 지출시
		if (!ObjectUtils.isEmpty(oMastAsset.getCHRG_YN())
			&& oMastAsset.getCHRG_YN().equals("Y")
			&& isPay) {

			BigDecimal nAmt = oMastAsset.getBEF_AMT().subtract(oMastAsset.getAMT());			// 충전할 금액
			
			// 금액 마이너스 된 경우 마이너스 금액만 충전됨
			if(nAmt.compareTo(BigDecimal.ZERO) < 0) {
				EvntAssetSelVo oChrgEvnt = new EvntAssetSelVo();
				oChrgEvnt.setWK_DT(oMastAsset.getWK_DT());
				oChrgEvnt.setEVNT("E0010");
				oChrgEvnt.setPAY_ASSET(oMastAsset.getCHRG_ASSET());
				oChrgEvnt.setRCV_ASSET(oMastAsset.getASSET());
				oChrgEvnt.setAMT(nAmt.negate());

				evntAssetSelSvc.ins(oChrgEvnt);
			}
			
			// 충전조건 터진 경우 충전거래 발생시킴
			else if (nAmt.compareTo(oMastAsset.getCHRG_COND_AMT()) < 0) {
				EvntAssetSelVo oChrgEvnt = new EvntAssetSelVo();
				oChrgEvnt.setWK_DT(oMastAsset.getWK_DT());
				oChrgEvnt.setEVNT("E0010");
				oChrgEvnt.setPAY_ASSET(oMastAsset.getCHRG_ASSET());
				oChrgEvnt.setRCV_ASSET(oMastAsset.getASSET());
				oChrgEvnt.setAMT(oMastAsset.getCHRG_AMT());

				evntAssetSelSvc.ins(oChrgEvnt);
			}
		}
				
	}
	
	// 자산원장 신규입력
	public void insMastAsset(MastAssetVo oMastAsset, Boolean isPay) {
		
		MastAssetVo oMastAssetIns = new MastAssetVo();

		String sWkdt = oMastAsset.getWK_DT();			// 원장일
		BigDecimal nAmt = oMastAsset.getAMT();			// 변경금액
		String sAsset = oMastAsset.getASSET();		// 원장자산

		oMastAssetIns.setWK_DT(sWkdt);
		oMastAssetIns.setASSET(sAsset);
		
		if (ObjectUtils.isEmpty(oMastAsset.getBEF_WK_DT())) {
			oMastAssetIns.setBEF_AMT(BigDecimal.ZERO);
			oMastAssetIns.setBEF_DT("00000000");
		}
		else {
			oMastAssetIns.setBEF_DT(oMastAsset.getBEF_WK_DT());
			oMastAssetIns.setBEF_AMT(oMastAsset.getBEF_AMT());
			
		}

		// 출금처 차대유형이 D면 금액감소로 C면 금액증가
		// 입금처 차대유형이 D면 금액증가로 C면 금액감소
		if (oMastAsset.getDC_TYCD().equals("D") && isPay
				|| oMastAsset.getDC_TYCD().equals("C") && !isPay
			) 
		{
			oMastAssetIns.setDEC_AMT(nAmt);
			oMastAssetIns.setAMT(oMastAssetIns.getBEF_AMT().subtract(nAmt));
		}
		else if (oMastAsset.getDC_TYCD().equals("C") && isPay
				|| oMastAsset.getDC_TYCD().equals("D") && !isPay
				) 
		{
			oMastAssetIns.setINC_AMT(nAmt);
			oMastAssetIns.setAMT(oMastAssetIns.getBEF_AMT().add(nAmt));
		}
		mastAssetDao.insMastAsset(oMastAssetIns);
		logger.info("원장 신규작성완료");
		
	}
	
	// 자산원장 수정
	public void updMastAsset(MastAssetVo oMastAsset, Boolean isPay) {
		
		MastAssetVo oMastAssetUpd = new MastAssetVo();

		String sWkdt = oMastAsset.getWK_DT();			// 원장일
		BigDecimal nAmt = oMastAsset.getAMT();			// 변경금액
		String sAsset = oMastAsset.getASSET();		// 원장자산
		
		oMastAssetUpd.setWK_DT(sWkdt);
		oMastAssetUpd.setASSET(sAsset);

		// 출금처 차대유형이 D면 금액감소로 C면 금액증가
		// 입금처 차대유형이 D면 금액증가로 C면 금액감소
		if (oMastAsset.getDC_TYCD().equals("D") && isPay
				|| oMastAsset.getDC_TYCD().equals("C") && !isPay
			) 
		{
			oMastAssetUpd.setDEC_AMT(oMastAsset.getBEF_DEC_AMT().add(nAmt));
			oMastAssetUpd.setAMT(oMastAsset.getBEF_AMT().subtract(nAmt));
			oMastAssetUpd.setINC_AMT(oMastAsset.getBEF_INC_AMT());
		}
		else if (oMastAsset.getDC_TYCD().equals("C") && isPay
				|| oMastAsset.getDC_TYCD().equals("D") && !isPay
				) 
		{
			oMastAssetUpd.setINC_AMT(oMastAsset.getBEF_INC_AMT().add(nAmt));
			oMastAssetUpd.setAMT(oMastAsset.getBEF_AMT().add(nAmt));
			oMastAssetUpd.setDEC_AMT(oMastAsset.getBEF_DEC_AMT());
		}				
		mastAssetDao.updMastAsset(oMastAssetUpd);
		logger.info("원장 변경완료");
	}

}

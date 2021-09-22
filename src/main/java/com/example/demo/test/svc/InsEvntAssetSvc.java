package com.example.demo.test.svc;

import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.example.demo.test.dao.InsEvntAssetDao;
import com.example.demo.test.vo.InsEvntAssetVo;


@Service
public class InsEvntAssetSvc {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	public InsEvntAssetDao insEvntAssetDao;

	public List<InsEvntAssetVo> lstEvnt(){
		return insEvntAssetDao.lstEvnt();
	}
//화면에 보내줄때 신용카드면 결제일같이보내줌
	public List<InsEvntAssetVo> lstAsset(){
		return insEvntAssetDao.lstAsset();
	}
	public List<InsEvntAssetVo> lstEvntTycd(){
		return insEvntAssetDao.lstEvntTycd();
	}

	public List<InsEvntAssetVo> lstMastAsset(InsEvntAssetVo insEvntAssetVo){
		return insEvntAssetDao.lstMastAsset(insEvntAssetVo);
	}

	/**
	 *  거래내역 입력 및 원장
	**/
	public void isrt(InsEvntAssetVo insEvntAssetVo){

		//화면에 보내줄때 신용카드면 결제일같이보내줘서 이미 받아옴
		// 이벤트내역 입력
		logger.info("이벤트내역 입력 시작={WK_DT::"+insEvntAssetVo.getWK_DT()+", EVNT::"+insEvntAssetVo.getEVNT()+", PAY_ASSET::"+insEvntAssetVo.getPAY_ASSET()+", RCV_ASSET::"+insEvntAssetVo.getRCV_ASSET()+", AMT::"+insEvntAssetVo.getAMT()+"}");
		insEvntAssetDao.isrt(insEvntAssetVo);
		logger.info("이벤트내역 입력 완료");

		InsEvntAssetVo oAsset = new InsEvntAssetVo();
		oAsset.setWK_DT(insEvntAssetVo.getWK_DT());
		oAsset.setAMT(insEvntAssetVo.getAMT());
		// 출금처가 있으면 출금처 원장 수정
		if (!ObjectUtils.isEmpty(insEvntAssetVo.getPAY_ASSET())) {
			oAsset.setASSET(insEvntAssetVo.getPAY_ASSET());			
			updtMastAsset(oAsset, true);
		}

		// 입금처가 있으면 입금처 원장 수정
		if (!ObjectUtils.isEmpty(insEvntAssetVo.getRCV_ASSET())) {
			oAsset.setASSET(insEvntAssetVo.getRCV_ASSET());			
			updtMastAsset(oAsset, false);
			
		}		
	}

	public void updtMastAsset(InsEvntAssetVo oAsset, Boolean isPay){
		// 자산정보(자산종류-차대타입, 충전정보)
		// 체크카드인 경우 결체처로 검색
		// 체크카드인데 지출금액이 더 작으면 
		InsEvntAssetVo oMastAsset = insEvntAssetDao.viewMastAsset(oAsset);
		String sWkdt = oAsset.getWK_DT();			// 원장일
		BigDecimal nAmt = oAsset.getAMT();			// 변경금액
		String sAsset = oMastAsset.getASSET();		// 원장자산
		logger.info("원장 입력 시작={WK_DT::"+sWkdt+", BEF_WK_DT::"+oMastAsset.getBEF_WK_DT()+", ASSET::"+sAsset+", isPay::"+isPay+"}");

		InsEvntAssetVo oMastAssetIsrt = new InsEvntAssetVo();

		// 최근원장이 없으면 전일금액0, 전일0
		if (ObjectUtils.isEmpty(oMastAsset.getBEF_WK_DT())) {
			oMastAssetIsrt.setWK_DT(sWkdt);
			oMastAssetIsrt.setASSET(sAsset);
			oMastAssetIsrt.setBEF_AMT(BigDecimal.ZERO);
			oMastAssetIsrt.setBEF_DT("00000000");

			// 출금처 차대유형이 D면 금액감소로 C면 금액증가
			// 입금처 차대유형이 D면 금액증가로 C면 금액감소
			if (oMastAsset.getDC_TYCD().equals("D") && isPay
					|| oMastAsset.getDC_TYCD().equals("C") && !isPay
				) 
			{
				oMastAssetIsrt.setDEC_AMT(nAmt);
				oMastAssetIsrt.setAMT(BigDecimal.ZERO.subtract(nAmt));
			}
			else if (oMastAsset.getDC_TYCD().equals("C") && isPay
					|| oMastAsset.getDC_TYCD().equals("D") && !isPay
					) 
			{
				oMastAssetIsrt.setINC_AMT(nAmt);
				oMastAssetIsrt.setAMT(BigDecimal.ZERO.add(nAmt));
			}
			insEvntAssetDao.isrtMastAsset(oMastAssetIsrt);
			logger.info("원장 신규작성완료");
		} 
		
		
		// 최근원장이 오늘이 아닌 경우 지난원장 정보 바탕으로 입력시킴
		else if (!oMastAsset.getBEF_WK_DT().equals(sWkdt)) 
		{
			oMastAssetIsrt.setWK_DT(sWkdt);
			oMastAssetIsrt.setASSET(sAsset);
			oMastAssetIsrt.setBEF_DT(oMastAsset.getBEF_WK_DT());
			oMastAssetIsrt.setBEF_AMT(oMastAsset.getBEF_AMT());

			// 출금처 차대유형이 D면 금액감소로 C면 금액증가
			// 입금처 차대유형이 D면 금액증가로 C면 금액감소
			if (oMastAsset.getDC_TYCD().equals("D") && isPay
					|| oMastAsset.getDC_TYCD().equals("C") && !isPay
				) 
			{
				oMastAssetIsrt.setDEC_AMT(nAmt);
				oMastAssetIsrt.setAMT(oMastAsset.getBEF_AMT().subtract(nAmt));
			}
			else if (oMastAsset.getDC_TYCD().equals("C") && isPay
					|| oMastAsset.getDC_TYCD().equals("D") && !isPay
					) 
			{
				oMastAssetIsrt.setINC_AMT(nAmt);
				oMastAssetIsrt.setAMT(oMastAsset.getBEF_AMT().add(nAmt));
			}
			insEvntAssetDao.isrtMastAsset(oMastAssetIsrt);
			logger.info("원장 작성완료");
		}
		
		// 최근원장이 오늘인 경우 업데이트 시킴
		else if (oMastAsset.getBEF_WK_DT().equals(sWkdt)) 
		{
			oMastAssetIsrt.setWK_DT(sWkdt);
			oMastAssetIsrt.setASSET(sAsset);

			// 출금처 차대유형이 D면 금액감소로 C면 금액증가
			// 입금처 차대유형이 D면 금액증가로 C면 금액감소
			if (oMastAsset.getDC_TYCD().equals("D") && isPay
					|| oMastAsset.getDC_TYCD().equals("C") && !isPay
				) 
			{
				oMastAssetIsrt.setDEC_AMT(oMastAsset.getBEF_DEC_AMT().add(nAmt));
				oMastAssetIsrt.setAMT(oMastAsset.getBEF_AMT().subtract(nAmt));
				oMastAssetIsrt.setINC_AMT(oMastAsset.getBEF_INC_AMT());
			}
			else if (oMastAsset.getDC_TYCD().equals("C") && isPay
					|| oMastAsset.getDC_TYCD().equals("D") && !isPay
					) 
			{
				oMastAssetIsrt.setINC_AMT(oMastAsset.getBEF_INC_AMT().add(nAmt));
				oMastAssetIsrt.setAMT(oMastAsset.getBEF_AMT().add(nAmt));
				oMastAssetIsrt.setDEC_AMT(oMastAsset.getBEF_DEC_AMT());
			}				
			insEvntAssetDao.updtMastAsset(oMastAssetIsrt);
			logger.info("원장 변경완료");
		}

		// 오늘 이후 첫번째 원장 이전날짜 업데이트시킴
		if (!ObjectUtils.isEmpty(oMastAsset.getAFT_WK_DT())) {
			logger.info("이벤트 날짜 이후 원장 수정");
			InsEvntAssetVo oMastAssetAft = new InsEvntAssetVo();
			oMastAssetAft.setASSET(sAsset);
			oMastAssetAft.setWK_DT(oMastAsset.getAFT_WK_DT());
			oMastAssetAft.setBEF_DT(sWkdt);
			insEvntAssetDao.updtMastAssetAft(oMastAssetAft);


			oMastAssetAft.setWK_DT(sWkdt);
			oMastAssetAft.setAMT(nAmt);
			// 출금처 차대유형이 D면 금액감소로 C면 금액증가
			// 입금처 차대유형이 D면 금액증가로 C면 금액감소
			if (oMastAsset.getDC_TYCD().equals("D") && isPay
					|| oMastAsset.getDC_TYCD().equals("C") && !isPay
				) {
				insEvntAssetDao.updtDecAssetAft(oMastAssetAft);
			}
			else if (oMastAsset.getDC_TYCD().equals("C") && isPay
					|| oMastAsset.getDC_TYCD().equals("D") && !isPay
					) {
				insEvntAssetDao.updtIncAssetAft(oMastAssetAft);
			}
		}

		// 충전하면서 금액 마이너스 된 경우 마이너스 금액만 충전됨
		if (!ObjectUtils.isEmpty(oMastAsset.getCHRG_YN())
				&& oMastAsset.getCHRG_YN().equals("Y")
				&& oMastAssetIsrt.getAMT().compareTo(BigDecimal.ZERO) < 0) {
			InsEvntAssetVo oChrgEvnt = new InsEvntAssetVo();
			oChrgEvnt.setWK_DT(sWkdt);
			oChrgEvnt.setEVNT("E0010");
			oChrgEvnt.setPAY_ASSET(oMastAsset.getCHRG_ASSET());
			oChrgEvnt.setRCV_ASSET(sAsset);
			oChrgEvnt.setAMT(oMastAssetIsrt.getAMT().negate());

			isrt(oChrgEvnt);
		}
		// 충전조건 터진 경우 충전거래 발생시킴
		else if (!ObjectUtils.isEmpty(oMastAsset.getCHRG_YN())
				&& oMastAsset.getCHRG_YN().equals("Y")
				&& oMastAssetIsrt.getAMT().compareTo(oMastAsset.getCHRG_COND_AMT()) < 0) {
			InsEvntAssetVo oChrgEvnt = new InsEvntAssetVo();
			oChrgEvnt.setWK_DT(sWkdt);
			oChrgEvnt.setEVNT("E0010");
			oChrgEvnt.setPAY_ASSET(oMastAsset.getCHRG_ASSET());
			oChrgEvnt.setRCV_ASSET(sAsset);
			oChrgEvnt.setAMT(oMastAsset.getCHRG_AMT());

			isrt(oChrgEvnt);
		}
		
	}

}

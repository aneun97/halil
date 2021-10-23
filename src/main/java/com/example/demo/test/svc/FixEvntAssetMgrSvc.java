package com.example.demo.test.svc;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.example.demo.test.biz.MastAssetBiz;
import com.example.demo.test.dao.FixEvntAssetMgrDao;
import com.example.demo.test.vo.FixEvntAssetMgrVo;
import com.example.demo.test.vo.MastAssetVo;

@Service
public class FixEvntAssetMgrSvc {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	public FixEvntAssetMgrDao fixAssetEvntDao;

	@Autowired
	public MastAssetBiz mastAssetBiz;
	
	public List<FixEvntAssetMgrVo> lst(FixEvntAssetMgrVo fixEvntAssetMgrVo){
		return fixAssetEvntDao.lst(fixEvntAssetMgrVo);
	}

	/**
	 *  자산 신규 입력
	 * @throws Exception 
	**/
	public void ins(FixEvntAssetMgrVo fixEvntAssetMgrVo) throws Exception{
		
		//1. 날짜 입력되면 시작일이 오늘 이후여야하고, 시작일보다 종료일이 커야함		
		
		//오늘날짜
		Date dNow = Date.valueOf(LocalDate.now());
		DateFormat dateForm = new SimpleDateFormat("yyyyMMdd");

		String sFromDt= fixEvntAssetMgrVo.getFROM_DT();
		String sToDt= fixEvntAssetMgrVo.getTO_DT();
		Date dFromDt = new Date(dateForm.parse(sFromDt).getTime());
		Date dToDt = new Date(dateForm.parse(sToDt).getTime());
		
		
		
		// 시작일이 종료일 이후이면 오류
		if (dFromDt.after(dToDt)) {
			throw new Exception("시작일이 종료일 이후이면 오류");
		}
		
		// 오늘날짜가 시작일 이후이면 입력 불가
		if (dNow.after(dFromDt)) {
			throw new Exception("오늘날짜가 시작일 이후이면 입력 불가");
		}

		//2. 고정자산변동 입력		
		fixAssetEvntDao.ins(fixEvntAssetMgrVo);	
		logger.info("고정이벤트번호::"+fixEvntAssetMgrVo.getFIX_EVNT_NO());

		
		//오늘날짜
		Calendar cNowDate = Calendar.getInstance();
		cNowDate.setTime(Date.valueOf(LocalDate.now()));	
		
		//종료날짜
		Calendar cToDate = Calendar.getInstance();
		cToDate.setTime(dToDt);	

		//오늘부터 2년뒤말일
		Calendar cAftTwoYear = Calendar.getInstance();
		cAftTwoYear.set(cNowDate.get(Calendar.YEAR)+2, 11, 31);	

		// 종료일이 2년뒤말일보다 이후이면 2년뒤말일까지만 입력
		if (cToDate.after(cAftTwoYear)) {
			cToDate.set(cNowDate.get(Calendar.YEAR)+2, 11, 31);
		}
		
		
		
		//작업날짜
		Calendar cWorkDate = Calendar.getInstance();
		cWorkDate.setTime(dFromDt);

		int iYear = cWorkDate.get(Calendar.YEAR);
		int iMonth = cWorkDate.get(Calendar.MONTH);
		int iDate = cWorkDate.get(Calendar.DAY_OF_MONTH);
		
		cWorkDate.set(iYear, iMonth, iDate);
		
		// 말일에 이벤트발생이면 일자를 말일로 세팅
		if (fixEvntAssetMgrVo.getEVNT_DT() == 99) {
			iDate = cWorkDate.getActualMaximum(Calendar.DAY_OF_MONTH);
			cWorkDate.set(iYear, iMonth, iDate);
			iDate = 1;
		}
		else {
			iDate = fixEvntAssetMgrVo.getEVNT_DT();
			cWorkDate.set(iYear, iMonth, iDate);
		}
		
		
		// 작업날짜가 오늘날짜보다 전이면 다음달부터 입력시작
		if (cWorkDate.before(cNowDate)) {
			iMonth++;
			cWorkDate.set(iYear, iMonth, iDate);
		}
		
		// 입력에 사용할 vo채우기
		FixEvntAssetMgrVo assetEvntVo = new FixEvntAssetMgrVo();
		assetEvntVo.setEVNT(fixEvntAssetMgrVo.getEVNT());
		assetEvntVo.setPAY_ASSET(fixEvntAssetMgrVo.getPAY_ASSET());
		assetEvntVo.setRCV_ASSET(fixEvntAssetMgrVo.getRCV_ASSET());
		assetEvntVo.setAMT(fixEvntAssetMgrVo.getAMT());
		assetEvntVo.setEVNT_HCLS(fixEvntAssetMgrVo.getEVNT_HCLS());
		assetEvntVo.setFIRM(fixEvntAssetMgrVo.getFIRM());
		assetEvntVo.setDTL(fixEvntAssetMgrVo.getDTL());
		assetEvntVo.setFIX_EVNT_NO(fixEvntAssetMgrVo.getFIX_EVNT_NO());
		assetEvntVo.setCHK_YN("N");
		
		
		
		// 작업일이 종료일보다 커질때까지 반복
		while(!cWorkDate.after(cToDate)) {
			
			assetEvntVo.setWK_DT(dateForm.format(cWorkDate.getTime()));
			
			//입력
			fixAssetEvntDao.insAssetEvnt(assetEvntVo);

			MastAssetVo oAsset = new MastAssetVo();
			oAsset.setWK_DT(assetEvntVo.getWK_DT());
			oAsset.setAMT(assetEvntVo.getAMT());
			// 출금처가 있으면 출금처 원장 수정
			if (!ObjectUtils.isEmpty(assetEvntVo.getPAY_ASSET())) {
				oAsset.setASSET(assetEvntVo.getPAY_ASSET());			
				mastAssetBiz.recMastAsset(oAsset, true, true);
			}

			// 입금처가 있으면 입금처 원장 수정
			if (!ObjectUtils.isEmpty(assetEvntVo.getRCV_ASSET())) {
				oAsset.setASSET(assetEvntVo.getRCV_ASSET());			
				mastAssetBiz.recMastAsset(oAsset, false, true);				
			}

			logger.info("작업일::"+assetEvntVo.getWK_DT());
			//후처리			
			iMonth++;
			cWorkDate.set(iYear, iMonth, iDate);
			
			// 말일에 이벤트발생이면 일자를 말일로 세팅
			if (fixEvntAssetMgrVo.getEVNT_DT() == 99) {
				iDate = cWorkDate.getActualMaximum(Calendar.DAY_OF_MONTH);
				cWorkDate.set(iYear, iMonth, iDate);
				iDate = 1;
			}
						
		}
		
		// 기입완료일 등록
		iMonth--;
		cWorkDate.set(iYear, iMonth, iDate);		
		// 말일에 이벤트발생이면 일자를 말일로 세팅
		if (fixEvntAssetMgrVo.getEVNT_DT() == 99) {
			iDate = cWorkDate.getActualMaximum(Calendar.DAY_OF_MONTH);
			cWorkDate.set(iYear, iMonth, iDate);
		}		
		fixEvntAssetMgrVo.setREC_DT(dateForm.format(cWorkDate.getTime()));
		fixAssetEvntDao.updRecDt(fixEvntAssetMgrVo);
		
		
		logger.info("입력 작업 완료");
		
		
		
		
	}
}

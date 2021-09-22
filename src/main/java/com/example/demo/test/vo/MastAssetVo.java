package com.example.demo.test.vo;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MastAssetVo {
	//EVNT_ASSET 테이블
	private String WK_DT;
	private String EVNT_NO;
	private String EVNT;
	private String ASSET;
	private String PAY_ASSET;
	private String RCV_ASSET;
	private BigDecimal AMT = new BigDecimal(0);
	private String EVNT_TYCD;
	private String FIRM;
	private String DTL;
	private String PAY_STG;
	private String PAY_DT;
	
	//INFO_ASSET 테이블	
	private String KR_NM;
	private String ASSET_KIND;
	private String DC_TYCD;
	private String CHRG_YN;
	private BigDecimal CHRG_COND_AMT = new BigDecimal(0);
	private String CHRG_ASSET;
	private BigDecimal CHRG_AMT = new BigDecimal(0);
	private String PAY_DAY;
	private String TOT_DAY;

	//MAST_ASSET 테이블
	private String DT_COND;
	private String BEF_DT;
	private BigDecimal BEF_AMT = new BigDecimal(0);
	private BigDecimal INC_AMT = new BigDecimal(0);
	private BigDecimal DEC_AMT = new BigDecimal(0);
	private String BEF_WK_DT;
	private String BEF_BEF_DT;
	private BigDecimal BEF_BEF_AMT = new BigDecimal(0);
	private BigDecimal BEF_INC_AMT = new BigDecimal(0);
	private BigDecimal BEF_DEC_AMT = new BigDecimal(0);
	private String AFT_WK_DT;
	private String AFT_BEF_DT;
	private BigDecimal AFT_BEF_AMT = new BigDecimal(0);
	private BigDecimal AFT_INC_AMT = new BigDecimal(0);
	private BigDecimal AFT_DEC_AMT = new BigDecimal(0);
	private BigDecimal AFT_AMT = new BigDecimal(0);

	//INFO_EVNT 테이블
	private String PAY_TYCD;
	private String RCV_TYCD;

	//INFO_EVNT_HCLS 테이블
	private String EVNT_HCLS;
	
	public String getWK_DT() {
		return WK_DT;
	}
	public void setWK_DT(String wK_DT) {
		WK_DT = wK_DT;
	}
	public String getEVNT_NO() {
		return EVNT_NO;
	}
	public void setEVNT_NO(String eVNT_NO) {
		EVNT_NO = eVNT_NO;
	}
	public String getEVNT() {
		return EVNT;
	}
	public void setEVNT(String eVNT) {
		EVNT = eVNT;
	}
	public String getASSET() {
		return ASSET;
	}
	public void setASSET(String aSSET) {
		ASSET = aSSET;
	}
	public String getPAY_ASSET() {
		return PAY_ASSET;
	}
	public void setPAY_ASSET(String pAY_ASSET) {
		PAY_ASSET = pAY_ASSET;
	}
	public String getRCV_ASSET() {
		return RCV_ASSET;
	}
	public void setRCV_ASSET(String rCV_ASSET) {
		RCV_ASSET = rCV_ASSET;
	}
	public BigDecimal getAMT() {
		return AMT;
	}
	public void setAMT(BigDecimal aMT) {
		AMT = aMT;
	}
	public String getEVNT_TYCD() {
		return EVNT_TYCD;
	}
	public void setEVNT_TYCD(String eVNT_TYCD) {
		EVNT_TYCD = eVNT_TYCD;
	}
	public String getFIRM() {
		return FIRM;
	}
	public void setFIRM(String fIRM) {
		FIRM = fIRM;
	}
	public String getDTL() {
		return DTL;
	}
	public void setDTL(String dTL) {
		DTL = dTL;
	}
	
	
	
	public String getKR_NM() {
		return KR_NM;
	}
	public void setKR_NM(String kR_NM) {
		KR_NM = kR_NM;
	}
	public String getDT_COND() {
		return DT_COND;
	}
	public void setDT_COND(String dT_COND) {
		DT_COND = dT_COND;
	}
	public String getBEF_DT() {
		return BEF_DT;
	}
	public void setBEF_DT(String bEF_DT) {
		BEF_DT = bEF_DT;
	}
	public BigDecimal getBEF_AMT() {
		return BEF_AMT;
	}
	public void setBEF_AMT(BigDecimal bEF_AMT) {
		BEF_AMT = bEF_AMT;
	}
	public BigDecimal getINC_AMT() {
		return INC_AMT;
	}
	public void setINC_AMT(BigDecimal iNC_AMT) {
		INC_AMT = iNC_AMT;
	}
	public BigDecimal getDEC_AMT() {
		return DEC_AMT;
	}
	public void setDEC_AMT(BigDecimal dEC_AMT) {
		DEC_AMT = dEC_AMT;
	}
	public String getASSET_KIND() {
		return ASSET_KIND;
	}
	public void setASSET_KIND(String aSSET_KIND) {
		ASSET_KIND = aSSET_KIND;
	}
	public String getDC_TYCD() {
		return DC_TYCD;
	}
	public void setDC_TYCD(String dC_TYCD) {
		DC_TYCD = dC_TYCD;
	}
	public String getCHRG_YN() {
		return CHRG_YN;
	}
	public void setCHRG_YN(String cHRG_YN) {
		CHRG_YN = cHRG_YN;
	}
	public BigDecimal getCHRG_COND_AMT() {
		return CHRG_COND_AMT;
	}
	public void setCHRG_COND_AMT(BigDecimal cHRG_COND_AMT) {
		CHRG_COND_AMT = cHRG_COND_AMT;
	}
	public String getCHRG_ASSET() {
		return CHRG_ASSET;
	}
	public void setCHRG_ASSET(String cHRG_ASSET) {
		CHRG_ASSET = cHRG_ASSET;
	}
	public BigDecimal getCHRG_AMT() {
		return CHRG_AMT;
	}
	public void setCHRG_AMT(BigDecimal cHRG_AMT) {
		CHRG_AMT = cHRG_AMT;
	}
	public String getBEF_WK_DT() {
		return BEF_WK_DT;
	}
	public void setBEF_WK_DT(String bEF_WK_DT) {
		BEF_WK_DT = bEF_WK_DT;
	}
	public String getBEF_BEF_DT() {
		return BEF_BEF_DT;
	}
	public void setBEF_BEF_DT(String bEF_BEF_DT) {
		BEF_BEF_DT = bEF_BEF_DT;
	}
	public BigDecimal getBEF_BEF_AMT() {
		return BEF_BEF_AMT;
	}
	public void setBEF_BEF_AMT(BigDecimal bEF_BEF_AMT) {
		BEF_BEF_AMT = bEF_BEF_AMT;
	}
	public BigDecimal getBEF_INC_AMT() {
		return BEF_INC_AMT;
	}
	public void setBEF_INC_AMT(BigDecimal bEF_INC_AMT) {
		BEF_INC_AMT = bEF_INC_AMT;
	}
	public BigDecimal getBEF_DEC_AMT() {
		return BEF_DEC_AMT;
	}
	public void setBEF_DEC_AMT(BigDecimal bEF_DEC_AMT) {
		BEF_DEC_AMT = bEF_DEC_AMT;
	}
	public String getAFT_WK_DT() {
		return AFT_WK_DT;
	}
	public void setAFT_WK_DT(String aFT_WK_DT) {
		AFT_WK_DT = aFT_WK_DT;
	}
	public String getAFT_BEF_DT() {
		return AFT_BEF_DT;
	}
	public void setAFT_BEF_DT(String aFT_BEF_DT) {
		AFT_BEF_DT = aFT_BEF_DT;
	}
	public BigDecimal getAFT_BEF_AMT() {
		return AFT_BEF_AMT;
	}
	public void setAFT_BEF_AMT(BigDecimal aFT_BEF_AMT) {
		AFT_BEF_AMT = aFT_BEF_AMT;
	}
	public BigDecimal getAFT_INC_AMT() {
		return AFT_INC_AMT;
	}
	public void setAFT_INC_AMT(BigDecimal aFT_INC_AMT) {
		AFT_INC_AMT = aFT_INC_AMT;
	}
	public BigDecimal getAFT_DEC_AMT() {
		return AFT_DEC_AMT;
	}
	public void setAFT_DEC_AMT(BigDecimal aFT_DEC_AMT) {
		AFT_DEC_AMT = aFT_DEC_AMT;
	}
	public BigDecimal getAFT_AMT() {
		return AFT_AMT;
	}
	public void setAFT_AMT(BigDecimal aFT_AMT) {
		AFT_AMT = aFT_AMT;
	}
	public String getPAY_TYCD() {
		return PAY_TYCD;
	}
	public void setPAY_TYCD(String pAY_TYCD) {
		PAY_TYCD = pAY_TYCD;
	}
	public String getRCV_TYCD() {
		return RCV_TYCD;
	}
	public void setRCV_TYCD(String rCV_TYCD) {
		RCV_TYCD = rCV_TYCD;
	}
	public String getPAY_STG() {
		return PAY_STG;
	}
	public void setPAY_STG(String pAY_STG) {
		PAY_STG = pAY_STG;
	}
	public String getPAY_DT() {
		return PAY_DT;
	}
	public void setPAY_DT(String pAY_DT) {
		PAY_DT = pAY_DT;
	}
	public String getPAY_DAY() {
		return PAY_DAY;
	}
	public void setPAY_DAY(String pAY_DAY) {
		PAY_DAY = pAY_DAY;
	}
	public String getTOT_DAY() {
		return TOT_DAY;
	}
	public void setTOT_DAY(String tOT_DAY) {
		TOT_DAY = tOT_DAY;
	}
	public String getEVNT_HCLS() {
		return EVNT_HCLS;
	}
	public void setEVNT_HCLS(String eVNT_HCLS) {
		EVNT_HCLS = eVNT_HCLS;
	}
	
	
}

package com.example.demo.test.vo;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AssetAmtSelVo {
	private BigDecimal DEBIT = new BigDecimal(0);
	private BigDecimal CREDIT = new BigDecimal(0);
	private BigDecimal NET_DEBIT = new BigDecimal(0);
	
	// 자산별 정보
	private String WK_DT;
	private String ASSET_WK_DT;
	private String ASSET;
	private String ASSET_NM;
	private BigDecimal AMT = new BigDecimal(0);
	private String ASSET_KIND;
	private String ASSET_KIND_NM;
	private String FIRM;

	//INFO_ASSET 테이블
	private int SORT_NO;
	private String KR_NM;
	private String EN_NM;
	private String OWNER;
	private String FROM_DT;
	private String TO_DT;
	private String CHRG_YN;
	private String CHRG_COND_AMT;
	private String CHRG_ASSET;
	private String CHRG_AMT;
	private String PAY_ASSET;
	private String PAY_DAY;
	private String TOT_DAY;
	
	public BigDecimal getDEBIT() {
		return DEBIT;
	}
	public void setDEBIT(BigDecimal dEBIT) {
		DEBIT = dEBIT;
	}
	public BigDecimal getCREDIT() {
		return CREDIT;
	}
	public void setCREDIT(BigDecimal cREDIT) {
		CREDIT = cREDIT;
	}
	public BigDecimal getNET_DEBIT() {
		return NET_DEBIT;
	}
	public void setNET_DEBIT(BigDecimal nET_DEBIT) {
		NET_DEBIT = nET_DEBIT;
	}
	public String getWK_DT() {
		return WK_DT;
	}
	public void setWK_DT(String wK_DT) {
		WK_DT = wK_DT;
	}
	public String getASSET_WK_DT() {
		return ASSET_WK_DT;
	}
	public void setASSET_WK_DT(String aSSET_WK_DT) {
		ASSET_WK_DT = aSSET_WK_DT;
	}
	public String getASSET() {
		return ASSET;
	}
	public void setASSET(String aSSET) {
		ASSET = aSSET;
	}
	public String getASSET_NM() {
		return ASSET_NM;
	}
	public void setASSET_NM(String aSSET_NM) {
		ASSET_NM = aSSET_NM;
	}
	public BigDecimal getAMT() {
		return AMT;
	}
	public void setAMT(BigDecimal aMT) {
		AMT = aMT;
	}
	public String getASSET_KIND() {
		return ASSET_KIND;
	}
	public void setASSET_KIND(String aSSET_KIND) {
		ASSET_KIND = aSSET_KIND;
	}
	public String getASSET_KIND_NM() {
		return ASSET_KIND_NM;
	}
	public void setASSET_KIND_NM(String aSSET_KIND_NM) {
		ASSET_KIND_NM = aSSET_KIND_NM;
	}
	public String getFIRM() {
		return FIRM;
	}
	public void setFIRM(String fIRM) {
		FIRM = fIRM;
	}
	public int getSORT_NO() {
		return SORT_NO;
	}
	public void setSORT_NO(int sORT_NO) {
		SORT_NO = sORT_NO;
	}
	public String getKR_NM() {
		return KR_NM;
	}
	public void setKR_NM(String kR_NM) {
		KR_NM = kR_NM;
	}
	public String getEN_NM() {
		return EN_NM;
	}
	public void setEN_NM(String eN_NM) {
		EN_NM = eN_NM;
	}
	public String getOWNER() {
		return OWNER;
	}
	public void setOWNER(String oWNER) {
		OWNER = oWNER;
	}
	public String getTO_DT() {
		return TO_DT;
	}
	public void setTO_DT(String tO_DT) {
		TO_DT = tO_DT;
	}
	public String getFROM_DT() {
		return FROM_DT;
	}
	public void setFROM_DT(String fROM_DT) {
		FROM_DT = fROM_DT;
	}
	public String getCHRG_YN() {
		return CHRG_YN;
	}
	public void setCHRG_YN(String cHRG_YN) {
		CHRG_YN = cHRG_YN;
	}
	public String getCHRG_COND_AMT() {
		return CHRG_COND_AMT;
	}
	public void setCHRG_COND_AMT(String cHRG_COND_AMT) {
		CHRG_COND_AMT = cHRG_COND_AMT;
	}
	public String getCHRG_ASSET() {
		return CHRG_ASSET;
	}
	public void setCHRG_ASSET(String cHRG_ASSET) {
		CHRG_ASSET = cHRG_ASSET;
	}
	public String getCHRG_AMT() {
		return CHRG_AMT;
	}
	public void setCHRG_AMT(String cHRG_AMT) {
		CHRG_AMT = cHRG_AMT;
	}
	public String getPAY_ASSET() {
		return PAY_ASSET;
	}
	public void setPAY_ASSET(String pAY_ASSET) {
		PAY_ASSET = pAY_ASSET;
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
	
	
}

package com.example.demo.test.vo;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EvntAssetSelVo {
	//EVNT_ASSET 테이블
	private String WK_DT;
	private String EVNT_NO;
	private String EVNT;
	private String PAY_ASSET;
	private String RCV_ASSET;
	private BigDecimal AMT = new BigDecimal(0);
	private String EVNT_HCLS;
	private String EVNT_LCLS;
	private String FIRM;
	private String DTL;
	private String CHK_YN;


	private String ASSET;
	private String ASSET_KIND;
	private String EVNT_NM;
	private String FROM_DT;
	private String TO_DT;
	

	private String PAY_YN;
	private String RCV_YN;
	private String ETC_YN;
	
	
	
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
	public String getEVNT_HCLS() {
		return EVNT_HCLS;
	}
	public void setEVNT_HCLS(String eVNT_HCLS) {
		EVNT_HCLS = eVNT_HCLS;
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
	public String getFROM_DT() {
		return FROM_DT;
	}
	public void setFROM_DT(String fROM_DT) {
		FROM_DT = fROM_DT;
	}
	public String getTO_DT() {
		return TO_DT;
	}
	public void setTO_DT(String tO_DT) {
		TO_DT = tO_DT;
	}
	public String getEVNT_NM() {
		return EVNT_NM;
	}
	public void setEVNT_NM(String eVNT_NM) {
		EVNT_NM = eVNT_NM;
	}
	public String getPAY_YN() {
		return PAY_YN;
	}
	public void setPAY_YN(String pAY_YN) {
		PAY_YN = pAY_YN;
	}
	public String getRCV_YN() {
		return RCV_YN;
	}
	public void setRCV_YN(String rCV_YN) {
		RCV_YN = rCV_YN;
	}
	public String getETC_YN() {
		return ETC_YN;
	}
	public void setETC_YN(String eTC_YN) {
		ETC_YN = eTC_YN;
	}
	public String getASSET_KIND() {
		return ASSET_KIND;
	}
	public void setASSET_KIND(String aSSET_KIND) {
		ASSET_KIND = aSSET_KIND;
	}
	public String getCHK_YN() {
		return CHK_YN;
	}
	public void setCHK_YN(String cHK_YN) {
		CHK_YN = cHK_YN;
	}
	public String getEVNT_LCLS() {
		return EVNT_LCLS;
	}
	public void setEVNT_LCLS(String eVNT_LCLS) {
		EVNT_LCLS = eVNT_LCLS;
	}

	
	
}

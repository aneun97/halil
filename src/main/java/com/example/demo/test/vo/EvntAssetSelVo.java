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
	private String EVNT_TYCD;
	private String FIRM;
	private String DTL;


	private String ASSET;
	private String EVNT_NM;
	private String FROM_DT;
	private String TO_DT;
	
	
	
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
	
	
	
}
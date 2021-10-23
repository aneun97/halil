package com.example.demo.test.vo;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FixEvntAssetMgrVo {
	//FIX_EVNT_ASSET 테이블
	private String FIX_EVNT_NO;
	private String EVNT;
	private String PAY_ASSET;
	private String RCV_ASSET;
	private BigDecimal AMT = new BigDecimal(0);
	private String EVNT_HCLS;
	private String FIRM;
	private String DTL;
	private String EVNT_PER;
	private int EVNT_DT;
	private String FROM_DT;
	private String TO_DT;
	private String AMT_ID_TYCD;
	private String AMT_ID;
	private String REC_DT;

	private String EVNT_NM;

	//EVNT_ASSET 테이블
	private String EVNT_NO;
	private String WK_DT;
	private String CHK_YN;
	
	
	public String getFIX_EVNT_NO() {
		return FIX_EVNT_NO;
	}
	public void setFIX_EVNT_NO(String fIX_EVNT_NO) {
		FIX_EVNT_NO = fIX_EVNT_NO;
	}
	public String getEVNT() {
		return EVNT;
	}
	public void setEVNT(String eVNT) {
		EVNT = eVNT;
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
	public String getEVNT_PER() {
		return EVNT_PER;
	}
	public void setEVNT_PER(String eVNT_PER) {
		EVNT_PER = eVNT_PER;
	}
	public int getEVNT_DT() {
		return EVNT_DT;
	}
	public void setEVNT_DT(int eVNT_DT) {
		EVNT_DT = eVNT_DT;
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
	public String getAMT_ID_TYCD() {
		return AMT_ID_TYCD;
	}
	public void setAMT_ID_TYCD(String aMT_ID_TYCD) {
		AMT_ID_TYCD = aMT_ID_TYCD;
	}
	public String getAMT_ID() {
		return AMT_ID;
	}
	public void setAMT_ID(String aMT_ID) {
		AMT_ID = aMT_ID;
	}
	public String getWK_DT() {
		return WK_DT;
	}
	public void setWK_DT(String wK_DT) {
		WK_DT = wK_DT;
	}
	public String getCHK_YN() {
		return CHK_YN;
	}
	public void setCHK_YN(String cHK_YN) {
		CHK_YN = cHK_YN;
	}
	public String getEVNT_NM() {
		return EVNT_NM;
	}
	public void setEVNT_NM(String eVNT_NM) {
		EVNT_NM = eVNT_NM;
	}
	public String getREC_DT() {
		return REC_DT;
	}
	public void setREC_DT(String rEC_DT) {
		REC_DT = rEC_DT;
	}
	public String getEVNT_NO() {
		return EVNT_NO;
	}
	public void setEVNT_NO(String eVNT_NO) {
		EVNT_NO = eVNT_NO;
	}
	
	
	
	
	

	
	
}

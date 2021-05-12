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
	
	
}

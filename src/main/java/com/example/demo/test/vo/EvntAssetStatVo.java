package com.example.demo.test.vo;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EvntAssetStatVo {

 	private String THS_ST_DT;
	private String THS_ED_DT;
 	private String BEF_ST_DT;
 	private String BEF_ED_DT;
 	private String THS_EVNT_HCLS;
 	private String THS_KR_NM;
 	private String BEF_EVNT_HCLS;
 	private String BEF_KR_NM;	
	
	private BigDecimal THS_SUM = new BigDecimal(0);
	private BigDecimal BEF_SUM = new BigDecimal(0);
	public String getTHS_ST_DT() {
		return THS_ST_DT;
	}
	public void setTHS_ST_DT(String tHS_ST_DT) {
		THS_ST_DT = tHS_ST_DT;
	}
	public String getTHS_ED_DT() {
		return THS_ED_DT;
	}
	public void setTHS_ED_DT(String tHS_ED_DT) {
		THS_ED_DT = tHS_ED_DT;
	}
	public String getBEF_ST_DT() {
		return BEF_ST_DT;
	}
	public void setBEF_ST_DT(String bEF_ST_DT) {
		BEF_ST_DT = bEF_ST_DT;
	}
	public String getBEF_ED_DT() {
		return BEF_ED_DT;
	}
	public void setBEF_ED_DT(String bEF_ED_DT) {
		BEF_ED_DT = bEF_ED_DT;
	}
	public String getTHS_EVNT_HCLS() {
		return THS_EVNT_HCLS;
	}
	public void setTHS_EVNT_HCLS(String tHS_EVNT_HCLS) {
		THS_EVNT_HCLS = tHS_EVNT_HCLS;
	}
	public String getTHS_KR_NM() {
		return THS_KR_NM;
	}
	public void setTHS_KR_NM(String tHS_KR_NM) {
		THS_KR_NM = tHS_KR_NM;
	}
	public String getBEF_EVNT_HCLS() {
		return BEF_EVNT_HCLS;
	}
	public void setBEF_EVNT_HCLS(String bEF_EVNT_HCLS) {
		BEF_EVNT_HCLS = bEF_EVNT_HCLS;
	}
	public String getBEF_KR_NM() {
		return BEF_KR_NM;
	}
	public void setBEF_KR_NM(String bEF_KR_NM) {
		BEF_KR_NM = bEF_KR_NM;
	}
	public BigDecimal getTHS_SUM() {
		return THS_SUM;
	}
	public void setTHS_SUM(BigDecimal tHS_SUM) {
		THS_SUM = tHS_SUM;
	}
	public BigDecimal getBEF_SUM() {
		return BEF_SUM;
	}
	public void setBEF_SUM(BigDecimal bEF_SUM) {
		BEF_SUM = bEF_SUM;
	}
	
	
}

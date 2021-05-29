package com.example.demo.test.vo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class TestVo {
	private String ST_DT;
	private String ED_DT;
	private String STD_DT;
	private BigDecimal DEBIT = BigDecimal.ZERO;
	
	private List<String> DATE_LIST = new ArrayList<String>();
	
	
	public String getST_DT() {
		return ST_DT;
	}
	public void setST_DT(String sT_DT) {
		ST_DT = sT_DT;
	}
	public String getED_DT() {
		return ED_DT;
	}
	public void setED_DT(String eD_DT) {
		ED_DT = eD_DT;
	}
	public String getSTD_DT() {
		return STD_DT;
	}
	public void setSTD_DT(String sTD_DT) {
		STD_DT = sTD_DT;
	}
	public BigDecimal getDEBIT() {
		return DEBIT;
	}
	public void setDEBIT(BigDecimal dEBIT) {
		DEBIT = dEBIT;
	}
	public List<String> getDATE_LIST() {
		return DATE_LIST;
	}
	public void setDATE_LIST(List<String> dATE_LIST) {
		DATE_LIST = dATE_LIST;
	}	
}

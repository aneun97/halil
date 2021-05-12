package com.example.demo.test.vo;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PayRcvSumVo {

	private BigDecimal PAY = new BigDecimal(0);
	private BigDecimal RCV = new BigDecimal(0);
	private String FROM_DT;
	private String TO_DT;
	
	public BigDecimal getPAY() {
		return PAY;
	}
	public void setPAY(BigDecimal pAY) {
		PAY = pAY;
	}
	public BigDecimal getRCV() {
		return RCV;
	}
	public void setRCV(BigDecimal rCV) {
		RCV = rCV;
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
}

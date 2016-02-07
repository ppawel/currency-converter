package com.example.ppawel.model;

import java.util.Date;

import org.hibernate.validator.constraints.NotBlank;

/**
 * Holds input data for the check exchange rate operation on the home page.
 * 
 * @author ppawel
 *
 */
public class CheckData {

	@NotBlank
	private String baseCurrency;

	@NotBlank
	private String targetCurrency;

	private Date date;

	public String getBaseCurrency() {
		return baseCurrency;
	}

	public void setBaseCurrency(String baseCurrency) {
		this.baseCurrency = baseCurrency;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getTargetCurrency() {
		return targetCurrency;
	}

	public void setTargetCurrency(String targetCurrency) {
		this.targetCurrency = targetCurrency;
	}

	@Override
	public String toString() {
		return "CheckData [baseCurrency=" + baseCurrency + ", targetCurrency=" + targetCurrency + ", date=" + date
				+ "]";
	}
}

package com.example.ppawel.model;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

/**
 * Holds input data for the convert operation on the home page.
 * 
 * @author ppawel
 *
 */
public class ConvertData {

	@NotBlank
	private String baseCurrency;

	@NotBlank
	private String targetCurrency;

	@NotNull
	@Range(min = 0)
	private BigDecimal amount;

	public String getBaseCurrency() {
		return baseCurrency;
	}

	public void setBaseCurrency(String baseCurrency) {
		this.baseCurrency = baseCurrency;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getTargetCurrency() {
		return targetCurrency;
	}

	public void setTargetCurrency(String targetCurrency) {
		this.targetCurrency = targetCurrency;
	}

}

package com.example.ppawel.service.impl;

import java.math.BigDecimal;
import java.util.Map;

/**
 * Response from the OER API containing latest or historical rates.
 * 
 * @see https://docs.openexchangerates.org/docs/latest-json
 * 
 * @author ppawel
 *
 */
public class OerRatesResponse {

	private long timestamp;

	private String base;

	private Map<String, BigDecimal> rates;

	public OerRatesResponse() {
	}

	public String getBase() {
		return base;
	}

	public void setBase(String base) {
		this.base = base;
	}

	public Map<String, BigDecimal> getRates() {
		return rates;
	}

	public void setRates(Map<String, BigDecimal> rates) {
		this.rates = rates;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
}

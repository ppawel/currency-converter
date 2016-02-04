package com.example.ppawel.service;

import java.math.BigDecimal;
import java.util.Date;

/**
 * API for currency operations - retrieving exchange rates, converting, ....
 * 
 * @author ppawel
 *
 */
public interface CurrencyOperations {

	/**
	 * Retrieves exchange rate between two given currencies on given day.
	 * 
	 * @param currencyCodeFrom
	 *            base currency
	 * @param currencyCodeTo
	 *            target currency
	 * @param timestamp
	 *            date to use
	 * @return exchange rate or <code>null</code> if not available
	 */
	BigDecimal getExchangeRate(String currencyCodeFrom, String currencyCodeTo, Date timestamp);

	/**
	 * Retrieves latest available exchange rate between two given currencies.
	 * 
	 * @param currencyCodeFrom
	 *            base currency
	 * @param currencyCodeTo
	 *            target currency
	 * @return exchange rate or <code>null</code> if not available
	 */
	BigDecimal getExchangeRate(String currencyCodeFrom, String currencyCodeTo);

}

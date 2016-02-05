package com.example.ppawel.service;

import java.math.BigDecimal;

/**
 * API for accessing currency operations and managing currency data providers.
 * 
 * @author ppawel
 *
 */
public interface CurrencyService extends CurrencyOperations {

	/**
	 * Attempts to convert given amount between two currencies using the latest
	 * available exchange rate.
	 * 
	 * @param amount
	 *            amount in (base currency) to convert
	 * @param baseCurrency
	 *            base currency to use
	 * @param targetCurrency
	 *            target currency to convert to
	 * @return amount in the target currency or <code>null</code> if exchange
	 *         rate is not available
	 */
	BigDecimal convert(BigDecimal amount, String baseCurrency, String targetCurrency);

	/**
	 * Sets a provider used for handling currency operations from now on.
	 * 
	 * @param provider
	 *            provider to use
	 */
	void setProvider(CurrencyDataProvider provider);
}
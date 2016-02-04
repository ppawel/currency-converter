package com.example.ppawel.service;

/**
 * API for accessing currency operations and managing currency data providers.
 * 
 * @author ppawel
 *
 */
public interface CurrencyService extends CurrencyOperations {

	/**
	 * Sets a provider used for handling currency operations from now on.
	 * 
	 * @param provider
	 *            provider to use
	 */
	void setProvider(CurrencyDataProvider provider);
}
package com.example.ppawel.service.impl;

import java.math.BigDecimal;
import java.util.List;

import com.example.ppawel.model.Currency;
import com.example.ppawel.service.CurrencyDataProvider;

/**
 * http://openexchangerates.org/
 * 
 * @author ppawel
 *
 */
public class OERCurrencyDataProvider implements CurrencyDataProvider {

	@Override
	public List<Currency> listCurrencies() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BigDecimal getExchangeRate(String currencyCodeFrom, String currencyCodeTo, long timestamp) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BigDecimal getExchangeRate(String currencyCodeFrom, String currencyCodeTo) {
		// TODO Auto-generated method stub
		return null;
	}

}

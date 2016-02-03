package com.example.ppawel.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ppawel.model.Currency;
import com.example.ppawel.service.CurrencyDataProvider;
import com.example.ppawel.service.CurrencyService;

@Service
public class CurrencyServiceImpl implements CurrencyService {

	@Autowired(required = false)
	private CurrencyDataProvider provider;

	public List<Currency> listCurrencies() {
		return provider.listCurrencies();
	}

	public BigDecimal getExchangeRate(String currencyCodeFrom, String currencyCodeTo, long timestamp) {
		return provider.getExchangeRate(currencyCodeFrom, currencyCodeTo, timestamp);
	}

	public BigDecimal getExchangeRate(String currencyCodeFrom, String currencyCodeTo) {
		return provider.getExchangeRate(currencyCodeFrom, currencyCodeTo);
	}

	@Override
	public void setProvider(CurrencyDataProvider provider) {
		this.provider = provider;
	}
}

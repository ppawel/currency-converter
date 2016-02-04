package com.example.ppawel.service.impl;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.ppawel.dao.UserQueryRepository;
import com.example.ppawel.model.UserQuery;
import com.example.ppawel.service.CurrencyDataProvider;
import com.example.ppawel.service.CurrencyService;

@Service
public class CurrencyServiceImpl implements CurrencyService {

	@Autowired
	private UserQueryRepository queryRepository;

	@Autowired(required = false)
	private CurrencyDataProvider provider;

	public BigDecimal getExchangeRate(String currencyCodeFrom, String currencyCodeTo, Date timestamp) {
		BigDecimal result = provider.getExchangeRate(currencyCodeFrom, currencyCodeTo, timestamp);
		saveUserQuery(currencyCodeFrom, currencyCodeTo, timestamp, result);
		return result;
	}

	public BigDecimal getExchangeRate(String currencyCodeFrom, String currencyCodeTo) {
		BigDecimal result = provider.getExchangeRate(currencyCodeFrom, currencyCodeTo);
		saveUserQuery(currencyCodeFrom, currencyCodeTo, null, result);
		return result;
	}

	@Override
	public void setProvider(CurrencyDataProvider provider) {
		this.provider = provider;
	}

	protected void saveUserQuery(String currencyCodeFrom, String currencyCodeTo, Date timestamp, BigDecimal rate) {
		UserQuery query = new UserQuery();
		query.setEmail(SecurityContextHolder.getContext().getAuthentication().getName());
		query.setBaseCurrency(currencyCodeFrom);
		query.setTargetCurrency(currencyCodeTo);
		query.setDate(timestamp);
		query.setRate(rate);
		queryRepository.save(query);
	}
}

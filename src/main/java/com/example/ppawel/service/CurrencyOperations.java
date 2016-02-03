package com.example.ppawel.service;

import java.math.BigDecimal;
import java.util.List;

import com.example.ppawel.model.Currency;

public interface CurrencyOperations {

	List<Currency> listCurrencies();

	BigDecimal getExchangeRate(String currencyCodeFrom, String currencyCodeTo, long timestamp);

	BigDecimal getExchangeRate(String currencyCodeFrom, String currencyCodeTo);

}

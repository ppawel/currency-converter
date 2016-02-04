package com.example.ppawel.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.ppawel.model.Currency;
import com.example.ppawel.service.CurrencyDataProvider;

/**
 * Currency data provider backed by http://openexchangerates.org/ API.
 * 
 * @author ppawel
 *
 */
@Service
public class OERCurrencyDataProvider implements CurrencyDataProvider {

	private String latestUrl = "https://openexchangerates.org/api/latest.json?app_id={appId}";

	private String historicalUrl = "https://openexchangerates.org/api/historical/{date}.json?app_id={appId}";

	private String appId = "e48903f3d4ee44e28398792694cd3b77";

	private RestTemplate restTemplate = new RestTemplate();

	@Override
	public List<Currency> listCurrencies() {

		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BigDecimal getExchangeRate(String currencyCodeFrom, String currencyCodeTo, Date timestamp) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("appId", appId);
		params.put("date", new SimpleDateFormat("yyyy-MM-dd").format(timestamp));
		OerRatesResponse response = restTemplate.getForObject(historicalUrl, OerRatesResponse.class, params);
		return response.getRates().get(currencyCodeTo);
	}

	@Override
	public BigDecimal getExchangeRate(String currencyCodeFrom, String currencyCodeTo) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("appId", appId);
		OerRatesResponse response = restTemplate.getForObject(latestUrl, OerRatesResponse.class, params);
		return response.getRates().get(currencyCodeTo);
	}

}

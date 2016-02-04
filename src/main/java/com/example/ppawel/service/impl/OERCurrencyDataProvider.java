package com.example.ppawel.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.example.ppawel.service.CurrencyDataProvider;

/**
 * Currency data provider backed by http://openexchangerates.org/ API.
 * 
 * @author ppawel
 *
 */
@Service
public class OERCurrencyDataProvider implements CurrencyDataProvider {

	private static final Logger log = LoggerFactory.getLogger(OERCurrencyDataProvider.class);

	private String latestUrl = "https://openexchangerates.org/api/latest.json?app_id={appId}&base={base}";

	private String historicalUrl = "https://openexchangerates.org/api/historical/{date}.json?app_id={appId}&base={base}";

	// TODO: externalize
	private String appId = "e48903f3d4ee44e28398792694cd3b77";

	private RestTemplate restTemplate = new RestTemplate();

	@Cacheable("historicalRates")
	@Override
	public BigDecimal getExchangeRate(String currencyCodeFrom, String currencyCodeTo, Date timestamp) {
		return callExchangeRateApi(historicalUrl, currencyCodeFrom, currencyCodeTo, timestamp);
	}

	@Cacheable("latestRates")
	@Override
	public BigDecimal getExchangeRate(String currencyCodeFrom, String currencyCodeTo) {
		return callExchangeRateApi(latestUrl, currencyCodeFrom, currencyCodeTo, null);
	}

	protected BigDecimal callExchangeRateApi(String url, String currencyCodeFrom, String currencyCodeTo,
			Date timestamp) {
		Map<String, String> params = new HashMap<String, String>();

		params.put("appId", appId);
		params.put("base", currencyCodeFrom);

		if (timestamp != null) {
			params.put("date", new SimpleDateFormat("yyyy-MM-dd").format(timestamp));
		}

		log.debug("Calling {} [params = {}]...", url, params);

		BigDecimal result = null;

		try {
			ResponseEntity<OerRatesResponse> response = restTemplate.getForEntity(url, OerRatesResponse.class, params);
			log.debug(" response = {}", response);
			if (response.hasBody()) {
				result = response.getBody().getRates().get(currencyCodeTo);
			}
		} catch (RestClientException e) {
			log.error("Problem calling API", e);
		}

		return result;
	}
}

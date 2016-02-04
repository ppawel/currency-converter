package com.example.ppawel.test.cucumber;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.example.ppawel.service.CurrencyDataProvider;
import com.example.ppawel.service.CurrencyService;
import com.example.ppawel.service.impl.OERCurrencyDataProvider;

import cucumber.api.Format;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

@ContextConfiguration(classes = TestConfig.class)
public class ExchangeRatesStepdefs {

	@Autowired
	private CurrencyService currencyService;

	@Autowired
	private OERCurrencyDataProvider oerProvider;

	private CurrencyDataProvider provider;

	private String baseCurrency;

	private String targetCurrency;

	private BigDecimal rate;

	@Given("^mock currency data provider$")
	public void mock_currency_data_provider() throws Throwable {
		provider = mock(CurrencyDataProvider.class);
		when(provider.getExchangeRate("USD", "EUR")).thenReturn(new BigDecimal(1.25));
		currencyService.setProvider(provider);
	}

	@Given("^OER currency data provider$")
	public void oer_currency_data_provider() throws Throwable {
		currencyService.setProvider(oerProvider);
	}

	@Given("^base currency (\\w+)$")
	public void base_currency(String currencyCode) throws Throwable {
		baseCurrency = currencyCode;
	}

	@Given("^target currency (\\w+)$")
	public void target_currency(String currencyCode) throws Throwable {
		targetCurrency = currencyCode;
	}

	@When("^I ask for current exchange rate$")
	public void i_ask_for_current_exchange_rate() throws Throwable {
		rate = currencyService.getExchangeRate(baseCurrency, targetCurrency);
	}

	@Then("^I should get ([\\d\\.]+)$")
	public void i_should_get(BigDecimal rate) throws Throwable {
		assertThat(this.rate, is(rate));
	}

	@Then("^I should get a reasonable result$")
	public void i_should_get_reasonable() throws Throwable {
		assertThat(this.rate, greaterThan(new BigDecimal(0.5)));
		assertThat(this.rate, lessThan(new BigDecimal(1.5)));
	}

	@When("^I ask for exchange rate on (.*)$")
	public void i_ask_for_exchange_rate_on(@Format("yyyy-MM-dd") Date date) throws Throwable {
		rate = currencyService.getExchangeRate(baseCurrency, targetCurrency, date);
	}
}

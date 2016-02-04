package com.example.ppawel.test.cucumber;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.example.ppawel.service.CurrencyDataProvider;
import com.example.ppawel.service.CurrencyService;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

@ContextConfiguration(classes = TestConfig.class)
public class ExchangeRatesStepdefs {

	@Autowired
	private CurrencyService currencyService;

	private CurrencyDataProvider provider;

	private String baseCurrency;

	private String targetCurrency;

	private BigDecimal rate;

	@Before
	public void init() {
		provider = mock(CurrencyDataProvider.class);
		when(provider.getExchangeRate("USD", "EUR")).thenReturn(new BigDecimal(1.25));
		currencyService.setProvider(provider);
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

	@Then("^I should get (.*)$")
	public void i_should_get(BigDecimal rate) throws Throwable {
		assertThat(this.rate, is(rate));
	}
}

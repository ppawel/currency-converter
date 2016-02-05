package com.example.ppawel.test.cucumber;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

import com.example.ppawel.service.CurrencyDataProvider;
import com.example.ppawel.service.CurrencyService;
import com.example.ppawel.service.UserService;

import cucumber.api.Format;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

@ContextConfiguration(classes = TestConfig.class)
@TestPropertySource(locations = "classpath:test.properties")
public class ExchangeRatesStepdefs {

	@Autowired
	private CurrencyService currencyService;

	@Autowired
	private UserService userService;

	@Autowired
	@Qualifier("OERCurrencyDataProvider")
	private CurrencyDataProvider oerProvider;

	private CurrencyDataProvider provider;

	private String baseCurrency;

	private String targetCurrency;

	private BigDecimal result;

	private int count;

	private BigDecimal amount;

	@Before
	public void init() {
		SecurityContextHolder.getContext().setAuthentication(new TestingAuthenticationToken("test@test.pl", ""));
	}

	@Given("^mock currency data provider$")
	public void mock_currency_data_provider() throws Throwable {
		provider = mock(CurrencyDataProvider.class);
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
		result = currencyService.getExchangeRate(baseCurrency, targetCurrency);
	}

	@Then("^I should get ([\\d\\.]+)$")
	public void i_should_get(BigDecimal rate) throws Throwable {
		assertThat(this.result, is(rate));
	}

	@Then("^I should get no result$")
	public void i_should_get_no_result() throws Throwable {
		assertThat(this.result, nullValue());
	}

	@Then("^I should get a reasonable result$")
	public void i_should_get_reasonable() throws Throwable {
		assertThat(this.result, greaterThan(new BigDecimal(0.5)));
		assertThat(this.result, lessThan(new BigDecimal(1.5)));
	}

	@When("^I ask for exchange rate on (.*)$")
	public void i_ask_for_exchange_rate_on(@Format("yyyy-MM-dd") Date date) throws Throwable {
		result = currencyService.getExchangeRate(baseCurrency, targetCurrency, date);
	}

	@When("^I ask (\\d+) times for current exchange rate$")
	public void i_ask_times_for_current_exchange_rate(int count) throws Throwable {
		this.count = count;
		for (int i = 0; i < count; i++) {
			currencyService.getExchangeRate(baseCurrency, targetCurrency);
		}
	}

	@Then("^I should get at least (\\d+) user queries$")
	public void i_should_get_user_queries(int arg1) throws Throwable {
		assertThat(userService.listUserQueries().size(), greaterThanOrEqualTo(count));
	}

	@Given("^amount (.*)$")
	public void amount(BigDecimal value) throws Throwable {
		amount = value;
	}

	@Given("^exchange rate (.*)$")
	public void exchange_rate(BigDecimal value) throws Throwable {
		when(provider.getExchangeRate("USD", "EUR")).thenReturn(value);
	}

	@When("^I ask for conversion$")
	public void i_ask_for_conversion() throws Throwable {
		result = currencyService.convert(amount, baseCurrency, targetCurrency);
	}

}

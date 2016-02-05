package com.example.ppawel.test.cucumber;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

import java.util.Date;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

import com.example.ppawel.model.User;
import com.example.ppawel.model.UserRegistrationData;
import com.example.ppawel.service.UserService;

import cucumber.api.Format;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

@ContextConfiguration(classes = TestConfig.class)
@TestPropertySource(locations = "classpath:test.properties")
public class UsersStepdefs {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserService userService;

	private UserRegistrationData data = new UserRegistrationData();

	private User user;

	private Authentication loggedIn;

	private AuthenticationException exception;

	private Set<ConstraintViolation<?>> errors;

	@Given("^e-mail (.*)$")
	public void e_mail(String email) {
		data.setEmail(email);
	}

	@Given("^country (.*)$")
	public void country(String value) {
		data.setCountryCode(value);
	}

	@Given("^street (.*)$")
	public void street(String value) {
		data.setStreet(value);
	}

	@Given("^city (.*)$")
	public void city(String value) {
		data.setCity(value);
	}

	@Given("^password (.*)$")
	public void password(String password) {
		data.setPassword(password);
	}

	@Given("^date of birth (.*)$")
	public void date_of_birth(@Format("yyyy-MM-dd") Date date) throws Throwable {
		data.setBirthDate(date);
	}

	@When("^I try to register$")
	public void i_try_to_register() throws Throwable {
		try {
			user = userService.register(data);
		} catch (ConstraintViolationException e) {
			errors = e.getConstraintViolations();
		}
	}

	@Then("^I should be registered$")
	public void registered() throws Throwable {
		assertThat(user, notNullValue());
		assertThat(user.getId(), notNullValue());
		assertThat(user.getEmail(), is(data.getEmail()));
	}

	@When("^I try to login$")
	public void i_try_to_login() {
		Authentication authentication = new UsernamePasswordAuthenticationToken(data.getEmail(), data.getPassword());
		try {
			loggedIn = authenticationManager.authenticate(authentication);
		} catch (AuthenticationException e) {
			exception = e;
		}
	}

	@Then("^I should get error messages$")
	public void i_should_get_error_messages() throws Throwable {
		assertThat(errors, notNullValue());
	}

	@Then("^I should be logged in$")
	public void i_should_be_logged_in() throws Throwable {
		assertThat(loggedIn, notNullValue());
		assertThat(exception, nullValue());
	}

	@Then("^I should fail to log in$")
	public void i_should_fail_to_log_in() throws Throwable {
		assertThat(loggedIn, nullValue());
		assertThat(exception, notNullValue());
		assertThat(exception, instanceOf(BadCredentialsException.class));
	}

}

package com.example.ppawel.test.cucumber;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.example.ppawel.model.User;
import com.example.ppawel.model.UserRegistrationData;
import com.example.ppawel.service.UserService;
import com.example.ppawel.spring.Application;

import cucumber.api.Format;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

@ContextConfiguration(classes = Application.class)
public class UsersStepdefs {

	@Autowired
	private UserService userService;

	private UserRegistrationData data = new UserRegistrationData();

	private User user;

	@Given("^e-mail (.*)$")
	public void e_mail(String email) {
		data.setEmail(email);
	}

	@Given("^date of birth (.*)$")
	public void date_of_birth(@Format("yyyy-MM-dd") Date date) throws Throwable {
		data.setBirthDate(date);
	}

	@When("^I try to register$")
	public void i_try_to_register() throws Throwable {
		user = userService.register(data);
	}

	@Then("^I should be successful$")
	public void i_should_be_successful() throws Throwable {
		assertThat(user, notNullValue());
		assertThat(user.getId(), notNullValue());
		assertThat(user.getEmail(), is(data.getEmail()));
	}

}

package com.example.ppawel.model;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Data transfer object for data related to new user registration.
 * 
 * @author ppawel
 *
 */
public class UserRegistrationData implements Serializable {

	private static final long serialVersionUID = -6494230977938111214L;

	@NotBlank
	@Email
	private String email;

	@NotBlank
	private String password;
	private Date birthDate;

	private String street;


	private String city;


	private String countryCode;

	public UserRegistrationData() {
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	@Override
	public String toString() {
		return "UserRegistrationData [email=" + email + "]";
	}
}
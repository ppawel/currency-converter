package com.example.ppawel.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 * Holds information of a single exchange rate query.
 * 
 * @author ppawel
 *
 */
@Entity
public class UserQuery implements Serializable {

	private static final long serialVersionUID = 9193245354648768670L;

	@Id
	@GeneratedValue
	private Long id;

	/**
	 * Date of execution of this query. By default it's the current date at the
	 * time of creation of this object.
	 */
	private Date executionDate = new Date();

	/**
	 * E-mail of the user.
	 */
	@NotNull
	private String email;

	/**
	 * Base currency.
	 */
	private String baseCurrency;

	/**
	 * Target currency.
	 */
	private String targetCurrency;

	/**
	 * Date of exchange rate as queried by the user (<code>null</code> means
	 * "latest rate" was queried).
	 */
	private Date date;

	@Column(precision = 12, scale = 9)
	private BigDecimal rate;

	public UserQuery() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getExecutionDate() {
		return executionDate;
	}

	public void setExecutionDate(Date executionDate) {
		this.executionDate = executionDate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBaseCurrency() {
		return baseCurrency;
	}

	public void setBaseCurrency(String baseCurrency) {
		this.baseCurrency = baseCurrency;
	}

	public String getTargetCurrency() {
		return targetCurrency;
	}

	public void setTargetCurrency(String targetCurrency) {
		this.targetCurrency = targetCurrency;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public BigDecimal getRate() {
		return rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserQuery other = (UserQuery) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}

package com.example.ppawel.model.validation;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Implements "reasonable date" validation.
 * 
 * @author ppawel
 *
 */
public class ReasonableDateValidator implements ConstraintValidator<ReasonableDate, Date> {

	private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

	private Date from;

	@Override
	public void initialize(ReasonableDate constraintAnnotation) {
		if (constraintAnnotation.from() != null) {
			try {
				from = DATE_FORMAT.parse(constraintAnnotation.from());
			} catch (ParseException e) {
				throw new IllegalArgumentException("Invalid date: " + constraintAnnotation.from());
			}
		}
	}

	@Override
	public boolean isValid(Date value, ConstraintValidatorContext context) {
		if (from != null && value.before(from)) {
			return false;
		}
		// Check if date is from the future
		if (value.after(new Date())) {
			return false;
		}
		return true;
	}

}

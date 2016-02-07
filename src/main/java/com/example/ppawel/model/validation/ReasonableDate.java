package com.example.ppawel.model.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.ReportAsSingleViolation;
import javax.validation.constraints.NotNull;

/**
 * Defines constraint for a reasonable date (date in given range, inclusive and
 * not from the future).
 * 
 * @author ppawel
 *
 */
@NotNull
@ReportAsSingleViolation
@Target({ ElementType.FIELD, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ReasonableDateValidator.class)
public @interface ReasonableDate {
	String message() default "Date should be between {from} and now";

	/**
	 * String in the "yyyy-MM-dd" format.
	 */
	String from();

	Class[] groups() default {};

	Class[] payload() default {};
}

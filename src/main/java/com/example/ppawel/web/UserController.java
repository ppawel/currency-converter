package com.example.ppawel.web;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.ppawel.model.UserAlreadyExistsException;
import com.example.ppawel.model.UserRegistrationData;
import com.example.ppawel.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model) {
		model.addAttribute("data", new UserRegistrationData());
		return "login";
	}

	@RequestMapping(path = "/register", method = RequestMethod.POST)
	public String register(Model model, @ModelAttribute("data") @Valid UserRegistrationData data,
			BindingResult result) {

		if (result.hasErrors()) {
			model.addAttribute("registered", false);
			return "login";
		}

		try {
			userService.register(data);
			// If registration successful, reset form data
			model.addAttribute("data", new UserRegistrationData());
		} catch (UserAlreadyExistsException e) {
			result.addError(new FieldError("user", "email", "User already exists"));
		} catch (ConstraintViolationException e) {
			// Convert Bean Validation exceptions to Spring errors so they
			// are shown to the user
			e.getConstraintViolations().forEach(cv -> {
				result.addError(new FieldError("user", cv.getPropertyPath().toString(), cv.getMessage()));
			});
		}

		model.addAttribute("registered", !result.hasErrors());

		return "login";
	}
}

package com.example.ppawel.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
		if (!result.hasErrors()) {
			userService.register(data);
		}

		model.addAttribute("registered", !result.hasErrors());
		return "login";
	}
}

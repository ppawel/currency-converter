package com.example.ppawel.web;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.ppawel.service.CurrencyService;
import com.example.ppawel.service.UserService;

@Controller
public class HomeController {

	private static final Logger log = LoggerFactory.getLogger(HomeController.class);

	private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

	@Autowired
	private CurrencyService currencyService;

	@Autowired
	private UserService userService;

	@RequestMapping(value = { "/", "/home" }, method = RequestMethod.GET)
	public String home(Model model, @RequestParam(value = "base", required = false) String baseCurrency,
			@RequestParam(value = "target", required = false) String targetCurrency,
			@RequestParam(value = "date", required = false) String dateString) {

		Date date = null;

		if (dateString != null) {
			try {
				date = DATE_FORMAT.parse(dateString);
			} catch (ParseException e) {
				// Ignore
			}
		}

		log.debug("base = {}, target = {}, date = {}", baseCurrency, targetCurrency, date);

		BigDecimal rate = null;
		if (baseCurrency != null && targetCurrency != null) {
			if (date == null) {
				// Get latest
				rate = currencyService.getExchangeRate(baseCurrency, targetCurrency);
			} else {
				rate = currencyService.getExchangeRate(baseCurrency, targetCurrency, date);
			}
		}

		model.addAttribute("date", date != null ? DATE_FORMAT.format(date) : "");
		model.addAttribute("rate", rate);
		model.addAttribute("queries", userService.listUserQueries());

		return "home";
	}

}

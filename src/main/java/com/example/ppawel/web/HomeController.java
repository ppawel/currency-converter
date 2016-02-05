package com.example.ppawel.web;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.ppawel.model.CheckData;
import com.example.ppawel.model.ConvertData;
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

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		// Convert empty string to null Date instead of failing data binding
		binder.registerCustomEditor(Date.class, new CustomDateEditor(DATE_FORMAT, true));
	}

	@RequestMapping(value = { "/", "/home" }, method = RequestMethod.GET)
	public String home(Model model) {
		model.addAttribute("convertData", new ConvertData());
		model.addAttribute("checkData", new CheckData());
		model.addAttribute("queries", userService.listUserQueries());
		return "home";
	}

	@RequestMapping(value = "/check", method = RequestMethod.POST)
	public String check(Model model, @ModelAttribute("checkData") @Valid CheckData data, BindingResult result) {
		model.addAttribute("convertData", new ConvertData());
		model.addAttribute("queries", userService.listUserQueries());

		if (result.hasErrors()) {
			return "home";
		}

		BigDecimal rate = null;
		if (data.getDate() == null) {
			// Get latest
			rate = currencyService.getExchangeRate(data.getBaseCurrency(), data.getTargetCurrency());
		} else {
			rate = currencyService.getExchangeRate(data.getBaseCurrency(), data.getTargetCurrency(), data.getDate());
		}

		if (rate != null) {
			model.addAttribute("rate", rate);
		} else {
			model.addAttribute("checkFailed", true);
		}

		return "home";
	}

	@RequestMapping(value = "/convert", method = RequestMethod.POST)
	public String convert(Model model, @ModelAttribute("convertData") @Valid ConvertData data, BindingResult result) {
		model.addAttribute("checkData", new CheckData());
		model.addAttribute("queries", userService.listUserQueries());

		if (result.hasErrors()) {
			return "home";
		}

		BigDecimal convertedAmount = currencyService.convert(data.getAmount(), data.getBaseCurrency(),
				data.getTargetCurrency());

		if (convertedAmount != null) {
			model.addAttribute("convertedAmount", convertedAmount);
		} else {
			model.addAttribute("conversionFailed", true);
		}

		return "home";
	}

	private void check(Model model, String baseCurrency, String targetCurrency, String dateString) {
		Date date = null;

		if (dateString != null) {
			try {
				date = DATE_FORMAT.parse(dateString);
			} catch (ParseException e) {
				// Ignore
			}
		}

	}

}

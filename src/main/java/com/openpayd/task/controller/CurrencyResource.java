package com.openpayd.task.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openpayd.task.dto.CurrencyDTO;
import com.openpayd.task.entity.Client;
import com.openpayd.task.services.CurrencyService;

import lombok.RequiredArgsConstructor;

@CrossOrigin
@RestController
@EnableAutoConfiguration
@RequestMapping("/currency")
@RequiredArgsConstructor
public class CurrencyResource {

	@Autowired
	CurrencyService currencyService;
	
	 @GetMapping("/dailyCurrency/{baseCurrency}")
	    public CurrencyDTO getDailyCurrency(@PathVariable("baseCurrency") String baseCurrency) {
	        return currencyService.getDailyCurrency(baseCurrency);
	    }
	 
}

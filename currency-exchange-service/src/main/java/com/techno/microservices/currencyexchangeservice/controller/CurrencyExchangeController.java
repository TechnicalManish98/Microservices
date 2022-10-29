package com.techno.microservices.currencyexchangeservice.controller;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.techno.microservices.currencyexchangeservice.entity.CurrencyExchange;
import com.techno.microservices.currencyexchangeservice.repository.CurrencyExchangeRepository;

@RestController
public class CurrencyExchangeController {
	
	Logger logger = LoggerFactory.getLogger(CurrencyExchangeController.class);
	
	@Autowired
	private Environment environment;
	
	@Autowired
	private CurrencyExchangeRepository currencyExchangeRepository;
	
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public CurrencyExchange retrieveExchange(@PathVariable String from, @PathVariable String to){
			
		logger.info("retrieveExchangeValue called with {} to {}", from, to);
		
		CurrencyExchange currencyExchange = currencyExchangeRepository.findByFromAndTo(from,to);
		
		if(currencyExchange==null) {
			throw new RuntimeException("Unable to find data");
		}
		
		//CurrencyExchange currencyExchange = new CurrencyExchange(1l,from, to, BigDecimal.valueOf(50));
		currencyExchange.setEnvironment(environment.getProperty("local.server.port"));
		
		
		return currencyExchange;
	}

}

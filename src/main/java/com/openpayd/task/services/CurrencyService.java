package com.openpayd.task.services;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Objects;
import com.openpayd.task.dto.CurrencyDTO;
import com.openpayd.task.entity.CurrencyRate;
import com.openpayd.task.exception.GeneralException;
import com.openpayd.task.repository.CurrencyRateRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class CurrencyService {
	
	@Value("${client.ratesapiurl}")
	private String ratesapiurl;
	
	@Autowired
    RestTemplate restTemplate;
	
	@Autowired
	CurrencyRateRepository currencyRateRepository;
		
	private static final String DATE_FORMAT = "yyyy-MM-dd";
	
	public CurrencyDTO getDailyCurrency(String base) {
		Date date= new Date();
		DateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
		String now = formatter.format(date);
		
		CurrencyRate rate= new CurrencyRate();
		CurrencyDTO currencyDTO= restTemplate.getForObject(ratesapiurl+now+"?base="+base, CurrencyDTO.class);
		BeanUtils.copyProperties(currencyDTO.getRates(), rate);
		try {
			rate.setCurrencyDate(formatter.parse(currencyDTO.getDate()));
		} catch (ParseException e) {
			throw new GeneralException(e.getMessage());
		}
		rate.setBaseCurrency(base);		
		currencyRateRepository.save(rate);		
		return currencyDTO;
	}
	
	
	
	public BigDecimal exchange(String currency, BigDecimal amount) {

		Optional<CurrencyRate> rateOpt= currencyRateRepository.findFirstByOrderByCurrencyDateDesc();
		if(!rateOpt.isPresent())
		throw new GeneralException("Currency Rate table is empty");
			
		return amount.multiply(BigDecimal.valueOf(getCurrency(currency, rateOpt.get())));
	}
	
	private Double getCurrency(String currency,CurrencyRate rate) {

            if(Objects.equal("gbp",currency))  return rate.getGbp();
                    
            if(Objects.equal("eur",currency))  return rate.getEur();
       
            if(Objects.equal("tl",currency))  return rate.getTl();
            
            if(Objects.equal("jpy",currency))  return rate.getJpy();
            
             if(Objects.equal("rub",currency))  return rate.getRub();
            
             if(Objects.equal("usd",currency))  return rate.getUsd();
             
             throw new GeneralException("Unknown currency");
 
	}
}

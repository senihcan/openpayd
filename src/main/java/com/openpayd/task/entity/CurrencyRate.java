package com.openpayd.task.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Entity
@Data
public class CurrencyRate extends BaseEntity {
	/**
	* 
	*/
	private static final long serialVersionUID = 2047325056820688720L;

	@Id
	@GeneratedValue
	private Long id;

	private String baseCurrency;

	private Double gbp;

	private Double eur;

	private Double tl;

	private Double jpy;

	private Double rub;

	private Double usd;

	private Date currencyDate;
}

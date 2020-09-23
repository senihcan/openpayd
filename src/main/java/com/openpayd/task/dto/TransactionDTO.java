package com.openpayd.task.dto;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;

@Data
public class TransactionDTO {

	private Long id;
	
	private BigDecimal debit;
	
	private BigDecimal credit;
	
	private BigDecimal amount;
	
	private String currency;
	
	private String transactionMessage;
	
	private Date created;
	
	private String accountType;
	
	private Long fromAccountId;

	private Long toAccountId;
}

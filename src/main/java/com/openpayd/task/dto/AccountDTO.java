package com.openpayd.task.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class AccountDTO {
	 private Long id;
		
		private String accountType;//Current or Save
		
		
		private BigDecimal balance;
		
		private String currency;
		
		private Long clientId;
}

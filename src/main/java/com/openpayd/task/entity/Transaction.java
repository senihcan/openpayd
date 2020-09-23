package com.openpayd.task.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
public class Transaction extends BaseEntity{/**
	 * 
	 */
	private static final long serialVersionUID = -5137451267132138657L;
	@Id
    @GeneratedValue
	private Long id;
	
	private BigDecimal debit;
	
	private BigDecimal credit;
	
	private BigDecimal amount;
	
	private String transactionMessage;
	
	private Date created;
	
	private String accountType;
	
	private String currency;
	
	@ManyToOne
	@JoinColumn(name = "from_account_id")
	private Account fromAccount;

	@ManyToOne
	@JoinColumn(name = "to_account_id")
	private Account toAccount;

}

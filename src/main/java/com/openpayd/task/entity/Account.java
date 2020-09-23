package com.openpayd.task.entity;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity
@Data
public class Account extends BaseEntity{/**
	 * 
	 */
	private static final long serialVersionUID = -7373422162611539689L;
	@Id
    @GeneratedValue
    private Long id;
	
	private String accountType;//current or save
	
	
	private BigDecimal balance;
	
	private String currency;
	
	@ManyToOne
	@JoinColumn(name = "client_id")
	private Client client;
	
	 @OneToMany(fetch = FetchType.LAZY)
	 @JoinColumn(name="to_account_id")
	 private List<Transaction> recieveTransactionList;
	
	 
	 @OneToMany(fetch = FetchType.LAZY)
	 @JoinColumn(name="from_account_id")
	 private List<Transaction> sendTransactionList;
}

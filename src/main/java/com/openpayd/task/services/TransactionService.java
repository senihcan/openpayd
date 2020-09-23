package com.openpayd.task.services;

import java.math.BigDecimal;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Objects;
import com.openpayd.task.dto.TransactionDTO;
import com.openpayd.task.entity.Account;
import com.openpayd.task.entity.Transaction;
import com.openpayd.task.exception.GeneralException;
import com.openpayd.task.repository.TransactionRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class TransactionService {
	
	@Autowired
	TransactionRepository transactionRepository;
	
	@Autowired
	CurrencyService currencyService;
	
	@Autowired
	AccountService accountService;
	

	public Transaction createTransaction(TransactionDTO transactionDto) {
		Transaction transaction= new Transaction();
		
		Account fromAccount=accountService.findAccountById(transactionDto.getFromAccountId());//db
		
		Account toAccount=accountService.findAccountById(transactionDto.getToAccountId());//cr
		
		
		
		fromAccount.setBalance(fromAccount.getBalance().subtract(getCurrencyAmount(transactionDto.getAmount(), transactionDto.getCurrency())));
		
		toAccount.setBalance(toAccount.getBalance().add(getCurrencyAmount(transactionDto.getAmount(), transactionDto.getCurrency())));
		
		BeanUtils.copyProperties(transactionDto, transaction);
		
		transaction.setToAccount(toAccount);
		
		transaction.setFromAccount(fromAccount);
		
		transactionRepository.save(transaction);
		
		accountService.updateAccount(fromAccount);
		
		accountService.updateAccount(toAccount);
		
		return transaction;
	}
	
	
	private BigDecimal getCurrencyAmount(BigDecimal amount,String currency) {
		
		return currencyService.exchange(currency, amount);
	}

}

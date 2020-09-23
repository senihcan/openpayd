package com.openpayd.task.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.openpayd.task.dto.AccountDTO;
import com.openpayd.task.entity.Account;
import com.openpayd.task.exception.GeneralException;
import com.openpayd.task.exception.NotFoundException;
import com.openpayd.task.repository.AccountRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class AccountService {
	@Autowired
	AccountRepository accountRepository;
	
	
	@Autowired
	ClientService clientService;
	
	public Account findAccountById(Long id) {
		 Optional<Account> account= accountRepository.findById(id);
		if(account.isPresent()) {
			return account.get();
		}
		else {
			throw new NotFoundException("Account not found with id : " + id);
		}		
	}

	public List<Account> getAllAccount(){		
		return findAll(accountRepository.findAll().iterator());		
	}
	
	private List<Account> findAll(Iterator<Account> accountIter) {
		List<Account> accounts= new ArrayList<>();
		while(accountIter.hasNext()) {
			if(Objects.isNull(accountIter.next().getEndDate())) {
				accounts.add(accountIter.next());
			}
		}
		return accounts;
	}
	
	public Account createAccount(AccountDTO accountDTO) {
		try {
			Account account=new Account();
			BeanUtils.copyProperties(accountDTO, account);
			if(Objects.nonNull(accountDTO.getClientId())) {
			account.setClient(clientService.findClientById(accountDTO.getClientId()));
			}
			
		return accountRepository.save(account);
		}
		catch (Exception e) {
			log.error(e.getMessage());
			throw new GeneralException("Error occured when create Account ");
		}
	}
	
	public Account updateAccount(Account account) {
		try {
			if(Objects.isNull(account.getId())) {
				throw new GeneralException("Account not found for update");
			}
		return accountRepository.save(account);
		}
		catch (Exception e) {
			log.error(e.getMessage());
			throw new GeneralException("Error occured when update Account with id : " + account.getId());
		}
	}
	
	public void deleteAccountById(Long id) {
		try {
		accountRepository.deleteById(id);
		}
		catch (Exception e) {
			log.error(e.getMessage());
			throw new GeneralException("Error occured when delete Account with id: " + id);
		}
	}

}

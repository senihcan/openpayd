package com.openpayd.task.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openpayd.task.dto.AccountDTO;
import com.openpayd.task.entity.Account;
import com.openpayd.task.services.AccountService;

import lombok.RequiredArgsConstructor;

@CrossOrigin
@RestController
@EnableAutoConfiguration
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountResource {

	@Autowired AccountService accountService;
	
	 @PostMapping
	    public Account createAccount(@RequestBody AccountDTO accountDTO) {
	        return accountService.createAccount(accountDTO);
	    }

	    @GetMapping("/{accountId}")
	    public Account getAccountById(@PathVariable("accountId") Long accountId) {
	        return accountService.findAccountById(accountId);
	    }

	    @DeleteMapping("/{accountId}")
	    public void deleteAccount(@PathVariable("accountId") Long accountId) {
	       accountService.deleteAccountById(accountId);
	    }

	    @PutMapping
	    public Account putAccount(@RequestBody Account Account) {

	        return accountService.updateAccount(Account);
	    }
	    
	    @GetMapping
	    public List<Account> getAllAccount(){
	    	return accountService.getAllAccount();
	    }
	
}

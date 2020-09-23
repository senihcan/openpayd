package com.openpayd.task.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openpayd.task.dto.ClientDTO;
import com.openpayd.task.dto.TransactionDTO;
import com.openpayd.task.entity.Client;
import com.openpayd.task.entity.Transaction;
import com.openpayd.task.services.TransactionService;

import lombok.RequiredArgsConstructor;

@CrossOrigin
@RestController
@EnableAutoConfiguration
@RequestMapping("/transaction")
@RequiredArgsConstructor
public class TransactionResource {

	@Autowired
	TransactionService transactionService;
	
	@PostMapping
    public Transaction createTransaction(@RequestBody TransactionDTO transactionDTO) {
        return transactionService.createTransaction(transactionDTO);
    }
	
}

package com.openpayd.task.controller;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.web.bind.annotation.RequestMethod;

import com.openpayd.task.dto.TransactionDTO;
import com.openpayd.task.entity.Transaction;
import com.openpayd.task.services.TransactionService;
import com.openpayd.task.utils.ServiceUtil;

@RunWith(SpringRunner.class)
@WebMvcTest(value = TransactionResource.class, secure = false)
@ContextConfiguration(classes = { TransactionResource.class })
public class TransactionResourceTests {
	@MockBean
	TransactionService transactionService;
	
	@Autowired
	MockMvc mockMvc;
	
	private List<Transaction> transactions;
	private boolean isSetup = false;
	private String reqMap = "/transaction/";
	
	
	@Before
	public void setup() {
		if (isSetup)
			return;
		transactions = ServiceUtil.generateTransaction(1);
		isSetup = true;
	}
	
	@Test
	public void createTRest() {
		Transaction transaction = transactions.get(0);
		TransactionDTO transactionDTO=new TransactionDTO();
				BeanUtils.copyProperties(transaction, transactionDTO);
		Mockito.when(transactionService.createTransaction(Mockito.any(TransactionDTO.class))).thenReturn(transaction);
		String transactionAsString = ServiceUtil.objToJsonString(transaction);
		RequestBuilder rb = ServiceUtil.requestBuilder(reqMap, RequestMethod.POST, transactionAsString);
		String response = performRB(rb);
		ServiceUtil.tryJsonObjectAssert(transaction, response);
	}
	
	public String performRB(RequestBuilder rb) {
		try {
			return mockMvc.perform(rb).andReturn().getResponse().getContentAsString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}

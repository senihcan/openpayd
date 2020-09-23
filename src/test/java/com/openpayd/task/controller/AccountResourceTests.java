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

import com.openpayd.task.dto.AccountDTO;
import com.openpayd.task.dto.ClientDTO;
import com.openpayd.task.entity.Account;
import com.openpayd.task.entity.Client;
import com.openpayd.task.utils.ServiceUtil;

@RunWith(SpringRunner.class)
@WebMvcTest(value = AccountResource.class, secure = false)
@ContextConfiguration(classes = { AccountResource.class })
public class AccountResourceTests {
	@MockBean
	AccountResource accountService;
	
	@Autowired
	MockMvc mockMvc;
	
	private List<Account> accounts;
	private boolean isSetup = false;
	private String reqMap = "/account/";
	
	
	@Before
	public void setup() {
		if (isSetup)
			return;
		accounts = ServiceUtil.generateAccount(1);
		isSetup = true;
	}
	@Test
	public void getClientById() {
		Account account = accounts.get(0);
		Mockito.when(accountService.getAccountById(Mockito.any(Long.class))).thenReturn(account);
		RequestBuilder rb = ServiceUtil.requestBuilder(reqMap + account.getId(), RequestMethod.GET, null);
		String response = performRB(rb);
		ServiceUtil.tryJsonObjectAssert(account, response);
	}

	@Test
	public void createTRest() {
		Account account = accounts.get(0);
		AccountDTO accountDTO=new AccountDTO();
				BeanUtils.copyProperties(account, accountDTO);
		Mockito.when(accountService.createAccount(Mockito.any(AccountDTO.class))).thenReturn(account);
		String accountAsString = ServiceUtil.objToJsonString(account);
		RequestBuilder rb = ServiceUtil.requestBuilder(reqMap, RequestMethod.POST, accountAsString);
		String response = performRB(rb);
		ServiceUtil.tryJsonObjectAssert(account, response);
	}
	
	
	@Test
	public void updateTRest() {
		Account account = accounts.get(0);
		AccountDTO accountDTO=new AccountDTO();
				BeanUtils.copyProperties(account, accountDTO);
		Mockito.when(accountService.putAccount(Mockito.any(Account.class))).thenReturn(account);
		String accountAsString = ServiceUtil.objToJsonString(account);
		RequestBuilder rb = ServiceUtil.requestBuilder(reqMap, RequestMethod.PUT, accountAsString);
		String response = performRB(rb);
		ServiceUtil.tryJsonObjectAssert(account, response);
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

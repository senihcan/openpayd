package com.openpayd.task.utils;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.json.JSONException;
import org.json.JSONObject;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.beans.BeanUtils;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.openpayd.task.dto.AccountDTO;
import com.openpayd.task.dto.ClientDTO;
import com.openpayd.task.dto.TransactionDTO;
import com.openpayd.task.entity.Account;
import com.openpayd.task.entity.Client;
import com.openpayd.task.entity.Transaction;

public class ServiceUtil {
	private static ObjectMapper om;
	private static Random randomGenerator = new Random();
	
	
	public static List<Client> generateClientss(int counts) {
		List<Client> clients = new ArrayList<>();
		for (int i = 0; i < counts; i++) {
			Client client= new Client();
			BeanUtils.copyProperties(generateClientModel(), client);
			clients.add(client);
		}
		return clients;
	}
	
	public static ClientDTO generateClientModel() {
		int base = (int) Math.pow(10, 1);
        
		ClientDTO client  = new ClientDTO();
		client.setId((long) (base + Math.random() * base * 9));
		client.setClientName("Senih");
		client.setClientFamilyName("Kardes");
		client.setAdressLineOne("test adres");
		client.setAdressLineTwo("Test Adres 2");
		client.setCity("Istanbul");
		client.setCountry("Turkey");
		
		return client;
	}
	
	
	
	public static List<Account> generateAccount(int counts) {
		List<Account> accounts = new ArrayList<>();
		for (int i = 0; i < counts; i++) {
			Account account= new Account();
			BeanUtils.copyProperties(generateClientModel(), account);
			accounts.add(account);
		}
		return accounts;
	}
	
	public static AccountDTO generateAccountModel() {
		int base = (int) Math.pow(10, 1);
        
		AccountDTO account  = new AccountDTO();
		account.setId((long) (base + Math.random() * base * 9));
		account.setAccountType("current");
		account.setBalance(BigDecimal.valueOf(12000.0));
		account.setCurrency("tl");

		return account;
	}
	public static List<Transaction> generateTransaction(int counts) {
		List<Transaction> transactions = new ArrayList<>();
		for (int i = 0; i < counts; i++) {
			Transaction transaction= new Transaction();
			BeanUtils.copyProperties(generateTransactionModel(), transaction);
			transactions.add(transaction);
		}
		return transactions;
	}
	
	public static TransactionDTO generateTransactionModel() {
		int base = (int) Math.pow(10, 1);
        
		TransactionDTO transaction  = new TransactionDTO();
		transaction.setId((long) (base + Math.random() * base * 9));
		 transaction.setAmount(BigDecimal.valueOf(255.0));
		 transaction.setCurrency("eur");
		 transaction.setTransactionMessage("send transfer my wife");
		 transaction.setFromAccountId(4l);
		 transaction.setToAccountId(5l);

		return transaction;
	}
	
	public static void tryJsonObjectAssert(Object obj1, Object obj2) {
		om = new ObjectMapper();

		try {
			String obj1String = (obj1 instanceof String) ? (String) obj1 : om.writeValueAsString(obj1);
			String obj2String = (obj2 instanceof String) ? (String) obj2 : om.writeValueAsString(obj2);
			JSONObject obj1Json = new JSONObject(obj1String);
			JSONObject obj2Json = new JSONObject(obj2String);
			JSONAssert.assertEquals(obj1Json, obj2Json, JSONCompareMode.STRICT);
		} catch (IOException e) {
			assert false;
		} catch (JSONException e) {
			assert false;
		} catch (AssertionError e) {
			assert false;
		}
	}

	
	public static RequestBuilder requestBuilder(String path, RequestMethod method, String content) {
		switch (method) {
		case GET:
			return MockMvcRequestBuilders.get(path).accept(MediaType.APPLICATION_JSON);
		case PUT:
			return MockMvcRequestBuilders.put(path).accept(MediaType.APPLICATION_JSON).content(content).contentType(MediaType.APPLICATION_JSON);
		case POST:
			return MockMvcRequestBuilders.post(path).accept(MediaType.APPLICATION_JSON).content(content).contentType(MediaType.APPLICATION_JSON);
		case DELETE:
			return MockMvcRequestBuilders.delete(path).accept(MediaType.APPLICATION_JSON);
		default:
			return null;
		}
	}
	
	public static String objToJsonString(Object obj) {
		om = new ObjectMapper();
		if (obj == null)
			return null;
		try {
			return om.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			assertTrue(false);
		}
		return null;
	}
	
}

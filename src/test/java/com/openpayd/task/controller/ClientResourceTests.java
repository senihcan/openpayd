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

import com.openpayd.task.dto.ClientDTO;
import com.openpayd.task.entity.Client;
import com.openpayd.task.utils.ServiceUtil;


@RunWith(SpringRunner.class)
@WebMvcTest(value = ClientResource.class, secure = false)
@ContextConfiguration(classes = { ClientResource.class })
public class ClientResourceTests {
	
	@MockBean
	ClientResource clService;
	
	@Autowired
	MockMvc mockMvc;
	
	private List<Client> clients;
	private boolean isSetup = false;
	private String reqMap = "/client/";
	
	@Before
	public void setup() {
		if (isSetup)
			return;
		clients = ServiceUtil.generateClientss(1);
		isSetup = true;
	}

	
	@Test
	public void getClientById() {
		Client client = clients.get(0);
		Mockito.when(clService.getClientById(Mockito.any(Long.class))).thenReturn(client);
		RequestBuilder rb = ServiceUtil.requestBuilder(reqMap + client.getId(), RequestMethod.GET, null);
		String response = performRB(rb);
		ServiceUtil.tryJsonObjectAssert(client, response);
	}

	@Test
	public void createTRest() {
		Client client = clients.get(0);
		ClientDTO clientDTO=new ClientDTO();
				BeanUtils.copyProperties(client, clientDTO);
		Mockito.when(clService.createClient(Mockito.any(ClientDTO.class))).thenReturn(client);
		String clientAsString = ServiceUtil.objToJsonString(client);
		RequestBuilder rb = ServiceUtil.requestBuilder(reqMap, RequestMethod.POST, clientAsString);
		String response = performRB(rb);
		ServiceUtil.tryJsonObjectAssert(client, response);
	}
	
	
	@Test
	public void updateTRest() {
		Client client = clients.get(0);
		ClientDTO clientDTO=new ClientDTO();
				BeanUtils.copyProperties(client, clientDTO);
		Mockito.when(clService.putClient(Mockito.any(Client.class))).thenReturn(client);
		String clientAsString = ServiceUtil.objToJsonString(client);
		RequestBuilder rb = ServiceUtil.requestBuilder(reqMap, RequestMethod.PUT, clientAsString);
		String response = performRB(rb);
		ServiceUtil.tryJsonObjectAssert(client, response);
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

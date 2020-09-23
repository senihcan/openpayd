package com.openpayd.task.dto;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Data
public class ClientDTO{
	
	private Long id;
	
	private String clientName;
	
	private String clientFamilyName;
	
	private String adressLineOne;
	
	private String adressLineTwo;
	
	private String city;
	
	private String country;
}

package com.openpayd.task.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data
public class Client extends BaseEntity{/**
	 * 
	 */
	private static final long serialVersionUID = -6942438000207088601L;
	@Id
    @GeneratedValue
	private Long id;
	
	private String clientName;
	
	private String clientFamilyName;
	
	private String adressLineOne;
	
	private String adressLineTwo;
	
	private String city;
	
	private String country;
	
    @OneToMany
    @JoinColumn(name="client_id")
    @JsonIgnore
    private List<Account> accounts;


}

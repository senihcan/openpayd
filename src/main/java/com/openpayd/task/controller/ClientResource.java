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

import com.openpayd.task.dto.ClientDTO;
import com.openpayd.task.entity.Client;
import com.openpayd.task.services.ClientService;

import lombok.RequiredArgsConstructor;

@CrossOrigin
@RestController
@EnableAutoConfiguration
@RequestMapping("/client")
@RequiredArgsConstructor
public class ClientResource {

	
	@Autowired
	ClientService clientService;

    @PostMapping
    public Client createClient(@RequestBody ClientDTO clientDTO) {
        return clientService.createClient(clientDTO);
    }

    @GetMapping("/{clientId}")
    public Client getClientById(@PathVariable("clientId") Long clientId) {
        return clientService.findClientById(clientId);
    }

    @DeleteMapping("/{clientId}")
    public void deleteClient(@PathVariable("clientId") Long clientId) {
       clientService.deleteClientById(clientId);
    }

    @PutMapping
    public Client putClient(@RequestBody Client client) {

        return clientService.updateClient(client);
    }
    
    @GetMapping
    public List<Client> getAllClient(){
    	return clientService.getAllClient();
    }
}

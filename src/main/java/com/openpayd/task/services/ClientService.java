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

import com.openpayd.task.dto.ClientDTO;
import com.openpayd.task.entity.Client;
import com.openpayd.task.exception.GeneralException;
import com.openpayd.task.exception.NotFoundException;
import com.openpayd.task.repository.ClientRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class ClientService {
	
	@Autowired ClientRepository clientRepository;
	
	
	public Client findClientById(Long id) {
		 Optional<Client> client= clientRepository.findById(id);
		if(client.isPresent()) {
			return client.get();
		}
		else {
			throw new NotFoundException("Client not found with id : " + id);
		}		
	}

	public List<Client> getAllClient(){		
		return findAll(clientRepository.findAll().iterator());		
	}
	
	private List<Client> findAll(Iterator<Client> clientIter) {
		List<Client> clients= new ArrayList<>();
		while(clientIter.hasNext()) {
			if(Objects.isNull(clientIter.next().getEndDate())) {
				clients.add(clientIter.next());
			}
		}
		return clients;
	}
	
	public Client createClient(ClientDTO clientDTO) {
		try {
			Client client=new Client();
			BeanUtils.copyProperties(clientDTO, client);
		return clientRepository.save(client);
		}
		catch (Exception e) {
			log.error(e.getMessage());
			throw new GeneralException("Error occured when create client ");
		}
	}
	
	public Client updateClient(Client client) {
		try {
			if(Objects.isNull(client.getId())) {
				throw new GeneralException("Client not found for update");
			}
		return clientRepository.save(client);
		}
		catch (Exception e) {
			log.error(e.getMessage());
			throw new GeneralException("Error occured when update client with id : " + client.getId());
		}
	}
	
	public void deleteClientById(Long id) {
		try {
		clientRepository.deleteById(id);
		}
		catch (Exception e) {
			log.error(e.getMessage());
			throw new GeneralException("Error occured when delete client with id: " + id);
		}
	}
}

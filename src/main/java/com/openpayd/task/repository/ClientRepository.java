package com.openpayd.task.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.openpayd.task.entity.Client;

@Repository
public interface ClientRepository  extends CrudRepository<Client, Long>{

}

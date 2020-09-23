package com.openpayd.task.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.openpayd.task.entity.Transaction;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction, Long>{
	
	List<Transaction> findByFromAccountId(Long fromAccountId);
	List<Transaction> findByToAccountId(Long fromAccountId);

}

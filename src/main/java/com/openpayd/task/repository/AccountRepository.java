package com.openpayd.task.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.openpayd.task.entity.Account;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long>{

}

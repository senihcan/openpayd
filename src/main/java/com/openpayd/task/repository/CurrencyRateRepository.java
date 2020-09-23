package com.openpayd.task.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.openpayd.task.entity.CurrencyRate;

@Repository
public interface CurrencyRateRepository extends CrudRepository<CurrencyRate, Long>{

	Optional<CurrencyRate> findFirstByOrderByCurrencyDateDesc();
}

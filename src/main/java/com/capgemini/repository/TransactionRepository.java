package com.capgemini.repository;

import com.capgemini.domain.Transaction;


import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Transaction entity.
 */

public interface TransactionRepository extends JpaRepository<Transaction,Long> {
	
	public List<Transaction> findOneByBAccountId(long B_ACCOUNT_ID);

}

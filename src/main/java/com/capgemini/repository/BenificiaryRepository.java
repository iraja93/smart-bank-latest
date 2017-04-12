package com.capgemini.repository;

import com.capgemini.domain.Benificiary;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Benificiary entity.
 */
@SuppressWarnings("unused")
public interface BenificiaryRepository extends JpaRepository<Benificiary,Long> {
	
	//public List<Benificiary> findByAccountIDAndBAccountId(long accountId,long B_ACCOUNT_ID);
	public List<Benificiary> findOneByBAccountId(long BAccountId);

}

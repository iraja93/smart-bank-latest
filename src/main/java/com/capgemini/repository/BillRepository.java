package com.capgemini.repository;

import com.capgemini.domain.Bill;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Bill entity.
 */
@SuppressWarnings("unused")
public interface BillRepository extends JpaRepository<Bill,Long> {
	public List<Bill> findOneByCardId(long cardId);

}

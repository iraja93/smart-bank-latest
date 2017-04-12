package com.capgemini.repository;

import com.capgemini.domain.Card;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Card entity.
 */
@SuppressWarnings("unused")
public interface CardRepository extends JpaRepository<Card,Long> {

   /* @Query("select card from Card card where card.user.login = ?#{principal.username}")
    List<Card> findByUserIsCurrentUser();*/
	
	public List<Card> findOneByBAccountId(long bAccountId);
	//public Card findOneByBAccountid(long bAccountId);

}

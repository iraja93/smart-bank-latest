package com.capgemini.repository;

import com.capgemini.domain.BAccount;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the BAccount entity.
 */
@SuppressWarnings("unused")
public interface BAccountRepository extends JpaRepository<BAccount,Long> {

    @Query("select bAccount from BAccount bAccount where bAccount.user.login = ?#{principal.username}")
    List<BAccount> findByUserIsCurrentUser();

}

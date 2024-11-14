package org.example.account.repository;

import org.example.account.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {

    @Query(value = "SELECT * FROM account ORDER BY id DESC LIMIT 1", nativeQuery = true)
    Optional<Account> findFirstByOrderByIdDesc();
}

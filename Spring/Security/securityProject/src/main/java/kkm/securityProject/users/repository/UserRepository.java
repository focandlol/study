package kkm.securityProject.users.repository;

import kkm.securityProject.domain.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;

public interface UserRepository extends JpaRepository<Account, Long> {

    Account findByUsername(String username);
}

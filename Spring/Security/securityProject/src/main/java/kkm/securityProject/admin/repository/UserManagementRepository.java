package kkm.securityProject.admin.repository;

import kkm.securityProject.domain.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserManagementRepository extends JpaRepository<Account, Long> { }

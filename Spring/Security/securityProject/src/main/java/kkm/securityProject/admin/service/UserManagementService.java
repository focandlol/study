package kkm.securityProject.admin.service;

import kkm.securityProject.domain.dto.AccountDto;
import kkm.securityProject.domain.entity.Account;

import java.util.List;

public interface UserManagementService {

    void modifyUser(AccountDto accountDto);

    List<Account> getUsers();
    AccountDto getUser(Long id);

    void deleteUser(Long idx);

}

package org.example.account.service;

import org.example.account.domain.Account;
import org.example.account.domain.AccountStatus;
import org.example.account.repository.AccountRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountService accountService;

    @Test
    @DisplayName("계좌 조회 성공")
    void testXXX(){
        given(accountRepository.findById(anyLong()))
                .willReturn(Optional.of(Account.builder()
                        .accountStatus(AccountStatus.UNREGISTERED)
                        .accountNumber("123412")
                        .build()));

        Account account = accountService.getAccount(4555L);

        assertEquals("123412",account.getAccountNumber());
        assertEquals(AccountStatus.UNREGISTERED,account.getAccountStatus());

    }
    @Test
    void accountServiceTest() {
        Account account = accountService.getAccount(1L);
        assertEquals("40000",account.getAccountNumber());
        assertEquals(AccountStatus.IN_USE,account.getAccountStatus());
    }

    @Test
    void accountServiceTest2() {
        Account account = accountService.getAccount(2L);
        assertEquals("40000",account.getAccountNumber());
        assertEquals(AccountStatus.IN_USE,account.getAccountStatus());
    }

    @Test
    void testSomething(){
        String something = "Hello world";

        assertEquals("Hello world", something);
    }

}
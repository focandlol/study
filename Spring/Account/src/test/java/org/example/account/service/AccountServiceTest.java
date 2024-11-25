package org.example.account.service;

import org.example.account.domain.Account;
import org.example.account.domain.AccountUser;
import org.example.account.dto.AccountDto;
import org.example.account.exception.AccountException;
import org.example.account.repository.AccountUserRepository;
import org.example.account.type.AccountStatus;
import org.example.account.repository.AccountRepository;
import org.example.account.type.ErrorCode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.*;

import static org.example.account.type.AccountStatus.IN_USE;
import static org.example.account.type.ErrorCode.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private AccountUserRepository accountUserRepository;

    @InjectMocks
    private AccountService accountService;

    private AccountUser user;

    @BeforeEach
    void setUp() {
        user = AccountUser.builder()
                .id(12L)
                .name("pobi").build();
    }

    @Test
    @DisplayName("계좌 번호 랜덤 생성")
    void createRandomAccountNumber(){
        given(accountRepository.findByAccountNumber(anyString()))
                .willReturn(Optional.of(new Account()))
                .willReturn(Optional.empty());

        String accountNumber = accountService.getAccountNumberTest();
        verify(accountRepository,times(2)).findByAccountNumber(anyString());
        assertEquals(10, accountNumber.length());
    }

    @Test
    @DisplayName("계좌 생성 성공")
    void createAccountSuccess(){
        given(accountUserRepository.findById(anyLong()))
                .willReturn(Optional.of(user));

        given(accountRepository.findByAccountNumber(anyString()))
                .willReturn(Optional.empty());

        given(accountRepository.save(any()))
                .willReturn(Account.builder()
                        .accountUser(user)
                        .accountNumber("1000000013")
                        .accountStatus(IN_USE)
                        .balance(1000L)
                        .registeredAt(LocalDateTime.now())
                        .build());

        ArgumentCaptor<Account> captor = ArgumentCaptor.forClass(Account.class);

        AccountDto account = accountService.createAccount(1L, 1000L);

        verify(accountRepository,times(1)).save(captor.capture());
        assertEquals(12L,account.getUserId());
        assertEquals(1000L,account.getBalance());
        assertEquals(1000L,captor.getValue().getBalance());
        assertEquals(IN_USE,captor.getValue().getAccountStatus());
    }

    @Test
    @DisplayName("해당 유저 없음 - 계좌 생성 실패")
    void createAccount_UserNotFound(){

        given(accountUserRepository.findById(anyLong()))
                .willReturn(Optional.empty());

        AccountException accountException = assertThrows(AccountException.class,
                () -> accountService.createAccount(1L, 1000L));

        assertEquals(USER_NOT_FOUND,accountException.getErrorCode());

    }

    @Test
    @DisplayName("유저 당 최대 계좌는 10개 - 계좌 생성 실패")
    void createAccount_maxAccountIs10(){
        given(accountUserRepository.findById(anyLong()))
                .willReturn(Optional.of(user));

        given(accountRepository.countByAccountUser(any()))
                .willReturn(10);

        AccountException accountException = assertThrows(AccountException.class, () ->
                accountService.createAccount(1L, 1000L));

        assertEquals(MAX_ACCOUNT_PER_USER_10,accountException.getErrorCode());
    }

    @Test
    @DisplayName("계좌 해지 성공")
    void deleteAccountSuccess(){
        given(accountUserRepository.findById(anyLong()))
                .willReturn(Optional.of(user));

        given(accountRepository.findByAccountNumber(anyString()))
                .willReturn(Optional.of(Account.builder()
                        .accountUser(user)
                                .balance(0L)
                        .accountNumber("1000000012").build()));

        ArgumentCaptor<Account> captor = ArgumentCaptor.forClass(Account.class);

        AccountDto account = accountService.deleteAccount(1L, "1234567890");

        verify(accountRepository,times(1)).save(captor.capture());
        assertEquals(12L,account.getUserId());
        assertEquals(0L,account.getBalance());
        assertEquals("1000000012",account.getAccountNumber());
        assertEquals("1000000012",captor.getValue().getAccountNumber());
        assertEquals(AccountStatus.UNREGISTERED,captor.getValue().getAccountStatus());
        assertEquals(0L,captor.getValue().getBalance());
        assertNotNull(captor.getValue().getUnregisteredAt());
    }


    @Test
    @DisplayName("해당 유저 없음 - 계좌 해지 실패")
    void deleteAccount_UserNotFound(){

        given(accountUserRepository.findById(anyLong()))
                .willReturn(Optional.empty());

        AccountException accountException = assertThrows(AccountException.class,
                () -> accountService.deleteAccount(1L, "1234567890"));

        assertEquals(USER_NOT_FOUND,accountException.getErrorCode());

    }

    @Test
    @DisplayName("해당 계좌 없음 - 계좌 해지 실패")
    void deleteAccount_AccountNotFound(){
        given(accountUserRepository.findById(anyLong()))
                .willReturn(Optional.of(user));

        given(accountRepository.findByAccountNumber(anyString()))
                .willReturn(Optional.empty());

        AccountException accountException = assertThrows(AccountException.class,
                () -> accountService.deleteAccount(1L, "1234567890"));

        assertEquals(ACCOUNT_NOT_FOUND,accountException.getErrorCode());
    }

    @Test
    @DisplayName("계좌 소유주 다름 - 계좌 해지 실패")
    void deleteAccountFailed_userUnMatch(){
        AccountUser otherUser = AccountUser.builder()
                .id(13L)
                .name("kkm").build();

        given(accountUserRepository.findById(anyLong()))
                .willReturn(Optional.of(user));

        given(accountRepository.findByAccountNumber(anyString()))
                .willReturn(Optional.of(Account.builder()
                        .accountUser(otherUser)
                        .balance(0L)
                        .accountNumber("1000000012").build()));

        AccountException accountException = assertThrows(AccountException.class,
                () -> accountService.deleteAccount(1L, "1234567890"));

        assertEquals(USER_ACCOUNT_UN_MATCH,accountException.getErrorCode());
    }

    @Test
    @DisplayName("잔액 남아있음 - 계좌 해지 실패")
    void deleteAccountFailed_balanceNotEmpty(){
        given(accountUserRepository.findById(anyLong()))
                .willReturn(Optional.of(user));

        given(accountRepository.findByAccountNumber(anyString()))
                .willReturn(Optional.of(Account.builder()
                        .accountUser(user)
                        .balance(100L)
                        .accountNumber("1000000012").build()));

        AccountException accountException = assertThrows(AccountException.class,
                () -> accountService.deleteAccount(1L, "1234567890"));

        assertEquals(BALANCE_NOT_EMPTY,accountException.getErrorCode());
    }

    @Test
    @DisplayName("이미 해지되어 있음 - 계좌 해지 실패")
    void deleteAccountFailed_accountAlreadyUnregistered(){
        given(accountUserRepository.findById(anyLong()))
                .willReturn(Optional.of(user));

        given(accountRepository.findByAccountNumber(anyString()))
                .willReturn(Optional.of(Account.builder()
                        .accountUser(user)
                        .balance(0L)
                        .accountStatus(AccountStatus.UNREGISTERED)
                        .accountNumber("1000000012").build()));

        AccountException accountException = assertThrows(AccountException.class,
                () -> accountService.deleteAccount(1L, "1234567890"));

        assertEquals(ACCOUNT_ALREADY_UNREGISTERED,accountException.getErrorCode());
    }

    @Test
    @DisplayName("계좌 확인 성공")
    void successGetAccountsByUserId(){
        List<Account> accounts = Arrays.asList(
                Account.builder()
                        .accountUser(user)
                        .accountNumber("1111111111")
                        .balance(1000L)
                        .build(),
                Account.builder()
                        .accountUser(user)
                        .accountNumber("2222222222")
                        .balance(2000L)
                        .build(),
                Account.builder()
                        .accountUser(user)
                        .accountNumber("3333333333")
                        .balance(3000L)
                        .build()
        );
        given(accountUserRepository.findById(anyLong()))
                .willReturn(Optional.of(user));

        given(accountRepository.findByAccountUser(any()))
                .willReturn(accounts);

        List<AccountDto> accountDtos = accountService.getAccountsByUserId(1L);

        assertEquals(3,accountDtos.size());
        assertEquals("1111111111",accountDtos.get(0).getAccountNumber());
        assertEquals("2222222222",accountDtos.get(1).getAccountNumber());
        assertEquals("3333333333",accountDtos.get(2).getAccountNumber());
        assertEquals(1000,accountDtos.get(0).getBalance());
        assertEquals(2000,accountDtos.get(1).getBalance());
        assertEquals(3000,accountDtos.get(2).getBalance());
    }

    @Test
    @DisplayName("계좌 확인 실패")
    void failedToGetAccounts(){
        given(accountUserRepository.findById(anyLong()))
                .willReturn(Optional.empty());

        AccountException accountException = assertThrows(AccountException.class,
                () -> accountService.getAccountsByUserId(1L));

        assertEquals(USER_NOT_FOUND,accountException.getErrorCode());
    }


}
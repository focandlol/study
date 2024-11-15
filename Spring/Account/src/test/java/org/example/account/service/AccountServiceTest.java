package org.example.account.service;

import org.example.account.domain.Account;
import org.example.account.domain.AccountUser;
import org.example.account.dto.AccountDto;
import org.example.account.exception.AccountException;
import org.example.account.repository.AccountUserRepository;
import org.example.account.type.AccountStatus;
import org.example.account.repository.AccountRepository;
import org.example.account.type.ErrorCode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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

    @Test
    @DisplayName("계좌 조회 성공")
    void testXXX(){
        given(accountRepository.findById(anyLong()))
                .willReturn(Optional.of(Account.builder()
                        .accountStatus(AccountStatus.UNREGISTERED)
                        .accountNumber("123412")
                        .build()));

        ArgumentCaptor<Long> captor = ArgumentCaptor.forClass(Long.class);


        Account account = accountService.getAccount(4555L);

        verify(accountRepository,times(1)).findById(captor.capture());
        verify(accountRepository,times(0)).save(any());
        assertEquals(4555L,captor.getValue());
        assertNotEquals(4535L,captor.getValue());
        assertTrue(4555L == captor.getValue());
        assertEquals("123412",account.getAccountNumber());
        assertEquals(AccountStatus.UNREGISTERED,account.getAccountStatus());
    }

    @Test
    void createAccountSuccess(){
        AccountUser user = AccountUser.builder()
                .id(12L)
                .name("pobi").build();

        given(accountUserRepository.findById(anyLong()))
                .willReturn(Optional.of(user));

        given(accountRepository.findFirstByOrderByIdDesc())
                .willReturn(Optional.of(Account.builder()
                        .accountNumber("1000000012").build()));

        given(accountRepository.save(any()))
                .willReturn(Account.builder()
                        .accountUser(user)
                        .accountNumber("1000000013").build());

        ArgumentCaptor<Account> captor = ArgumentCaptor.forClass(Account.class);

        AccountDto account = accountService.createAccount(1L, 1000L);

        verify(accountRepository,times(1)).save(captor.capture());
        assertEquals(12L,account.getUserId());
        assertEquals("1000000013",captor.getValue().getAccountNumber());
    }

    @Test
    void deleteAccountSuccess(){
        AccountUser user = AccountUser.builder()
                .id(12L)
                .name("pobi").build();

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
        assertEquals("1000000012",captor.getValue().getAccountNumber());
        assertEquals(AccountStatus.UNREGISTERED,captor.getValue().getAccountStatus());
    }

    @Test
    void createFirstAccount(){
        AccountUser user = AccountUser.builder()
                .id(12L)
                .name("pobi").build();

        given(accountUserRepository.findById(anyLong()))
                .willReturn(Optional.of(user));

        given(accountRepository.findFirstByOrderByIdDesc())
                .willReturn(Optional.empty());

        given(accountRepository.save(any()))
                .willReturn(Account.builder()
                        .accountUser(user)
                        .accountNumber("1000000013").build());

        ArgumentCaptor<Account> captor = ArgumentCaptor.forClass(Account.class);

        AccountDto account = accountService.createAccount(1L, 1000L);

        verify(accountRepository,times(1)).save(captor.capture());
        assertEquals(12L,account.getUserId());
        assertEquals("1000000000",captor.getValue().getAccountNumber());
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
    @DisplayName("유저 당 최대 계좌는 10개")
    void createAccount_maxAccountIs10(){
        AccountUser user = AccountUser.builder()
                .id(12L)
                .name("pobi").build();

        given(accountUserRepository.findById(anyLong()))
                .willReturn(Optional.of(user));

        given(accountRepository.countByAccountUser(any()))
                .willReturn(10);

        AccountException accountException = assertThrows(AccountException.class, () ->
                accountService.createAccount(1L, 1000L));

        assertEquals(MAX_ACCOUNT_PER_USER_10,accountException.getErrorCode());
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
        AccountUser user = AccountUser.builder()
                .id(12L)
                .name("pobi").build();

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
        AccountUser user = AccountUser.builder()
                .id(12L)
                .name("pobi").build();

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
        AccountUser user = AccountUser.builder()
                .id(12L)
                .name("pobi").build();

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
        AccountUser user = AccountUser.builder()
                .id(12L)
                .name("pobi").build();

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
    void successGetAccountsByUserId(){
        AccountUser user = AccountUser.builder()
                .id(12L)
                .name("pobi").build();

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
    void failedToGetAccounts(){
        AccountUser user = AccountUser.builder()
                .id(12L)
                .name("pobi").build();
        given(accountUserRepository.findById(anyLong()))
                .willReturn(Optional.empty());

        AccountException accountException = assertThrows(AccountException.class,
                () -> accountService.getAccountsByUserId(1L));

        assertEquals(USER_NOT_FOUND,accountException.getErrorCode());
    }


}
package org.example.account.service;

import org.example.account.domain.Account;
import org.example.account.domain.AccountUser;
import org.example.account.domain.Transaction;
import org.example.account.dto.TransactionDto;
import org.example.account.exception.AccountException;
import org.example.account.repository.AccountRepository;
import org.example.account.repository.AccountUserRepository;
import org.example.account.repository.TransactionRepository;
import org.example.account.type.AccountStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.example.account.type.AccountStatus.*;
import static org.example.account.type.ErrorCode.*;
import static org.example.account.type.TransactionResultType.F;
import static org.example.account.type.TransactionResultType.S;
import static org.example.account.type.TransactionType.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class TransactionServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private AccountUserRepository accountUserRepository;

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private TransactionService transactionService;

    private AccountUser user;

    @BeforeEach
    void setUp() {
        user = AccountUser.builder()
                .id(12L)
                .name("pobi").build();
    }

    @Test
    @DisplayName("잔액 사용 성공")
    void successUseBalance(){
        given(accountUserRepository.findById(anyLong()))
                .willReturn(Optional.of(user));

        Account account = Account.builder()
                .accountUser(user)
                .balance(10000L)
                .accountStatus(IN_USE)
                .accountNumber("1000000012").build();

        given(accountRepository.findByAccountNumber(anyString()))
                .willReturn(Optional.of(account));

        given(transactionRepository.save(any()))
                .willReturn(Transaction.builder()
                        .account(account)
                        .transactionType(USE)
                        .transactionResult(S)
                        .amount(1000L)
                        .balanceSnapshot(9000L)
                        .transactionId("transactionId")
                        .transactedAt(LocalDateTime.now())
                        .build());

        ArgumentCaptor<Transaction> captor = ArgumentCaptor.forClass(Transaction.class);

        TransactionDto transactionDto = transactionService.useBalance(1L, "10000000000", 200L);

        verify(transactionRepository,times(1)).save(captor.capture());
        assertEquals(9000L,transactionDto.getBalanceSnapshot());
        assertEquals(S,transactionDto.getTransactionResult());
        assertEquals(USE,transactionDto.getTransactionType());
        assertEquals(1000L,transactionDto.getAmount());
        assertEquals("transactionId",transactionDto.getTransactionId());
        assertEquals("1000000012",captor.getValue().getAccount().getAccountNumber());
        assertEquals(200L,captor.getValue().getAmount());
        assertEquals(USE,captor.getValue().getTransactionType());
        assertEquals(S,captor.getValue().getTransactionResult());
        assertEquals(10000L-200L,captor.getValue().getBalanceSnapshot());

    }

    @Test
    @DisplayName("실패 트랜잭션 저장")
    void saveFailedUseTransaction(){
        Account account = Account.builder()
                .accountUser(user)
                .balance(10000L)
                .accountStatus(IN_USE)
                .accountNumber("1000000012").build();

        given(accountRepository.findByAccountNumber(anyString()))
                .willReturn(Optional.of(account));

        given(transactionRepository.save(any()))
                .willReturn(Transaction.builder()
                        .account(account)
                        .transactionType(USE)
                        .transactionResult(F)
                        .amount(1000L)
                        .balanceSnapshot(9000L)
                        .transactionId("transactionId")
                        .transactedAt(LocalDateTime.now())
                        .build());

        ArgumentCaptor<Transaction> captor = ArgumentCaptor.forClass(Transaction.class);

        transactionService.saveFailedUseTransaction("10000000000", 200L);

        verify(transactionRepository,times(1)).save(captor.capture());
        assertEquals(10000L,captor.getValue().getBalanceSnapshot());
        assertEquals(F,captor.getValue().getTransactionResult());
        assertEquals("1000000012",captor.getValue().getAccount().getAccountNumber());
        assertEquals(200L,captor.getValue().getAmount());
        assertEquals(USE,captor.getValue().getTransactionType());

    }

    @Test
    @DisplayName("해당 유저 없음 - 잔액 사용 실패")
    void useBalance_UserNotFound(){

        given(accountUserRepository.findById(anyLong()))
                .willReturn(Optional.empty());

        AccountException accountException = assertThrows(AccountException.class,
                () -> transactionService.useBalance(1L, "10000000000", 200L));

        assertEquals(USER_NOT_FOUND,accountException.getErrorCode());

    }

    @Test
    @DisplayName("해당 계좌 없음 - 잔액 사용 실패")
    void useBalance_AccountNotFound(){
        given(accountUserRepository.findById(anyLong()))
                .willReturn(Optional.of(user));

        given(accountRepository.findByAccountNumber(anyString()))
                .willReturn(Optional.empty());

        AccountException accountException = assertThrows(AccountException.class,
                () -> transactionService.useBalance(1L, "1234567890",100L));

        assertEquals(ACCOUNT_NOT_FOUND,accountException.getErrorCode());
    }

    @Test
    @DisplayName("계좌 소유주 다름 - 잔액 사용 실패")
    void useBalance_userUnMatch(){
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
                () -> transactionService.useBalance(1L, "1234567890",200L));

        assertEquals(USER_ACCOUNT_UN_MATCH,accountException.getErrorCode());
    }

    @Test
    @DisplayName("이미 해지되어 있음 - 잔액 사용 실패")
    void useBalance_accountAlreadyUnregistered(){
        given(accountUserRepository.findById(anyLong()))
                .willReturn(Optional.of(user));

        given(accountRepository.findByAccountNumber(anyString()))
                .willReturn(Optional.of(Account.builder()
                        .accountUser(user)
                        .balance(0L)
                        .accountStatus(AccountStatus.UNREGISTERED)
                        .accountNumber("1000000012").build()));

        AccountException accountException = assertThrows(AccountException.class,
                () -> transactionService.useBalance(1L, "1234567890",200L));

        assertEquals(ACCOUNT_ALREADY_UNREGISTERED,accountException.getErrorCode());
    }

    @Test
    @DisplayName("돈 부족 - 잔액 사용 실패")
    void exceedAmount_useBalance(){
        given(accountUserRepository.findById(anyLong()))
                .willReturn(Optional.of(user));

        Account account = Account.builder()
                .accountUser(user)
                .balance(100L)
                .accountStatus(IN_USE)
                .accountNumber("1000000012").build();

        given(accountRepository.findByAccountNumber(anyString()))
                .willReturn(Optional.of(account));

        AccountException accountException = assertThrows(AccountException.class,
                () -> transactionService.useBalance(1L, "1234567890", 3000L));

        assertEquals(AMOUNT_EXCEED_BALANCE,accountException.getErrorCode());
        verify(transactionRepository,times(0)).save(any());
    }

    @Test
    @DisplayName("잔액 사용 취소 성공")
    void successCancelBalance(){
        Account account = Account.builder()
                .id(1L)
                .accountUser(user)
                .balance(9000L)
                .accountStatus(IN_USE)
                .accountNumber("1000000012").build();

        Transaction transaction = Transaction.builder()
                .account(account)
                .transactionType(USE)
                .transactionResult(S)
                .amount(1000L)
                .balanceSnapshot(9000L)
                .transactionId("transactionId")
                .transactedAt(LocalDateTime.now())
                .build();

        given(transactionRepository.findByTransactionId(anyString()))
                .willReturn(Optional.of(transaction));

        given(accountRepository.findByAccountNumber(anyString()))
                .willReturn(Optional.of(account));

        given(transactionRepository.save(any()))
                .willReturn(Transaction.builder()
                        .account(account)
                        .transactionType(CANCEL)
                        .transactionResult(S)
                        .amount(1000L)
                        .balanceSnapshot(10000L)
                        .transactionId("transactionIdCancel")
                        .transactedAt(LocalDateTime.now())
                        .build());

        ArgumentCaptor<Transaction> captor = ArgumentCaptor.forClass(Transaction.class);

        TransactionDto transactionDto = transactionService.cancelBalance("transactionId", "10000000000", 1000L);

        verify(transactionRepository,times(1)).save(captor.capture());
        assertEquals(10000L,transactionDto.getBalanceSnapshot());
        assertEquals(S,transactionDto.getTransactionResult());
        assertEquals("transactionIdCancel",transactionDto.getTransactionId());
        assertEquals(1000L,transactionDto.getAmount());
        assertEquals("1000000012",captor.getValue().getAccount().getAccountNumber());
        assertEquals(1000L,captor.getValue().getAmount());
        assertEquals(CANCEL,captor.getValue().getTransactionType());
        assertEquals(S,captor.getValue().getTransactionResult());
        assertEquals(9000L+1000L,captor.getValue().getBalanceSnapshot());
    }

    @Test
    @DisplayName("해당 계좌 없음 - 잔액 사용 취소 실패")
    void cancelBalance_AccountNotFound(){
        Transaction transaction = Transaction.builder()
                .transactionType(USE)
                .transactionResult(S)
                .amount(1000L)
                .balanceSnapshot(9000L)
                .transactionId("transactionId")
                .transactedAt(LocalDateTime.now())
                .build();

        given(transactionRepository.findByTransactionId(anyString()))
                .willReturn(Optional.of(transaction));
        given(accountRepository.findByAccountNumber(anyString()))
                .willReturn(Optional.empty());

        AccountException accountException = assertThrows(AccountException.class,
                () -> transactionService.cancelBalance("dsf", "1234567890",100L));

        assertEquals(ACCOUNT_NOT_FOUND,accountException.getErrorCode());
    }

    @Test
    @DisplayName("해당 거래 없음 - 잔액 사용 취소 실패")
    void cancelBalance_TransactionNotFound(){

        given(transactionRepository.findByTransactionId(anyString()))
                .willReturn(Optional.empty());

        AccountException accountException = assertThrows(AccountException.class,
                () -> transactionService.cancelBalance("dsf", "1234567890",100L));

        assertEquals(TRANSACTION_NOT_FOUND,accountException.getErrorCode());
    }

    @Test
    @DisplayName("해당 계좌의 거래가 아님 - 잔액 사용 취소 실패")
    void cancelBalance_TransactionAccountUnMatch(){
        Account account = Account.builder()
                .id(1L)
                .accountUser(user)
                .balance(9000L)
                .accountStatus(IN_USE)
                .accountNumber("1000000012").build();

        Account otherAccount = Account.builder()
                .id(2L)
                .accountUser(user)
                .balance(9000L)
                .accountStatus(IN_USE)
                .accountNumber("1000000012").build();

        Transaction transaction = Transaction.builder()
                .account(account)
                .transactionType(USE)
                .transactionResult(S)
                .amount(1000L)
                .balanceSnapshot(9000L)
                .transactionId("transactionId")
                .transactedAt(LocalDateTime.now())
                .build();

        given(transactionRepository.findByTransactionId(anyString()))
                .willReturn(Optional.of(transaction));

        given(accountRepository.findByAccountNumber(anyString()))
                .willReturn(Optional.of(otherAccount));

        AccountException accountException = assertThrows(AccountException.class,
                () -> transactionService.cancelBalance("dsf", "1234567890",1000L));

        assertEquals(TRANSACTION_ACCOUNT_UN_MATCH,accountException.getErrorCode());
    }

    @Test
    @DisplayName("거래 금액과 취소금액 다름 - 잔액 사용 취소 실패")
    void cancelBalance_gullyCancel(){
        Account account = Account.builder()
                .id(1L)
                .accountUser(user)
                .balance(9000L)
                .accountStatus(IN_USE)
                .accountNumber("1000000012").build();


        Transaction transaction = Transaction.builder()
                .account(account)
                .transactionType(USE)
                .transactionResult(S)
                .amount(1000L)
                .balanceSnapshot(9000L)
                .transactionId("transactionId")
                .transactedAt(LocalDateTime.now())
                .build();

        given(transactionRepository.findByTransactionId(anyString()))
                .willReturn(Optional.of(transaction));

        given(accountRepository.findByAccountNumber(anyString()))
                .willReturn(Optional.of(account));

        AccountException accountException = assertThrows(AccountException.class,
                () -> transactionService.cancelBalance("dsf", "1234567890",100L));

        assertEquals(CANCEL_MUST_FULLY,accountException.getErrorCode());
    }


    @Test
    @DisplayName("거래 확인 성공 - 성공 거래 확인")
    void successQueryTransaction_SuccessTransaction(){
        Account account = Account.builder()
                .id(1L)
                .balance(9000L)
                .accountStatus(IN_USE)
                .accountNumber("1000000012").build();

        Transaction transaction = Transaction.builder()
                .account(account)
                .transactionType(USE)
                .transactionResult(S)
                .amount(1000L)
                .balanceSnapshot(9000L)
                .transactionId("transactionId")
                .transactedAt(LocalDateTime.now())
                .build();

        given(transactionRepository.findByTransactionId(anyString()))
                .willReturn(Optional.of(transaction));

        TransactionDto transactionDto = transactionService.queryTransaction("asdasd");

        assertEquals("1000000012",transactionDto.getAccountNumber());
        assertEquals(USE,transactionDto.getTransactionType());
        assertEquals(S,transactionDto.getTransactionResult());
        assertEquals(1000L,transactionDto.getAmount());
        assertEquals("transactionId",transactionDto.getTransactionId());
    }

    @Test
    @DisplayName("거래 확인 성공 - 실패 거래 확인")
    void successQueryTransaction_FailedTransaction() {
        Account account = Account.builder()
                .id(1L)
                .balance(9000L)
                .accountStatus(IN_USE)
                .accountNumber("1000000012").build();

        Transaction transaction = Transaction.builder()
                .account(account)
                .transactionType(USE)
                .transactionResult(F)
                .amount(1000L)
                .balanceSnapshot(9000L)
                .transactionId("transactionId")
                .transactedAt(LocalDateTime.now())
                .build();

        given(transactionRepository.findByTransactionId(anyString()))
                .willReturn(Optional.of(transaction));

        TransactionDto transactionDto = transactionService.queryTransaction("asdasd");

        assertEquals("1000000012", transactionDto.getAccountNumber());
        assertEquals(USE, transactionDto.getTransactionType());
        assertEquals(F, transactionDto.getTransactionResult());
        assertEquals(1000L, transactionDto.getAmount());
        assertEquals("transactionId", transactionDto.getTransactionId());
    }

    @Test
    @DisplayName("해당 거래 없음 - 거래 확인 실패")
    void queryTransaction_TransactionNotFound(){
        given(transactionRepository.findByTransactionId(anyString()))
                .willReturn(Optional.empty());

        AccountException accountException = assertThrows(AccountException.class,
                () -> transactionService.queryTransaction("dsf"));

        assertEquals(TRANSACTION_NOT_FOUND,accountException.getErrorCode());
    }


}
package org.example.account.service;

import lombok.RequiredArgsConstructor;
import org.example.account.domain.Account;
import org.example.account.domain.AccountUser;
import org.example.account.dto.AccountDto;
import org.example.account.exception.AccountException;
import org.example.account.repository.AccountRepository;
import org.example.account.repository.AccountUserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static org.example.account.type.AccountStatus.*;
import static org.example.account.type.ErrorCode.*;

@Service
@RequiredArgsConstructor
@Transactional
public class AccountService {
    private final AccountRepository accountRepository;
    private final AccountUserRepository accountUserRepository;

    /**
     * 사용자가 있는지 조회
     * 계좌 번호 생성
     * 계좌를 저장하고 그 정보를 넘긴다
     *
     */
    public AccountDto createAccount(Long userId, Long initialBalance){
        AccountUser accountUser = getAccountUser(userId);

        validateCreateAccount(accountUser);

        String newAccount = getAccountNumber();

        Account saveAccount = accountRepository.save(
                Account.builder()
                        .accountUser(accountUser)
                        .accountStatus(IN_USE)
                        .accountNumber(newAccount)
                        .balance(initialBalance)
                        .registeredAt(LocalDateTime.now())
                        .build()
        );

        return AccountDto.fromEntity(saveAccount);
    }

    /**
     * 랜덤 10자리 계좌 번호 반환
     */
    private String getAccountNumber() {
        String accountNumber;
        Random random = new Random();
        do {
            long number = (long) (random.nextDouble() * 10_000_000_000L);
            accountNumber = String.format("%010d", number);
        } while (!accountRepository.findByAccountNumber(accountNumber).isEmpty());
        return accountNumber;
    }

    /**
     * 테스트용 메서드
     */
    public String getAccountNumberTest(){
        return getAccountNumber();
    }

    private void validateCreateAccount(AccountUser accountUser) {
        /**
         * 계좌가 10개 인 경우 실패
         */
        if(accountRepository.countByAccountUser(accountUser) >= 10){
            throw new AccountException(MAX_ACCOUNT_PER_USER_10);
        }
    }


    public AccountDto deleteAccount(Long userId, String accountNumber) {
        AccountUser accountUser = getAccountUser(userId);
        Account account = getAccount(accountNumber);

        validateDeleteAccount(accountUser,account);

        account.setAccountStatus(UNREGISTERED);
        account.setUnregisteredAt(LocalDateTime.now());

        //테스트를 위해
        accountRepository.save(account);

        return AccountDto.fromEntity(account);
    }

    private void validateDeleteAccount(AccountUser accountUser, Account account) {
        /**
         * 사용자 아이디와 계좌 소유주가 다른 경우 실패
         */
        if(!accountUser.getId().equals(account.getAccountUser().getId())){
            throw new AccountException(USER_ACCOUNT_UN_MATCH);
        }

        /**
         * 계좌가 이미 해지 상태인 경우 실패
         */
        if(account.getAccountStatus() == UNREGISTERED){
            throw new AccountException(ACCOUNT_ALREADY_UNREGISTERED);
        }

        /**
         * 잔액이 있는 경우 실패
         */
        if(account.getBalance() > 0){
            throw new AccountException(BALANCE_NOT_EMPTY);
        }
    }

    public List<AccountDto> getAccountsByUserId(Long userId) {
        AccountUser accountUser = getAccountUser(userId);

        List<Account> accounts = accountRepository.findByAccountUser(accountUser);

        return accounts.stream()
                .map(account -> AccountDto.fromEntity(account))
                .collect(Collectors.toList());

    }

    /**
     * 사용자가 없는 경우 실패
     */
    private AccountUser getAccountUser(Long userId) {
        AccountUser accountUser = accountUserRepository.findById(userId)
                .orElseThrow(() -> new AccountException(USER_NOT_FOUND));
        return accountUser;
    }

    /**
     * 계좌가 없는 경우 실패
     */
    private Account getAccount(String accountNumber) {
        Account account = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new AccountException(ACCOUNT_NOT_FOUND));
        return account;
    }
}

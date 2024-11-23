package org.example.account.service;

import lombok.RequiredArgsConstructor;
import org.example.account.domain.Account;
import org.example.account.domain.AccountUser;
import org.example.account.dto.AccountDto;
import org.example.account.exception.AccountException;
import org.example.account.repository.AccountRepository;
import org.example.account.repository.AccountUserRepository;
import org.example.account.type.AccountStatus;
import org.example.account.type.ErrorCode;
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
        /**
         * 사용자가 없는 경우 실패
         */
        AccountUser accountUser = accountUserRepository.findById(userId)
                .orElseThrow(() -> new AccountException(USER_NOT_FOUND));

        validateCreateAccount(accountUser);

        String newAccount = getAccountNumber();

//        String newAccount = accountRepository.findFirstByOrderByIdDesc()
//                .map(account -> (Integer.parseInt(account.getAccountNumber())) + 1 + "")
//                .orElse("1000000000");


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
    private String getAccountNumber() {
        String accountNumber;
        do {
            accountNumber = String.format("%010d", (new Random()).nextInt(1_000_000_000));
        } while (!accountRepository.findByAccountNumber(accountNumber).isEmpty());
        return accountNumber;
    }

    private void validateCreateAccount(AccountUser accountUser) {
        /**
         * 계좌가 10개 인 경우 실패
         */
        if(accountRepository.countByAccountUser(accountUser) >= 10){
            throw new AccountException(MAX_ACCOUNT_PER_USER_10);
        }
    }

    public Account getAccount(Long id){
        if(id < 0){
            throw new RuntimeException("Minus");
        }
        return accountRepository.findById(id).get();
    }

    public AccountDto deleteAccount(Long userId, String accountNumber) {
        /**
         * 사용자가 없는 경우 실패
         */
        AccountUser accountUser = accountUserRepository.findById(userId)
                .orElseThrow(() -> new AccountException(USER_NOT_FOUND));

        /**
         * 계좌가 없는 경우 실패
         */
        Account account = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new AccountException(ACCOUNT_NOT_FOUND));

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
        /**
         * 사용자가 없는 경우 실패
         */
        AccountUser accountUser = accountUserRepository.findById(userId)
                .orElseThrow(() -> new AccountException(USER_NOT_FOUND));

        List<Account> accounts = accountRepository.findByAccountUser(accountUser);

        return accounts.stream()
                .map(account -> AccountDto.fromEntity(account))
                .collect(Collectors.toList());

    }
}

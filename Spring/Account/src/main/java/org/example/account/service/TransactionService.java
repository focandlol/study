package org.example.account.service;

import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.account.domain.Account;
import org.example.account.domain.AccountUser;
import org.example.account.domain.Transaction;
import org.example.account.dto.TransactionDto;
import org.example.account.exception.AccountException;
import org.example.account.repository.AccountRepository;
import org.example.account.repository.AccountUserRepository;
import org.example.account.repository.TransactionRepository;
import org.example.account.type.AccountStatus;
import org.example.account.type.TransactionResultType;
import org.example.account.type.TransactionType;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.example.account.type.ErrorCode.*;
import static org.example.account.type.TransactionResultType.*;
import static org.example.account.type.TransactionType.*;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountUserRepository accountUserRepository;
    private final AccountRepository accountRepository;

    public TransactionDto useBalance(Long userId, String accountNumber, Long amount){
        /**
         * 유저가 없을 경우 실패
         */
        AccountUser user = accountUserRepository.findById(userId)
                .orElseThrow(() -> new AccountException(USER_NOT_FOUND));

        Account account = getAccount(accountNumber);

        validateUserBalance(user,account,amount);

        account.useBalance(amount);

        return TransactionDto.fromEntity(saveAndGetTransaction(S, account, amount,USE));
    }

    private void validateUserBalance(AccountUser user, Account account, Long amount) {
        /**
         * 사용자 아이디와 계좌 소유주가 다른 경우 실패
         */
        if(!user.getId().equals(account.getAccountUser().getId())){
            throw new AccountException(USER_ACCOUNT_UN_MATCH);
        }

        /**
         * 계좌가 이미 해지 상태인 경우 실패
         */
        if(account.getAccountStatus() == AccountStatus.UNREGISTERED){
            throw new AccountException(ACCOUNT_ALREADY_UNREGISTERED);
        }

        /**
         * 거래금액이 잔액보다 큰 경우 실패
         */
        if(account.getBalance() < amount){
            throw new AccountException(AMOUNT_EXCEED_BALANCE);
        }

        /**
         * 거래금액이 너무 작거나 큰 경우 실패 응담
         * @valid로 잡아서 GlobalExceptionHandler 에서 처리
         */

    }

    /**
     * 잔액 사용 실패 시 호출
     */
    public void saveFailedUseTransaction(String accountNumber, Long amount) {
        Account account = getAccount(accountNumber);

        saveAndGetTransaction(F, account, amount,USE);

    }

    /**
     * 거래 저장 메서드
     */
    private Transaction saveAndGetTransaction(TransactionResultType transactionResultType,
                                              Account account, Long amount,TransactionType transactionType) {
        return transactionRepository.save(Transaction.builder()
                .transactionType(transactionType)
                .transactionResult(transactionResultType)
                .account(account)
                .amount(amount)
                .balanceSnapshot(account.getBalance())
                .transactionId(UUID.randomUUID().toString().replace("-", ""))
                .transactedAt(LocalDateTime.now())
                .build()
        );
    }

    public TransactionDto cancelBalance(String transactionId, String accountNumber, Long amount) {
        Transaction transaction = getTransaction(transactionId);

        Account account = getAccount(accountNumber);

        validateCancelBalance(transaction,account,amount);

        account.cancelBalance(amount);

        return TransactionDto.fromEntity(saveAndGetTransaction(S, account, amount,CANCEL));
    }


    private void validateCancelBalance(Transaction transaction, Account account, Long amount) {
        /**
         * 트랜잭션이 해당 계좌의 거래가 아닌 경우 실패
         */
        if(!transaction.getAccount().getId().equals(account.getId())){
            throw new AccountException(TRANSACTION_ACCOUNT_UN_MATCH);
        }

        /**
         * 원거래 금액과 취소 금액이 다른 경우 실패
         */
        if(!transaction.getAmount().equals(amount)){
            throw new AccountException(CANCEL_MUST_FULLY);
        }
    }

    /**
     * 잔액 사용 취소 실패 시 호출
     */
    public void saveFailedCancelTransaction(String accountNumber, Long amount) {
        Account account = getAccount(accountNumber);

        saveAndGetTransaction(F, account, amount,CANCEL);
    }

    public TransactionDto queryTransaction(String transactionId) {
        return TransactionDto.fromEntity(getTransaction(transactionId));
    }

    /**
     * 계좌가 없을 경우 실패
     */
    private Account getAccount(String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new AccountException(ACCOUNT_NOT_FOUND));
    }

    /**
     * 거래가 없을 경우 실패
     */
    private Transaction getTransaction(String transactionId) {
        return transactionRepository.findByTransactionId(transactionId)
                .orElseThrow(() -> new AccountException(TRANSACTION_NOT_FOUND));
    }
}

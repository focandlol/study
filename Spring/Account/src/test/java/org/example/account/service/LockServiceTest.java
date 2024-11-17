package org.example.account.service;

import org.example.account.exception.AccountException;
import org.example.account.type.ErrorCode;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class LockServiceTest {

    @Mock
    private RedissonClient redissonClient;

    @Mock
    private RLock rlock;

    @InjectMocks
    private LockService lockService;

    @Test
    void successGetLock() throws InterruptedException {
        given(redissonClient.getLock(anyString()))
                .willReturn(rlock);

        given(rlock.tryLock(anyLong(),anyLong(),any()))
                .willReturn(true);

        assertDoesNotThrow(() -> lockService.lock("123"));
    }

    @Test
    void failGetLock() throws InterruptedException {
        given(redissonClient.getLock(anyString()))
                .willReturn(rlock);

        given(rlock.tryLock(anyLong(),anyLong(),any()))
                .willReturn(false);

        AccountException accountException = assertThrows(AccountException.class, () -> lockService.lock("123"));

        assertEquals(accountException.getErrorCode(), ErrorCode.ACCOUNT_TRANSACTION_LOCK);
    }

}
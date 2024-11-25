package org.example.account.service;

import org.aspectj.lang.ProceedingJoinPoint;
import org.example.account.dto.UseBalance;
import org.example.account.exception.AccountException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.example.account.type.ErrorCode.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class LockAopAspectTest {

    @Mock
    private LockService lockService;

    @Mock
    private ProceedingJoinPoint proceedingJoinPoint;

    @InjectMocks
    private LockAopAspect lockAopAspect;

    @Test
    void lockAndUnLock() throws Throwable {
        ArgumentCaptor<String> lockCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> unlockCaptor = ArgumentCaptor.forClass(String.class);
        UseBalance.Request request = new UseBalance.Request(123L,"1234",1000L);

        lockAopAspect.aroundMethod(proceedingJoinPoint,request);

        verify(lockService,times(1)).lock(lockCaptor.capture());
        verify(lockService,times(1)).unlock(unlockCaptor.capture());

        assertEquals("1234",lockCaptor.getValue());
        assertEquals("1234",unlockCaptor.getValue());
    }

    @Test
    void lockAndUnLock_evenIfThrow() throws Throwable {
        ArgumentCaptor<String> lockCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> unlockCaptor = ArgumentCaptor.forClass(String.class);
        UseBalance.Request request = new UseBalance.Request(123L,"1234",1000L);

        given(proceedingJoinPoint.proceed())
                .willThrow(new AccountException(ACCOUNT_NOT_FOUND));

        assertThrows(AccountException.class,
                () -> lockAopAspect.aroundMethod(proceedingJoinPoint,request));

        verify(lockService,times(1)).lock(lockCaptor.capture());
        verify(lockService,times(1)).unlock(unlockCaptor.capture());

        assertEquals("1234",lockCaptor.getValue());
        assertEquals("1234",unlockCaptor.getValue());
    }
}
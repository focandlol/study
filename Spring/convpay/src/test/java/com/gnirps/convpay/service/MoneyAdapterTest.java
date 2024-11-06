package com.gnirps.convpay.service;

import com.gnirps.convpay.type.MoneyUseCancelResult;
import com.gnirps.convpay.type.MoneyUseResult;
import org.junit.jupiter.api.Test;

import static com.gnirps.convpay.type.MoneyUseResult.*;
import static org.junit.jupiter.api.Assertions.*;

class MoneyAdapterTest {
    MoneyAdapter moneyAdapter = new MoneyAdapter();

    @Test
    void money_use_fail(){
        Integer payAmount = 1000_001;

        MoneyUseResult moneyUseResult = moneyAdapter.use(payAmount);

        assertEquals(USE_FAIL,moneyUseResult);
    }

    @Test
    void money_use_success(){
        Integer payAmount = 1000_000;

        MoneyUseResult moneyUseResult = moneyAdapter.use(payAmount);

        assertEquals(USE_SUCCESS,moneyUseResult);
    }

    @Test
    void money_use_cancel_success(){
        Integer payCancelAmount = 101;

        MoneyUseCancelResult moneyUseCancelResult = moneyAdapter.useCancel(payCancelAmount);

        assertEquals(MoneyUseCancelResult.MONEY_USE_CANCEL_SUCCESS,moneyUseCancelResult);
    }

    @Test
    void money_use_cancel_fail(){
        Integer payCancelAmount = 99;

        MoneyUseCancelResult moneyUseCancelResult = moneyAdapter.useCancel(payCancelAmount);

        assertEquals(MoneyUseCancelResult.MONEY_USE_CANCEL_FAILED,moneyUseCancelResult);
    }

}
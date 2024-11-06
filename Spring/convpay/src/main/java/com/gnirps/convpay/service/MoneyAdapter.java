package com.gnirps.convpay.service;

import com.gnirps.convpay.type.MoneyUseCancelResult;
import com.gnirps.convpay.type.MoneyUseResult;

public class MoneyAdapter {

    public MoneyUseResult use(Integer payAmount){
        System.out.println("MoneyAdapter.use = " + payAmount);

        if(payAmount > 1000_000){
            return MoneyUseResult.USE_FAIL;
        }
        return MoneyUseResult.USE_SUCCESS;
    }

    public MoneyUseCancelResult useCancel(Integer payCancelAmount){
        System.out.println("MoneyAdapter.useCancel = " + payCancelAmount);

        if(payCancelAmount < 100){
            return MoneyUseCancelResult.MONEY_USE_CANCEL_FAILED;
        }
        return MoneyUseCancelResult.MONEY_USE_CANCEL_SUCCESS;
    }
}

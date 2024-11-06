package com.gnirps.convpay.service;

import com.gnirps.convpay.type.CardUseCancelResult;
import com.gnirps.convpay.type.CardUseResult;

public class CardAdapter {

    public void authorization(){
        System.out.println("authorization success");
    }

    public void approval(){
        System.out.println("approval success");
    }

    public CardUseResult capture(Integer payAmount){
        if(payAmount > 100){
            return CardUseResult.USE_FAIL;
        }
        return CardUseResult.USE_SUCCESS;
    }

    public CardUseCancelResult cancelCapture(Integer cancelAmount){
        if(cancelAmount > 100){
            return CardUseCancelResult.USE_CANCEL_FAIL;
        }
        return CardUseCancelResult.USE_CANCEL_SUCCESS;

    }
}

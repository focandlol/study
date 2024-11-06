package com.gnirps.convpay.service;

import com.gnirps.convpay.type.CancelPaymentResult;
import com.gnirps.convpay.type.CardUseCancelResult;
import com.gnirps.convpay.type.CardUseResult;
import com.gnirps.convpay.type.PaymentResult;

public class CardAdapter implements PaymentInterface{

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

    @Override
    public PaymentResult payment(Integer payAmount) {
        authorization();
        approval();
        CardUseResult cardUseResult = capture(payAmount);
        if(cardUseResult == CardUseResult.USE_FAIL){
            return PaymentResult.PAYMENT_FAIL;
        }
        return PaymentResult.PAYMENT_SUCCESS;
    }

    @Override
    public CancelPaymentResult cancelPayment(Integer cancelAmount) {
        CardUseCancelResult cardUseCancelResult = cancelCapture(cancelAmount);
        if(cardUseCancelResult == CardUseCancelResult.USE_CANCEL_FAIL){
            return CancelPaymentResult.CANCEL_PAYMENT_FAIL;
        }
        return CancelPaymentResult.CANCEL_PAYMENT_SUCCESS;
    }
}

package com.gnirps.convpay.service;

import com.gnirps.convpay.dto.*;
import com.gnirps.convpay.type.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
@Component
public class ConveniencePayService {
    private final Map<PayMethodType,PaymentInterface> paymentInterfaceMap = new HashMap<>();
    private final DiscountInterface discountInterface;

    public ConveniencePayService(Set<PaymentInterface> paymentInterfaceSet,
                                 DiscountInterface discountInterface) {
        paymentInterfaceSet.forEach(paymentInterface -> {
            paymentInterfaceMap.put(paymentInterface.getPayMethodType(),paymentInterface);
        });
        this.discountInterface = discountInterface;
    }

    public PayResponse pay(PayRequest payRequest){
        PaymentInterface paymentInterface = paymentInterfaceMap.get(payRequest.getPayMethodType());

        Integer discountedAmount = discountInterface.getDiscountedAmount(payRequest);
        PaymentResult payment = paymentInterface.payment(discountedAmount);

        if(payment == PaymentResult.PAYMENT_FAIL){
            return new PayResponse(PayResult.FAIL,0);
        }

        return new PayResponse(PayResult.SUCCESS, payRequest.getPayAmount());

    }

    public PayCancelResponse payCancel(PayCancelRequest payCancelRequest){
        PaymentInterface paymentInterface = paymentInterfaceMap.get(payCancelRequest.getPayMethodType());

        CancelPaymentResult cancelPaymentResult = paymentInterface.cancelPayment(payCancelRequest.getPayCancelAmount());

        if(cancelPaymentResult == CancelPaymentResult.CANCEL_PAYMENT_FAIL){
            return new PayCancelResponse(PayCancelResult.PAY_CANCEL_FAILED,0);
        }

        return new PayCancelResponse(PayCancelResult.PAY_CANCEL_SUCCESS,payCancelRequest.getPayCancelAmount());
    }
}

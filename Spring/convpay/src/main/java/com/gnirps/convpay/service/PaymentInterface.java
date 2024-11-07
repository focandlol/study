package com.gnirps.convpay.service;

import com.gnirps.convpay.type.CancelPaymentResult;
import com.gnirps.convpay.type.PayMethodType;
import com.gnirps.convpay.type.PaymentResult;

public interface PaymentInterface {
    PayMethodType getPayMethodType();
    PaymentResult payment(Integer payAmount);
    CancelPaymentResult cancelPayment(Integer cancelAmount);
}

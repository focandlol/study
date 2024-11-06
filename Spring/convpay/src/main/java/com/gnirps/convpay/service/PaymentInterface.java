package com.gnirps.convpay.service;

import com.gnirps.convpay.type.CancelPaymentResult;
import com.gnirps.convpay.type.PaymentResult;

public interface PaymentInterface {
    PaymentResult payment(Integer payAmount);
    CancelPaymentResult cancelPayment(Integer cancelAmount);
}

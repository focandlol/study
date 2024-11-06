package com.gnirps.convpay.service;

import com.gnirps.convpay.dto.PayCancelRequest;
import com.gnirps.convpay.dto.PayCancelResponse;
import com.gnirps.convpay.type.ConvenienceType;
import com.gnirps.convpay.dto.PayRequest;
import com.gnirps.convpay.dto.PayResponse;
import com.gnirps.convpay.type.PayCancelResult;
import com.gnirps.convpay.type.PayResult;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConveniencePayServiceTest {
    ConveniencePayService conveniencePayService = new ConveniencePayService();

    @Test
    void pay_success(){
        PayRequest payRequest = new PayRequest(ConvenienceType.G25, 50);

        PayResponse payResponse = conveniencePayService.pay(payRequest);

        assertEquals(PayResult.SUCCESS, payResponse.getPayResult());
        assertEquals(50, payRequest.getPayAmount());
    }

    @Test
    void pay_fail(){
        PayRequest payRequest = new PayRequest(ConvenienceType.G25, 1000_001);

        PayResponse payResponse = conveniencePayService.pay(payRequest);

        assertEquals(payResponse.getPayResult(), PayResult.FAIL);
        assertEquals(0,payResponse.getPaidAmount());
    }

    @Test
    void pay_cancel_success(){
        PayCancelRequest payCancelRequest = new PayCancelRequest(ConvenienceType.G25, 1500);
        PayCancelResponse payCancelResponse = conveniencePayService.payCancel(payCancelRequest);

        assertEquals(PayCancelResult.PAY_CANCEL_SUCCESS, payCancelResponse.getPayCancelResult());
        assertEquals(1500, payCancelResponse.getPayCancelAmount());
    }

    @Test
    void pay_cancel_fail(){
        PayCancelRequest payCancelRequest = new PayCancelRequest(ConvenienceType.G25, 50);
        PayCancelResponse payCancelResponse = conveniencePayService.payCancel(payCancelRequest);

        assertEquals(PayCancelResult.PAY_CANCEL_FAILED, payCancelResponse.getPayCancelResult());
        assertEquals(0, payCancelResponse.getPayCancelAmount());
    }
}
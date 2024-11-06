package com.gnirps.convpay;

import com.gnirps.convpay.dto.PayCancelRequest;
import com.gnirps.convpay.dto.PayCancelResponse;
import com.gnirps.convpay.dto.PayRequest;
import com.gnirps.convpay.dto.PayResponse;
import com.gnirps.convpay.service.ConveniencePayService;
import com.gnirps.convpay.type.ConvenienceType;

public class UserClient {
    public static void main(String[] args) {
        ConveniencePayService conveniencePayService = new ConveniencePayService();

        PayRequest payRequest = new PayRequest(ConvenienceType.SEVEN, 1000);
        PayResponse payResponse = conveniencePayService.pay(payRequest);

        System.out.println(payResponse);

        PayCancelRequest payCancelRequest = new PayCancelRequest(ConvenienceType.G25, 500);
        PayCancelResponse payCancelResponse = conveniencePayService.payCancel(payCancelRequest);
        System.out.println(payCancelResponse);

    }
}

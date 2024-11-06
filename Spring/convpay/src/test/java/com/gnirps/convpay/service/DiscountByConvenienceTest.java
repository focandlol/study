package com.gnirps.convpay.service;

import com.gnirps.convpay.dto.PayRequest;
import com.gnirps.convpay.type.ConvenienceType;
import com.gnirps.convpay.type.PayMethodType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DiscountByConvenienceTest {

    DiscountInterface discountByConvenience = new DiscountByConvenience();

    @Test
    void discountTest(){

        PayRequest payRequestG25 = new PayRequest(ConvenienceType.G25,1000,PayMethodType.MONEY);
        PayRequest payRequestGU = new PayRequest(ConvenienceType.GU,1000,PayMethodType.MONEY);
        PayRequest payRequestSeven = new PayRequest(ConvenienceType.SEVEN,1000,PayMethodType.MONEY);

        Integer discountedAmountG25 = discountByConvenience.getDiscountedAmount(payRequestG25);
        Integer discountedAmountGU = discountByConvenience.getDiscountedAmount(payRequestGU);
        Integer discountedAmountSeven = discountByConvenience.getDiscountedAmount(payRequestSeven);

        assertEquals(800,discountedAmountG25);
        assertEquals(900,discountedAmountGU);
        assertEquals(1000,discountedAmountSeven);
    }

}
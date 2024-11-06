package com.gnirps.convpay.service;

import com.gnirps.convpay.dto.PayRequest;
import com.gnirps.convpay.type.ConvenienceType;
import com.gnirps.convpay.type.PayMethodType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DiscountByPayMethodTest {
    DiscountInterface discountInterface = new DiscountByPayMethod();
    @Test
    void discountSuccess(){

        PayRequest payRequestMoney = new PayRequest(ConvenienceType.G25,1000, PayMethodType.MONEY);
        PayRequest payRequestCard = new PayRequest(ConvenienceType.G25,1000, PayMethodType.CARD);

        Integer discountAmountMoney = discountInterface.getDiscountedAmount(payRequestMoney);
        Integer discountAmountCard = discountInterface.getDiscountedAmount(payRequestCard);

        assertEquals(discountAmountMoney,700);
        assertEquals(discountAmountCard,1000);
    }


}
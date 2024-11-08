package com.gnirps.convpay.service;

import com.gnirps.convpay.dto.PayRequest;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("production")
public class DiscountByPayMethod implements DiscountInterface{

    @Override
    public Integer getDiscountedAmount(PayRequest payRequest) {
        switch(payRequest.getPayMethodType()){
            case MONEY -> {
                return payRequest.getPayAmount() * 7 / 10;
            }
            case CARD -> {
                return payRequest.getPayAmount();
            }
        }
        return payRequest.getPayAmount();
    }
}

package com.gnirps.convpay.service;

import com.gnirps.convpay.dto.PayCancelRequest;
import com.gnirps.convpay.dto.PayRequest;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("test")
public class DiscountByConvenience implements DiscountInterface{
    @Override
    public Integer getDiscountedAmount(PayRequest payRequest) {
       switch(payRequest.getConvenienceType()){
           case G25 -> {
               return payRequest.getPayAmount() * 8 / 10;
           }
           case GU -> {
               return payRequest.getPayAmount() * 9 / 10;
           }
           case SEVEN -> {
               return payRequest.getPayAmount();
           }
       }

       return payRequest.getPayAmount();

    }
}

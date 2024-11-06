package com.gnirps.convpay.dto;

import com.gnirps.convpay.type.ConvenienceType;

public class PayCancelRequest {

    ConvenienceType convenienceType;

    Integer payCancelAmount;

    public PayCancelRequest(ConvenienceType convenienceType, Integer payCancelAmount) {
        this.convenienceType = convenienceType;
        this.payCancelAmount = payCancelAmount;
    }

    public ConvenienceType getConvenienceType() {
        return convenienceType;
    }

    public void setConvenienceType(ConvenienceType convenienceType) {
        this.convenienceType = convenienceType;
    }

    public Integer getPayCancelAmount() {
        return payCancelAmount;
    }

    public void setPayCancelAmount(Integer payCancelAmount) {
        this.payCancelAmount = payCancelAmount;
    }
}

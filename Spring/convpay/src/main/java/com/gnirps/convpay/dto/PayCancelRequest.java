package com.gnirps.convpay.dto;

import com.gnirps.convpay.type.ConvenienceType;
import com.gnirps.convpay.type.PayCancelResult;
import com.gnirps.convpay.type.PayMethodType;

public class PayCancelRequest {

    PayMethodType payMethodType;

    ConvenienceType convenienceType;

    Integer payCancelAmount;


    public PayCancelRequest(PayMethodType payMethodType, ConvenienceType convenienceType, Integer payCancelAmount) {
        this.payMethodType = payMethodType;
        this.convenienceType = convenienceType;
        this.payCancelAmount = payCancelAmount;
    }

    public PayMethodType getPayMethodType() {
        return payMethodType;
    }

    public void setPayMethodType(PayMethodType payMethodType) {
        this.payMethodType = payMethodType;
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

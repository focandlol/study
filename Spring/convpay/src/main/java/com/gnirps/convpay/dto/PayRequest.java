package com.gnirps.convpay.dto;

import com.gnirps.convpay.type.ConvenienceType;
import com.gnirps.convpay.type.PayMethodType;

public class PayRequest {
    ConvenienceType convenienceType;

    Integer payAmount;

    PayMethodType payMethodType;

    public PayRequest(ConvenienceType convenienceType, Integer payAmount, PayMethodType payMethodType) {
        this.convenienceType = convenienceType;
        this.payAmount = payAmount;
        this.payMethodType = payMethodType;
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

    public Integer getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(Integer payAmount) {
        this.payAmount = payAmount;
    }
}

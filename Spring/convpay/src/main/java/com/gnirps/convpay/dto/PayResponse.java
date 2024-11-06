package com.gnirps.convpay.dto;

import com.gnirps.convpay.type.PayResult;

public class PayResponse {

    PayResult payResult;

    Integer paidAmount;

    public PayResponse(PayResult payResult, Integer paidAmount) {
        this.payResult = payResult;
        this.paidAmount = paidAmount;
    }

    public PayResult getPayResult() {
        return payResult;
    }

    public void setPayResult(PayResult payResult) {
        this.payResult = payResult;
    }

    public Integer getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(Integer paidAmount) {
        this.paidAmount = paidAmount;
    }

    @Override
    public String toString() {
        return "PayResponse{" +
                "payResult=" + payResult +
                ", paidAmount=" + paidAmount +
                '}';
    }
}

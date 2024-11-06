package com.gnirps.convpay.service;

import com.gnirps.convpay.dto.PayRequest;

public interface DiscountInterface {
    Integer getDiscountedAmount(PayRequest payRequest);
}

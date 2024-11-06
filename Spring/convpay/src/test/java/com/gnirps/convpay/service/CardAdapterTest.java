package com.gnirps.convpay.service;

import com.gnirps.convpay.type.CardUseResult;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class CardAdapterTest {
    CardAdapter cardAdapter = new CardAdapter();
    @Test
    void capture_success(){
        Integer payAmount = 99;

        CardUseResult capture = cardAdapter.capture(payAmount);
        assertEquals(CardUseResult.USE_SUCCESS,capture);
    }

    @Test
    void capture_fail(){
        Integer payAmount = 999;

        CardUseResult capture = cardAdapter.capture(payAmount);
        assertEquals(CardUseResult.USE_FAIL,capture);
    }

}
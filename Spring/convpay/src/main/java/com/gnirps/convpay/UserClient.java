package com.gnirps.convpay;

import com.gnirps.convpay.config.ApplicationConfig;
import com.gnirps.convpay.dto.PayCancelRequest;
import com.gnirps.convpay.dto.PayCancelResponse;
import com.gnirps.convpay.dto.PayRequest;
import com.gnirps.convpay.dto.PayResponse;
import com.gnirps.convpay.service.ConveniencePayService;
import com.gnirps.convpay.type.ConvenienceType;
import com.gnirps.convpay.type.PayMethodType;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class UserClient {
    public static void main(String[] args) {
        ApplicationContext ac = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        ConveniencePayService conveniencePayService = ac.getBean(ConveniencePayService.class);

        PayRequest payRequest = new PayRequest(ConvenienceType.G25, 1000,PayMethodType.MONEY);
        PayResponse payResponse = conveniencePayService.pay(payRequest);

        System.out.println(payResponse);

        PayCancelRequest payCancelRequest =
                new PayCancelRequest(PayMethodType.CARD,ConvenienceType.G25, 500);
        PayCancelResponse payCancelResponse = conveniencePayService.payCancel(payCancelRequest);
        System.out.println(payCancelResponse);

    }
}

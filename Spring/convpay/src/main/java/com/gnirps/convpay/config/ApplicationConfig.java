package com.gnirps.convpay.config;

import com.gnirps.convpay.service.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;

@Configuration
@ComponentScan(basePackages = "com.gnirps.convpay")
public class ApplicationConfig {

//    @Bean
//    public ConveniencePayService conveniencePayService(){
//        return new ConveniencePayService(
//                new HashSet<>(Arrays.asList(moneyAdapter(), cardAdapter())),
//                discountByConvenience()
//        );
//    }
//
//    @Bean
//    private static CardAdapter cardAdapter() {
//        return new CardAdapter();
//    }
//
//    @Bean
//    private static MoneyAdapter moneyAdapter() {
//        return new MoneyAdapter();
//    }
//
//    @Bean
//    private static DiscountByConvenience discountByConvenience() {
//        return new DiscountByConvenience();
//    }
}

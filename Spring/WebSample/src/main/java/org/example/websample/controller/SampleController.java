package org.example.websample.controller;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class SampleController {

    @GetMapping("/order/{orderId}")
    public String getOrder(@PathVariable String orderId) throws IllegalAccessException {
        log.info("Get some order");

        if("500".equals(orderId)){
            throw new IllegalAccessException("500 is not valid orderId");
        }
        return "orderId:" + orderId + " , orderAmount:1000";
    }

    @DeleteMapping("/order/{orderId}")
    public String deleteOrder(@PathVariable String orderId){
        log.info("Delete some order : " + orderId);
        return "Delete orderId:" + orderId;
    }

    @GetMapping("/order")
    public String getOrderWithRequestParam(@RequestParam(required = false, defaultValue = "defaultId") String orderId,
                                           @RequestParam Integer amount){
        log.info("Get order : " + orderId + ", amount : " + amount);
        return "orderId:" + orderId + " , orderAmount:" + amount;
    }

    @PostMapping("/order")
    public String createOrder(@RequestBody CreateOrderRequest createOrderRequest,
                                           @RequestHeader String userAccountId){
        log.info("Create order : " + createOrderRequest +
                ", userAccountId : " + userAccountId);
        return "orderId:" + createOrderRequest.getOrderId()
                + " , orderAmount:" + createOrderRequest.getOrderAmount();
    }

//    @PostMapping("/order")
//    public String createOrder(){
//        log.info("Create new order");
//        return "order created -> orderId:1, orderAmount:1000";
//    }

    @Data
    public static class CreateOrderRequest{
        private String orderId;
        private Integer orderAmount;
    }
}

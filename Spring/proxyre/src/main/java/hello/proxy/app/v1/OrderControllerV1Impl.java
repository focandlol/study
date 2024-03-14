package hello.proxy.app.v1;

import hello.proxy.app.v2.OrderControllerV2;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;

@Slf4j
public class OrderControllerV1Impl implements OrderControllerV1{

    private final OrderServiceV1 orderServiceV1;

    public OrderControllerV1Impl(OrderServiceV1 orderService) {
        this.orderServiceV1 = orderService;
    }

    @Override
    public String request(String itemId){
        StackTraceElement[] stacks = new Throwable().getStackTrace();
        StackTraceElement currentStack = stacks[0];
        String methodName = currentStack.getMethodName();
        log.info("methodName = {}",methodName);
        //Method method = this.getClass().getMethod(methodName);
        orderServiceV1.orderItem(itemId);
        return "ok";

    }

    @Override
    public String noLog() {
        return "ok";
    }
}

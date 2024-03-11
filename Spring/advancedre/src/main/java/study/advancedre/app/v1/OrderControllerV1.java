package study.advancedre.app.v1;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import study.advancedre.trace.hellotrace.HelloTraceV1;

@RestController
@RequiredArgsConstructor
public class OrderControllerV1 {

    private final OrderServiceV1 orderService;
    private final HelloTraceV1 trace;
    @GetMapping("/v1/request")
    public String request(String itemId){
        orderService.orderItem(itemId);
        return "ok";
    }
}

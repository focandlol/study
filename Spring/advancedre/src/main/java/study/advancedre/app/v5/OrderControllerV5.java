package study.advancedre.app.v5;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import study.advancedre.trace.callback.TraceTemplate;
import study.advancedre.trace.logtrace.LogTrace;
import study.advancedre.trace.template.AbstractTemplate;

@RestController

public class OrderControllerV5 {

    private final OrderServiceV5 orderService;
    private final TraceTemplate traceTemplate;

    public OrderControllerV5(OrderServiceV5 orderService, LogTrace trace) {
        this.orderService = orderService;
        this.traceTemplate = new TraceTemplate(trace);
    }

    @GetMapping("/v5/request")
    public String request(String itemId) {

        return traceTemplate.execute("OrderController.request",() -> {
            orderService.orderItem(itemId);
            return "ok";
        });

    }
}

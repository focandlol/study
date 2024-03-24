package study.advancedre.app.v4;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import study.advancedre.trace.TraceStatus;
import study.advancedre.trace.logtrace.LogTrace;
import study.advancedre.trace.template.AbstractTemplate;

@RestController
@RequiredArgsConstructor
public class OrderControllerV4 {

    private final OrderServiceV4 orderService;
    private final LogTrace trace;

    @GetMapping("/v4/request")
    public String request(String itemId) {

        AbstractTemplate<String> template = new AbstractTemplate<>(trace) {
            @Override
            protected String call() {
                orderService.orderItem(itemId);
                return "ok";
            }
        };
       // AbstractTemplate<String> template = new SubClassLogic(trace);
        String execute = template.execute("OrderController.request()");
        return execute;
    }
}

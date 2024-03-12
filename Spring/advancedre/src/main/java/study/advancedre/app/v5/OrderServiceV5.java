package study.advancedre.app.v5;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import study.advancedre.trace.callback.TraceTemplate;
import study.advancedre.trace.logtrace.LogTrace;
import study.advancedre.trace.template.AbstractTemplate;

@Service
public class OrderServiceV5 {
    private final OrderRepositoryV5 orderRepository;
    private final TraceTemplate traceTemplate;

    public OrderServiceV5(OrderRepositoryV5 orderRepository, LogTrace trace) {
        this.orderRepository = orderRepository;
        this.traceTemplate = new TraceTemplate(trace);
    }

    public void orderItem(String itemId) {

        traceTemplate.execute("OrderService.orderItem()",() -> {
            orderRepository.save(itemId);
            return null;
        });

    }
}

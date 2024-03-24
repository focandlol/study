package study.advancedre.app.v3;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import study.advancedre.trace.TraceId;
import study.advancedre.trace.TraceStatus;
import study.advancedre.trace.hellotrace.HelloTraceV2;
import study.advancedre.trace.logtrace.LogTrace;

@Service
@RequiredArgsConstructor
public class OrderServiceV3 {
    private final OrderRepositoryV3 orderRepository;
    private final LogTrace trace;
    public void orderItem(String itemId) {
        TraceStatus status = null;
        try {
            status = trace.begin("OrderService.orderItem()");
            orderRepository.save(itemId);
            trace.end(status);
        } catch (Exception e) {
            trace.exception(status, e);
            throw e;
        }
    }
}

package study.advancedre.app.v5;

import org.springframework.stereotype.Repository;
import study.advancedre.trace.callback.TraceTemplate;
import study.advancedre.trace.logtrace.LogTrace;
import study.advancedre.trace.template.AbstractTemplate;

@Repository
public class OrderRepositoryV5 {

    private final TraceTemplate traceTemplate;

    public OrderRepositoryV5(LogTrace trace) {
        this.traceTemplate = new TraceTemplate(trace);
    }

    public void save(String itemId){

        traceTemplate.execute("OrderRepository.save()",() ->{
            if(itemId.equals("ex")){
                throw new IllegalStateException("예외 발생");
            }
            sleep(1000);
            return null;
        });
    }

    private void sleep(int millis){
        try {
            Thread.sleep(millis);
        } catch(InterruptedException e){
            e.printStackTrace();
        }
    }
}

package study.advancedre.trace.strategy.code.template;

import lombok.extern.slf4j.Slf4j;
import study.advancedre.trace.strategy.code.Strategy;

@Slf4j
public class TimeLogTemplate {

    public void execute(Callback callback){
        long startTime = System.currentTimeMillis();
        callback.call();
        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTime={}",resultTime);
    }
}

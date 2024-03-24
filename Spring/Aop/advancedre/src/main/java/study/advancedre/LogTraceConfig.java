package study.advancedre;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import study.advancedre.trace.logtrace.FieldLogTrace;
import study.advancedre.trace.logtrace.LogTrace;
import study.advancedre.trace.logtrace.ThreadLocalLogTrace;

@Configuration
public class LogTraceConfig {

    @Bean
    public LogTrace logTrace(){
        return new ThreadLocalLogTrace();
    }
}

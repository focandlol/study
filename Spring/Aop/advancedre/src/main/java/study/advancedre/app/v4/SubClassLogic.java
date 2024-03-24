package study.advancedre.app.v4;

import study.advancedre.trace.logtrace.LogTrace;
import study.advancedre.trace.template.AbstractTemplate;

public class SubClassLogic<T> extends AbstractTemplate {
    public SubClassLogic(LogTrace trace) {
        super(trace);
    }

    @Override
    protected String call() {
        return "oasd";
    }
}

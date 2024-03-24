package hello.aopre.exam;

import hello.aopre.exam.annotation.Retry;
import hello.aopre.exam.annotation.Trace;
import org.springframework.stereotype.Repository;

@Repository
public class ExamRepository {

    private static int seq = 0;

    @Trace
    @Retry(4)
    public String save(String itemId){
        seq++;
        if(seq % 5 == 0){
            throw new IllegalStateException("예외 발생");
        }
        return "ok";
    }


}

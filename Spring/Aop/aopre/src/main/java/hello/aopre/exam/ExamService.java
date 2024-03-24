package hello.aopre.exam;

import hello.aopre.exam.annotation.Trace;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExamService {

    private final ExamRepository examRepository;

    @Trace
    public void request(String itemId){
        String save = examRepository.save(itemId);
       // log.info("save = {}",save);
    }

}

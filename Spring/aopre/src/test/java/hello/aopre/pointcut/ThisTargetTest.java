package hello.aopre.pointcut;

import hello.aopre.member.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Slf4j
@SpringBootTest(properties = "spring.aop.proxy-target-class=false")
@Import(ThisTargetTest.ThisTargetAspect.class)
public class ThisTargetTest {

    @Autowired
    MemberService memberService;

    @Test
    void success(){
        log.info("memberService proxy={}",memberService.getClass());
        memberService.hello("helloA");
    }

    @Aspect
    @Slf4j
    static class ThisTargetAspect{
        @Around("this(hello.aopre.member.MemberService)")
        public Object doThisInterface(ProceedingJoinPoint joinPoint) throws Throwable {
            log.info("[this-interface] {}",joinPoint.getSignature());
            return joinPoint.proceed();
        }

        @Around("target(hello.aopre.member.MemberService)")
        public Object doTargetInterface(ProceedingJoinPoint joinPoint) throws Throwable {
            log.info("[target-interface] {}",joinPoint.getSignature());
            return joinPoint.proceed();
        }

        @Around("this(hello.aopre.member.MemberServiceImpl)")
        public Object doThis(ProceedingJoinPoint joinPoint) throws Throwable {
            log.info("[this-impl] {}",joinPoint.getSignature());
            return joinPoint.proceed();
        }

        @Around("target(hello.aopre.member.MemberServiceImpl)")
        public Object doTarget(ProceedingJoinPoint joinPoint) throws Throwable {
            log.info("[target-impl] {}",joinPoint.getSignature());
            return joinPoint.proceed();
        }
    }
}

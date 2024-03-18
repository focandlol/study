package hello.aopre.pointcut;

import hello.aopre.member.MemberService;
import hello.aopre.member.MemberService2;
import hello.aopre.member.annotation.ClassAop;
import hello.aopre.member.annotation.MethodAop;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Slf4j
@SpringBootTest
@Import(ParameterTest.ParameterAspect.class)
public class ParameterTest {

    @Autowired
    MemberService2 memberService;

    @Test
    void success(){
        log.info("memberService proxy = {}",memberService.getClass());
        memberService.hello("helloA","a","b");
    }

    @Slf4j
    @Aspect
    static class ParameterAspect{
        @Pointcut("execution(* hello.aopre.member..*.*(..))")
        private void allMember(){}

        @Around("allMember()")
        public Object logArgs1(ProceedingJoinPoint joinPoint) throws Throwable {
            Object arg1 = joinPoint.getArgs();
            log.info("[logArgs2]{}, arg={}",joinPoint.getSignature(),arg1);
            return joinPoint.proceed();
        }

        @Around("allMember() && args(arg,..)")
        public Object logArgs2(ProceedingJoinPoint joinPoint,Object arg) throws Throwable {
            log.info("[logArgs]{}, arg={}",joinPoint.getSignature(),arg);
            return joinPoint.proceed();
        }

        @Before("allMember() && args(arg,..)")
        public void logArgs(String arg){
            log.info("[logArgs3] arg={}",arg);
        }

        @Before("allMember() && this(obj)")
        public void thisArgs(JoinPoint joinPoint,MemberService2 obj){
            log.info("[this]{}, obj={}",joinPoint.getSignature(),obj.getClass());
        }

        @Before("allMember() && target(obj)")
        public void targetArgs(JoinPoint joinPoint,MemberService2 obj){
            log.info("[target]{}, obj={}",joinPoint.getSignature(),obj.getClass());
        }

        @Before("allMember() && @target(annotation)")
        public void atTarget(JoinPoint joinPoint, ClassAop annotation){
            log.info("[@target]{}, obj={}",joinPoint.getSignature(),annotation);
        }

        @Before("allMember() && @within(annotation)")
        public void atWithin(JoinPoint joinPoint, ClassAop annotation){
            log.info("[@within]{}, obj={}",joinPoint.getSignature(),annotation);
        }

        @Before("allMember() && @annotation(annotation)")
        public void atAnnotation(JoinPoint joinPoint, MethodAop annotation){
            log.info("[@annotation]{}, obj={}",joinPoint.getSignature(),annotation.value());
        }

    }
}

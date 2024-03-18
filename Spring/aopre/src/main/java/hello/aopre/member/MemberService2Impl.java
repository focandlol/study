package hello.aopre.member;

import hello.aopre.member.annotation.ClassAop;
import hello.aopre.member.annotation.MethodAop;
import org.springframework.stereotype.Component;

@ClassAop
@Component
public class MemberService2Impl implements MemberService2{


    @Override
    @MethodAop("test value")
    public String hello(String param,String a, String b) {
        return "ok";
    }

    @Override
    public String kkm() {
        return null;
    }

    public String internal(String param){
        return "ok";
    }
}

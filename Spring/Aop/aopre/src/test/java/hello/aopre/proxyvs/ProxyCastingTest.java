package hello.aopre.proxyvs;

import hello.aopre.member.MemberService;
import hello.aopre.member.MemberServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.aop.framework.ProxyFactory;

import static org.assertj.core.api.Assertions.*;

@Slf4j
public class ProxyCastingTest {

    @Test
    void jdkProxy(){
        MemberServiceImpl target = new MemberServiceImpl();
        ProxyFactory proxyFactory = new ProxyFactory(target);
        proxyFactory.setProxyTargetClass(false);

        MemberService memberServiceProxy = (MemberService) proxyFactory.getProxy();

        //jdk 동적 프록시를 구현 클래스로 캐스팅 시도 실패 ClassCastException 예외 발생

        assertThatThrownBy(() -> {
            MemberServiceImpl castingMemberService = (MemberServiceImpl) memberServiceProxy;
        }).isInstanceOf(ClassCastException.class);
    }

    @Test
    void cglibProxy(){
        MemberServiceImpl target = new MemberServiceImpl();
        ProxyFactory proxyFactory = new ProxyFactory(target);
        proxyFactory.setProxyTargetClass(true);

        MemberService memberServiceProxy = (MemberService) proxyFactory.getProxy();

        //cglib 동적 프록시를 구현 클래스로 캐스팅 시도 성공

        MemberServiceImpl castingMemberService = (MemberServiceImpl) memberServiceProxy;

    }

}

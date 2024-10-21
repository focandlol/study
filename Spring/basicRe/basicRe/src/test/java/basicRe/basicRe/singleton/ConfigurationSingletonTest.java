package basicRe.basicRe.singleton;

import basicRe.basicRe.AppConfig;
import basicRe.basicRe.member.MemberRepository;
import basicRe.basicRe.member.MemberService;
import basicRe.basicRe.member.MemberServiceImpl;
import basicRe.basicRe.order.OrderService;
import basicRe.basicRe.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;

public class ConfigurationSingletonTest {

    @Test
    void configurationTest(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberServiceImpl memberService = ac.getBean(MemberServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean(OrderServiceImpl.class);
        MemberRepository memberRepository = ac.getBean(MemberRepository.class);

        assertThat(memberService.getMemberRepository())
                .isSameAs(orderService.getMemberRepository())
                .isSameAs(memberRepository);
    }

    @Test
    void configurationDeep(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        AppConfig bean = ac.getBean(AppConfig.class);

        System.out.println("bean = " + bean.getClass());
    }
}

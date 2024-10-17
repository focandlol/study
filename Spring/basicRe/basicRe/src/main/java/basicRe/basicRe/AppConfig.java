package basicRe.basicRe;

import basicRe.basicRe.discount.DiscountPolicy;
import basicRe.basicRe.discount.FixDiscountPolicy;
import basicRe.basicRe.discount.RateDiscountPolicy;
import basicRe.basicRe.member.MemberRepository;
import basicRe.basicRe.member.MemberService;
import basicRe.basicRe.member.MemberServiceImpl;
import basicRe.basicRe.member.MemoryMemberRepository;
import basicRe.basicRe.order.OrderService;
import basicRe.basicRe.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public MemberService memberService(){
        return new MemberServiceImpl(memberRepository());
    }
    @Bean
    public OrderService orderService(){
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }
    @Bean
    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }
    @Bean
    public DiscountPolicy discountPolicy(){
        //return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }

}

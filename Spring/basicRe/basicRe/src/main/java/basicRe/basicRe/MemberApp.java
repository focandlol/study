package basicRe.basicRe;

import basicRe.basicRe.member.Grade;
import basicRe.basicRe.member.Member;
import basicRe.basicRe.member.MemberService;
import basicRe.basicRe.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {
    public static void main(String[] args) {
       ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(member.getId());
        System.out.println("new Member = " + member.getName());
    }
}

package basicRe.basicRe;

import basicRe.basicRe.member.Grade;
import basicRe.basicRe.member.Member;
import basicRe.basicRe.member.MemberService;
import basicRe.basicRe.member.MemberServiceImpl;

public class MemberApp {
    public static void main(String[] args) {
        MemberService memberService = new MemberServiceImpl();
        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMmeber = memberService.findMember(member.getId());
        System.out.println("new Member = " + member.getName());
    }
}

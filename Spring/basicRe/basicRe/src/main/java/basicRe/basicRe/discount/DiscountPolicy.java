package basicRe.basicRe.discount;

import basicRe.basicRe.member.Member;

public interface DiscountPolicy {

    int discount(Member member, int price);
}

package basicRe.basicRe.order;

import basicRe.basicRe.discount.DiscountPolicy;
import basicRe.basicRe.discount.FixDiscountPolicy;
import basicRe.basicRe.discount.RateDiscountPolicy;
import basicRe.basicRe.member.Member;
import basicRe.basicRe.member.MemberRepository;
import basicRe.basicRe.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository = new MemoryMemberRepository();
    //private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findMember(memberId);
        int discount = discountPolicy.discount(member, itemPrice);

        return new Order(memberId,itemName,itemPrice,discount);
    }
}

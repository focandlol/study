package basicRe.basicRe.order;

import basicRe.basicRe.annotation.MainDiscountPolicy;
import basicRe.basicRe.discount.DiscountPolicy;
import basicRe.basicRe.discount.FixDiscountPolicy;
import basicRe.basicRe.discount.RateDiscountPolicy;
import basicRe.basicRe.member.Member;
import basicRe.basicRe.member.MemberRepository;
import basicRe.basicRe.member.MemberService;
import basicRe.basicRe.member.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository;
    //private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    private final DiscountPolicy discountPolicy;

    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository,@MainDiscountPolicy DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findMember(memberId);
        int discount = discountPolicy.discount(member, itemPrice);

        return new Order(memberId,itemName,itemPrice,discount);
    }

    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}

package basicRe.basicRe.discount;

import basicRe.basicRe.member.Grade;
import basicRe.basicRe.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class RateDiscountPolicyTest {
    RateDiscountPolicy rateDiscountPolicy = new RateDiscountPolicy();

    @Test
    @DisplayName("vip는 10% 할인이 적용되어야 한다")
    void vip_o(){
        Member member = new Member(1L, "memberVip", Grade.VIP);

        int discount = rateDiscountPolicy.discount(member, 10000);

        assertThat(discount).isEqualTo(1000);

    }

    @Test
    @DisplayName("vip가 아니면 할인 x")
    void vip_x(){
        Member member = new Member(1L, "memberVip", Grade.BASIC);

        int discount = rateDiscountPolicy.discount(member, 10000);

        assertThat(discount).isEqualTo(0);

    }
}
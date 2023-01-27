package hello.core.orderServiceTest;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.Order;
import hello.core.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class OrderServiceImpleTest {
    @Test
    void createOrder(){
        MemoryMemberRepository memberRepository = new MemoryMemberRepository();
        memberRepository.save(new Member(1L,"name", Grade.VIP));
        DiscountPolicy discountPolicy = new RateDiscountPolicy();

        OrderServiceImpl orderService = new OrderServiceImpl(discountPolicy, memberRepository);
        Order order = orderService.createOrder(1L,"item",10000);
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);

    }
}

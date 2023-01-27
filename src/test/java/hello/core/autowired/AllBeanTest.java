package hello.core.autowired;

import hello.core.AppConfig;
import hello.core.AutoAppConfig;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Map;

public class AllBeanTest {
    @Test
    void findAllBean(){

        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class, DiscountService.class);

        DiscountService discountService = ac.getBean(DiscountService.class);
        Member member= new Member(1L, "userA", Grade.VIP);
        int discountPrice= discountService.discount(member,10000, "fixDiscountPolicy");
    }

    static class DiscountService{
        private final Map<String, DiscountPolicy> policyMap;
        private final List<DiscountPolicy> policies;

        public int discount(Member member, int price, String discountCode){
            DiscountPolicy discountPolicy = policyMap.get(discountCode);
            return discountPolicy.discount(member,price);
        }

        public DiscountService(Map<String, DiscountPolicy> plicyMap, List<DiscountPolicy> policies) {
            this.policyMap = plicyMap;
            this.policies = policies;
            System.out.println("plicyMap = " + plicyMap);
            System.out.println("policies = " + policies);
        }
    }
}

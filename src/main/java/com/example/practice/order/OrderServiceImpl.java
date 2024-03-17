package com.example.practice.order;

import com.example.practice.annotation.MainDiscountPolicy;
import com.example.practice.discount.DiscountPolicy;
import com.example.practice.member.Member;
import com.example.practice.member.MemberRepository;
import org.springframework.stereotype.Component;

@Component
//@RequiredArgsConstructor    // final field의 생성자를 자동으로 만들어줌
public class OrderServiceImpl implements OrderService {
    /*
    할인 정책을 바꾸기위해선 OrderServiceImple을 수정해야한다. 변경시 자유로운 확장을 못함 => OCP 위반
    추상클래스(인터페이스)를 의존 O But 구현클래스 Fix,RateDiscountPolicy도 의존 O => DIP 위반
    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
    */

    // 해결방안 : 인터페이스만 의존하도록 변경, 다른 무언가 클라이언트인 Impl에 DiscountPolicy의 구현객체를 대신 생성하고 주입해줘야 한다.
    private final DiscountPolicy discountPolicy;
    private final MemberRepository memberRepository;

    public OrderServiceImpl(/*@Qualifier("mainDiscountPolicy")*/ @MainDiscountPolicy DiscountPolicy discountPolicy, MemberRepository memberRepository) {
        this.discountPolicy = discountPolicy;
        this.memberRepository = memberRepository;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    // Test
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}

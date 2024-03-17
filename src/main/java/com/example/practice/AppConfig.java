package com.example.practice;

import com.example.practice.discount.DiscountPolicy;
import com.example.practice.discount.RateDiscountPolicy;
import com.example.practice.member.MemberRepository;
import com.example.practice.member.MemberService;
import com.example.practice.member.MemberServiceImpl;
import com.example.practice.member.MemoryMemberRepository;
import com.example.practice.order.OrderService;
import com.example.practice.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 각 Impl에 생성자 주입을 통한 관심사 분리
@Configuration
public class AppConfig {

    // @Bean memberService -> new MemoryMemberRepository()
    // @Bean orderService -> new MemoryMemberRepository()
    // Singleton 깨지나? => Test

    // 리팩터링을 통한 중복요소 제거
    @Bean   // Spring Container에 등록하는 어노테이션
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        return new RateDiscountPolicy();
    }

    @Bean
    public MemberService memberService() {
        System.out.println("call AppConfig.memberService");
        return  new MemberServiceImpl(memberRepository());
    }

    @Bean
    public OrderService orderService() {
        System.out.println("call AppConfig.orderService");
        return  new OrderServiceImpl(discountPolicy(), memberRepository());
    }
}
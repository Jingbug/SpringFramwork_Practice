package com.example.practice;

import com.example.practice.member.Grade;
import com.example.practice.member.Member;
import com.example.practice.member.MemberService;
import com.example.practice.order.Order;
import com.example.practice.order.OrderService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class OrderApp {
    public static void main(String[] args) {
        /*
        AppConfig appConfig = new AppConfig();
        MemberService memberService = appConfig.memberService();
        OrderService orderService = appConfig.orderService();
        */

        // ApplicationContext -> Spring Container
        // @Bean으로 등록되어 있는 메서드를 모두 호출하여 Container에 등록한다. -> 이러한 Bean들을 Spring Bean이라고 한다.
        // Spring Bean은 메서드 이름을 Bean 이름으로 사용한다.
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
        OrderService orderService = applicationContext.getBean("orderService", OrderService.class);

        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 20000);
        System.out.println("order = " + order);
        System.out.println("order calculatorPrice = " + order.calculatorPrice());
    }
}

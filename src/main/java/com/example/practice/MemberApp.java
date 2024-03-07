package com.example.practice;

import com.example.practice.member.Grade;
import com.example.practice.member.Member;
import com.example.practice.member.MemberService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {
    public static void main(String[] args) {
        /*
        AppConfig appConfig = new AppConfig();
        MemberService memberService = appConfig.memberService();
        */

        // ApplicationContext -> Spring Container
        // @Bean으로 등록되어 있는 메서드를 모두 호출하여 Container에 등록한다. -> 이러한 Bean들을 Spring Bean이라고 한다.
        // Spring Bean은 메서드 이름을 Bean 이름으로 사용한다.
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);

        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("new Member = " + member.getName());
        System.out.println("find Member = " + findMember.getName());
    }
}

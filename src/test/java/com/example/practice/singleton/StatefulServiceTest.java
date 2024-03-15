package com.example.practice.singleton;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.assertj.core.api.Assertions.assertThat;

class StatefulServiceTest {

    @Test
    void statefulServiceSingleton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        // Thread A : 사용자 A가 10000원을 주문
        int userA = statefulService1.order("userA", 10000);
        // Thread B : 사용자 B가 20000원을 주문
        int userB = statefulService2.order("userB", 20000);

        // Thread A : 사용자 A 주문 금액 조회
//        int price = statefulService1.getPrice();
        System.out.println("userA price = " + userA);
        System.out.println("userB price = " + userB);

//        assertThat(userA).isEqualTo(10000);
    }

    static class TestConfig {
        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }

}
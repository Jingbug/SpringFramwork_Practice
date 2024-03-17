package com.example.practice.discount;

import com.example.practice.member.Grade;
import com.example.practice.member.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
//@Qualifier("fixDiscountPolicy")
public class FixDiscountPolicy implements DiscountPolicy {

    // 고정 할인 정책
    private int discountFixAmount = 1000; // 1000원 할인

    @Override
    public int discount(Member member, int price) {
        if(member.getGrade() == Grade.VIP) { // enum type은 '==' 사용가능
            return discountFixAmount;
        } else{
            return 0;
        }
    }
}

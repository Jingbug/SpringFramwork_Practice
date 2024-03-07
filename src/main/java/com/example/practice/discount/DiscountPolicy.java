package com.example.practice.discount;

import com.example.practice.member.Member;

public interface DiscountPolicy {

    // @return 할인 대상 금액
    int discount(Member member, int price);
}
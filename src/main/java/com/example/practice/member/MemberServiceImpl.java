package com.example.practice.member;

public class MemberServiceImpl implements MemberService {

    // 인터페이스와 구현 객체에 모두 의존 DIP 위반 -> 나중에 변경시 문제 발생
    // private final MemberRepository memberRepository = new MemoryMemberRepository();

    private final MemberRepository memberRepository;
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}

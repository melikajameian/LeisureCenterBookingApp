package application.services;

import domain.entities.Member;
import domain.repositories.MemberRepository;

import java.util.ArrayList;
import java.util.List;

public class MemberService {
    private MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public void createMember(String firstName, String lastName) {
        memberRepository.add(new Member(firstName, lastName));
    }

    public List<Member> getMembers() {
        return memberRepository.getAll();
    }

    public Member getMemberById(String id) {
        return memberRepository.getById(id);
    }
}

package application.services;

import domain.entities.Member;

import java.util.ArrayList;
import java.util.List;

public class MemberService {
    private final List<Member> members;   // ← field

    public MemberService() {
        this.members = new ArrayList<Member>();        // ← map created here
    }

    public void createMember(String firstName, String lastName) {
        members.add(new Member(firstName, lastName));
    }

    public List<Member> getMembers() {
        return members;
    }
}

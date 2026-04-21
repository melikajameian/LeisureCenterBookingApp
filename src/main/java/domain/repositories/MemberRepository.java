package domain.repositories;

import domain.entities.Member;

import java.util.ArrayList;
import java.util.List;

public class MemberRepository {
    private List<Member> members ;

    public void add(Member member) {
        members.add(member);
    }

    public List<Member> getAll() {
        return new ArrayList<>(members);
    }
}

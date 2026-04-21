package domain.repositories;

import domain.entities.Member;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class MemberRepository {
    private List<Member> members= new ArrayList<>(); ;

    public void add(Member member) {
        members.add(member);
    }

    public List<Member> getAll() {
        return new ArrayList<>(members);
    }

    public Member getById(String id) {
        return members.stream().filter(m -> m.getId().equals(id)).findFirst().orElse(null);
    }
}

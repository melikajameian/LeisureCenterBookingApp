package infrastructure.factories;

import application.services.MemberService;
import domain.entities.Member;

import java.util.ArrayList;
import java.util.List;

public class MemberFactory {
    public static void createMembers(MemberService memberService) {
        memberService.createMember("Melika","Jameian");
        memberService.createMember("Nadim","Hajizadeh");
        memberService.createMember("Bill","Burton");
        memberService.createMember("Stephanie","Max");
    }
}

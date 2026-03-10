package infrastructure.factories;

import application.services.LessonService;
import application.services.MemberService;

public class DataFactory {

        public static void initialize(LessonService lessonService, MemberService memberService) {
            LessonFactory.createLessons(lessonService);
            MemberFactory.createMembers(memberService);
        }

}

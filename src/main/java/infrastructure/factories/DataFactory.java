package infrastructure.factories;

import application.services.LessonService;
import application.services.MemberService;
import application.services.SessionService;

public class DataFactory {

        public static void initialize(LessonService lessonService, MemberService memberService, SessionService sessionService) {
            LessonFactory.createLessons(lessonService);
            MemberFactory.createMembers(memberService);
            SessionFactory.createSessions(sessionService,lessonService);
        }

}

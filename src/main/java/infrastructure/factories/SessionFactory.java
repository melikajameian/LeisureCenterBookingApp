package infrastructure.factories;

import application.services.LessonService;
import application.services.SessionService;
import domain.entities.Lesson;
import domain.entities.Session;
import domain.entities.SessionDate;
import domain.enums.LessonType;
import domain.enums.Month;
import domain.enums.TimeSlot;
import org.jetbrains.annotations.NotNull;

import java.util.Random;


public class SessionFactory {

    public static void createSessions(SessionService sessionService, LessonService lessonService) {
        Lesson yoga = lessonService.getLesson(LessonType.Yoga);
        Lesson box = lessonService.getLesson(LessonType.Box);
        Lesson hiit = lessonService.getLesson(LessonType.HIIT);
        Lesson pilates = lessonService.getLesson(LessonType.Pilates);
        Lesson zumba = lessonService.getLesson(LessonType.Zumba);
        Lesson[] lessons = {yoga,box,hiit,pilates,zumba};
        Random generate = new Random();

        TimeSlot morning = TimeSlot.morning;
        TimeSlot afternoon = TimeSlot.afternoon;
        TimeSlot evening = TimeSlot.evening;

        int[] monthDays = {1, 2, 8, 9, 15, 16, 22, 23};
        Month[] months = {Month.JUNE,Month.JULY};

        // Generating sessions for two weeks
        for(Month month : months ){
            for (int monthDay : monthDays) {
                sessionService.createSession(lessons[generate.nextInt(5)], getSessionDate(month, monthDay), morning,4);
                sessionService.createSession(lessons[generate.nextInt(5)], getSessionDate(month, monthDay), afternoon, 2);
                sessionService.createSession(lessons[generate.nextInt(5)], getSessionDate(month, monthDay), evening, 3);
            }
        }
    }

    private static SessionDate getSessionDate(Month month, int monthDay) {
        return new SessionDate(month, monthDay);
    }

}

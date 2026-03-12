package infrastructure.factories;

import application.services.LessonService;
import application.services.SessionService;
import domain.entities.Lesson;
import domain.entities.Session;
import domain.entities.SessionDate;
import domain.enums.LessonType;
import domain.enums.TimeSlot;

import java.util.Random;


public class SessionFactory {
    public SessionFactory() {
    }

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

        for(int month = 1; month <= 2; month++){
            for(int index = 0; index < monthDays.length; index++){
                sessionService.createSession(lessons[generate.nextInt(5)], new SessionDate(month,monthDays[index]),morning);
                sessionService.createSession(lessons[generate.nextInt(5)], new SessionDate(month,monthDays[index]),afternoon);
                sessionService.createSession(lessons[generate.nextInt(5)], new SessionDate(month,monthDays[index]),evening);

            }
        }
    }

}

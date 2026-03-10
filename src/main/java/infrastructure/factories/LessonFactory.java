package infrastructure.factories;

import application.services.LessonService;
import domain.enums.LessonType;


public class LessonFactory {
    public static void createLessons(LessonService lessonService) {

        lessonService.createLesson(LessonType.Yoga, 10);
        lessonService.createLesson(LessonType.Zumba, 12);
        lessonService.createLesson(LessonType.HIIT, 15);
        lessonService.createLesson(LessonType.Pilates, 9);
        lessonService.createLesson(LessonType.Box, 9);

    }

}

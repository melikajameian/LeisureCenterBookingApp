package application.services;

import domain.enums.LessonType;
import domain.repositories.LessonRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class LessonServiceTest {

    @Test
    void createLesson_shouldRejectDuplicateLessonTypes() {
        LessonService lessonService = new LessonService(new LessonRepository());

        lessonService.createLesson(LessonType.Yoga, 10);

        assertThrows(IllegalStateException.class,
                () -> lessonService.createLesson(LessonType.Yoga, 12));
    }
}

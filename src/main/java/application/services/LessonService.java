package application.services;

import domain.entities.Lesson;
import domain.enums.LessonType;

import java.util.HashMap;
import java.util.Map;

public class LessonService {

    private final Map<LessonType, Lesson> lessons;   // ← field

    public LessonService() {
        this.lessons = new HashMap<>();        // ← map created here
    }

    public void createLesson(LessonType type, double price) {

        if (lessons.containsKey(type)) {
            throw new IllegalStateException("Lesson already exists for this type");
        }

        Lesson lesson = new Lesson(type, price);
        lessons.put(type, lesson);

    }

    public Lesson getLesson(LessonType type) {
        return lessons.get(type);
    }

    public Map<LessonType, Lesson> getLessonsMap() {
        return lessons;
    }

}
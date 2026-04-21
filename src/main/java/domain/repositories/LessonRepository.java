package domain.repositories;

import domain.entities.Lesson;
import domain.enums.LessonType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LessonRepository {
    private final Map<LessonType, Lesson> lessons = new HashMap<>();

    public void add(Lesson lesson) {
        lessons.put(lesson.getLessonType(), lesson);
    }

    public Lesson getByType(LessonType type) {
        return lessons.get(type);
    }

    public boolean exists(LessonType type) {
        return lessons.containsKey(type);
    }

    public List<Lesson> getLessons() {
        return new ArrayList<>(lessons.values());
    }



}

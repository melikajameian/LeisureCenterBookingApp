package application.services;

import domain.entities.Lesson;
import domain.enums.LessonType;
import domain.repositories.LessonRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LessonService {

    private LessonRepository lessonRepository;

    public LessonService(LessonRepository lessonRepository) {
        this.lessonRepository = lessonRepository;
    }

    public void createLesson(LessonType type, double price) {

        if (lessonRepository.exists(type)) {
            throw new IllegalStateException("Lesson already exists for this type");
        }

        Lesson lesson = new Lesson(type, price);
        lessonRepository.add(lesson);

    }

    public Lesson getLesson(LessonType type) {
        return lessonRepository.getByType(type);
    }


    public List<Lesson> getLessons() {
        return lessonRepository.getLessons() ;
    }

}
package domain.entities;

import domain.enums.LessonType;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Lesson {
    private LessonType lessonType;
    private double price;
    private List<Session> sessionList;

    public Lesson(LessonType lessonType, double price) {
        this.lessonType = lessonType;
        this.price = price;
        this.sessionList = new ArrayList<>();
    }

    public LessonType getLessonType() {
        return lessonType;
    }

    public double getPrice() {
        return price;
    }

    public List<Session> getSessionList() {
        return sessionList;
    }


}

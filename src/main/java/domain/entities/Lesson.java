package domain.entities;

import domain.enums.LessonType;

public class Lesson {
    private LessonType lessonType;
    private double price;

    public Lesson(LessonType lessonType, double price) {
        this.lessonType = lessonType;
        this.price = price;
    }

    public LessonType getLessonType() {
        return lessonType;
    }

    public double getPrice() {
        return price;
    }


}

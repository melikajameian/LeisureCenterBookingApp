package domain.entities;

import domain.enums.LessonType;

import java.util.HashSet;

public class Lesson {
    LessonType lessonType;
    double price;
    HashSet<Session> sessionList;
}

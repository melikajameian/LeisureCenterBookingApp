package domain.entities;


import domain.enums.TimeSlot;

public class Session {
    Lesson lesson;
    Day day;
    TimeSlot timeSlot;

    public Session(Lesson lesson, Day day, TimeSlot timeSlot) {
        this.lesson = lesson;
        this.day = day;
        this.timeSlot = timeSlot;
    }
}

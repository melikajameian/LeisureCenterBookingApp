package domain.entities;


import domain.enums.TimeSlot;

public class Session {
    private final Lesson lesson;
    private final SessionDate day;
    private final TimeSlot timeSlot;

    public Session(Lesson lesson, SessionDate day, TimeSlot timeSlot) {
        this.lesson = lesson;
        this.day = day;
        this.timeSlot = timeSlot;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public SessionDate getSessionDate() {
        return day;
    }

    public TimeSlot getTimeSlot() {
        return timeSlot;
    }

    @Override
    public String toString() {
        return day.getFullDateString() +
                " | " + lesson.getLessonType().toString() +
                " | " + timeSlot;
    }
}

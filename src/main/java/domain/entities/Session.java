package domain.entities;


import domain.enums.TimeSlot;

import java.util.ArrayList;

public class Session {
    private final Lesson lesson;
    private final SessionDate day;
    private final TimeSlot timeSlot;
    private final int capacity;
    private ArrayList<Booking> bookings;

    public Session(Lesson lesson, SessionDate day, TimeSlot timeSlot, int capacity) {
        this.lesson = lesson;
        this.day = day;
        this.timeSlot = timeSlot;
        this.capacity = capacity;
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

    public boolean isFull() {
        return capacity == bookings.size();
    }

    public void addBooking(Booking booking) {
        bookings.add(booking);
    }

    @Override
    public String toString() {
        return day.getFullDateString() +
                " | " + lesson.getLessonType().toString() +
                " | " + timeSlot;
    }
}

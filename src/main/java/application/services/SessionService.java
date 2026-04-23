package application.services;

import domain.entities.*;
import domain.enums.BookingStatus;
import domain.enums.DayOfWeek;
import domain.enums.Month;
import domain.enums.TimeSlot;
import domain.repositories.SessionRepository;

import java.util.List;

public class SessionService {

    private final SessionRepository sessionRepository;

    public SessionService(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    public void createSession(Lesson lesson, SessionDate date, TimeSlot timeSlot, int capacity){
        if (sessionRepository.exists(lesson, date, timeSlot)) {
            throw new IllegalStateException
                    ("Session already exists for this lesson, date, and time");
        }


        Session session = new Session(lesson, date, timeSlot,capacity);
        sessionRepository.add(session);    }


    public List<Session> getSessions(){
        return sessionRepository.getAll();
    }

    public List<Session> getSessionsByLesson(Lesson lesson) {
        return sessionRepository.findByLesson(lesson);
    }

    public List<Session> getSessionsByMonth(Month month) {
        return sessionRepository.getAll().stream().filter(session -> session.getSessionDate().getMonth().equals(month)).toList();
    }

    public List<Booking> getAttendedBookings(Session session) {
        return session.getBookings().stream()
                .filter(b -> b.getStatus() == BookingStatus.Attended)
                .toList();
    }

    public List<Session> getSessionsByTheDayOfWeek(DayOfWeek dayOfWeek) {
        return sessionRepository.getAll().stream()
                .filter(s -> s.getSessionDate().getDayOfWeek() == dayOfWeek)
                .toList();
    }

}

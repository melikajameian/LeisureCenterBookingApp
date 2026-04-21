package domain.repositories;

import domain.entities.Lesson;
import domain.entities.Session;
import domain.entities.SessionDate;
import domain.enums.TimeSlot;

import java.util.ArrayList;
import java.util.List;

public class SessionRepository {
    private List<Session> sessions ;

    public void add(Session session) {
        sessions.add(session);
    }

    public List<Session> getAll() {
        return new ArrayList<>(sessions);
    }

    public boolean exists(Lesson lesson, SessionDate date, TimeSlot timeSlot) {
        return sessions.stream()
                .anyMatch(session ->
                        session.getLesson().getLessonType() == lesson.getLessonType()
                                && session.getSessionDate().equals(date)
                                && session.getTimeSlot() == timeSlot
                );
    }
}

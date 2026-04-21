package application.services;

import domain.entities.SessionDate;
import domain.entities.Lesson;
import domain.entities.Session;
import domain.enums.TimeSlot;
import domain.repositories.SessionRepository;

import java.util.ArrayList;
import java.util.List;

public class SessionService {

    private SessionRepository sessionRepository;

    public SessionService(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    public void createSession(Lesson lesson, SessionDate date, TimeSlot timeSlot){
        if (sessionRepository.exists(lesson, date, timeSlot)) {
            throw new IllegalStateException
                    ("Session already exists for this lesson, date, and time");
        }


        Session session = new Session(lesson, date, timeSlot);
        sessionRepository.add(session);    }


    public List<Session> getSessions(){
        return sessionRepository.getAll();
    }

    public List<Session> getSessionsByLesson(Lesson lesson) {
        return sessionRepository.findByLesson(lesson);
    }
}

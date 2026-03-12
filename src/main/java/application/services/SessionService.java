package application.services;

import domain.entities.SessionDate;
import domain.entities.Lesson;
import domain.entities.Session;
import domain.enums.TimeSlot;

import java.util.ArrayList;
import java.util.List;

public class SessionService {
    private final List<Session> sessions ;

    public SessionService() {
        this.sessions = new ArrayList<>();
    }

    public void createSession(Lesson lesson, SessionDate day, TimeSlot timeSlot){
        for(Session session : sessions){
            if(session.getLesson().getLessonType() ==lesson.getLessonType()){
            }
        }

        sessions.add(new Session(lesson, day, timeSlot));
    }


    public List<Session> getSessions(){
        return sessions;
    }
}

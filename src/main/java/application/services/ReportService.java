package application.services;

import domain.entities.Booking;
import domain.entities.Lesson;
import domain.entities.Session;
import domain.enums.Month;

import java.util.ArrayList;
import java.util.List;

public class ReportService {

    private final SessionService sessionService;
    private final LessonService lessonService;

    public ReportService(SessionService sessionService, LessonService lessonService) {
        this.sessionService = sessionService;
        this.lessonService = lessonService;
    }

    public String getMonthlyLessonReport(int monthNumber) {

        Month month = Month.getRateByNumber(monthNumber);

        List<Session> sessions = sessionService.getSessionsByMonth(month);
        List<Lesson> lessons = lessonService.getLessons();

        StringBuilder result = new StringBuilder();

        result.append("\n *** Monthly Lesson Report for month ").append(monthNumber).append("\n");
        for (Lesson lesson : lessons) {

            result.append("Lesson: ")
                    .append(lesson.getLessonType().name())
                    .append("\n   ");

            List<Session> lessonSessions = sessions.stream()
                    .filter(s -> s.getLesson() == lesson)
                    .toList();

            List<Booking> allAttendedBookings = new ArrayList<>();

            for (Session session : lessonSessions) {
                allAttendedBookings.addAll(
                        sessionService.getAttendedBookings(session)
                );
            }

            int totalAttendees = allAttendedBookings.size();

            double averageRating = allAttendedBookings.stream()
                    .mapToInt(b -> b.getReview().getRate().getRateNumber())
                    .average()
                    .orElse(0);

            result.append("Total Attendees: ")
                    .append(totalAttendees)
                    .append("\n   ");

            result.append("Average Rating: ")
                    .append(((int)(averageRating * 100)) / 100.0)
                    .append("\n\n");
        }

        return result.toString();
    }
}

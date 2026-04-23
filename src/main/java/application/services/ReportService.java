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

        Month month = Month.getMonthByNumber(monthNumber);

        List<Session> sessions = sessionService.getSessionsByMonth(month);
        List<Lesson> lessons = lessonService.getLessons();

        StringBuilder result = new StringBuilder();

        result.append("\n *** Monthly Lesson Report: ").append(month).append("\n");
        for (Lesson lesson : lessons) {
            result.append("\n------------------------\n");

            result.append("Lesson: ")
                    .append(lesson.getLessonType().name())
                    .append("\n   ");


            List<Booking> allAttendedBookings = new ArrayList<>();

            int attendees = getAttendees(lesson, sessions,allAttendedBookings);

            double averageRating = allAttendedBookings.stream()
                    .mapToInt(b -> b.getReview().getRate().getRateNumber())
                    .average()
                    .orElse(0);

            result.append("Total Attendees: ")
                    .append(attendees)
                    .append("\n   ");

            result.append("Average Rating: ")
                    .append(((int)(averageRating * 100)) / 100.0)
                    .append("\n\n");
        }

        return result.toString();
    }

    public String getChampionReport(int monthNumber) {

        Month month = Month.getMonthByNumber(monthNumber);

        List<Session> sessions = sessionService.getSessionsByMonth(month);
        List<Lesson> lessons = lessonService.getLessons();

        StringBuilder result = new StringBuilder();

        String championLesson = "";
        double maxIncome = 0;

        result.append("*** Champion Report: ").append(month).append("\n");

        for (Lesson lesson : lessons) {

            List<Booking> allAttendedBookings = new ArrayList<>();

            int attendees = getAttendees(lesson, sessions,allAttendedBookings);

            double income = attendees * lesson.getPrice();

            result.append(lesson.getLessonType().name())
                    .append(" → £")
                    .append(income)
                    .append("\n");

            if (income > maxIncome) {
                maxIncome = income;
                championLesson = lesson.getLessonType().name();
            }
        }

        result.append("\n------------------------\n");
        result.append("Champion Lesson: ").append(championLesson).append("\n");
        result.append("Highest Income: £").append(maxIncome).append("\n");

        return result.toString();
    }

    private int getAttendees(Lesson lesson, List<Session> sessions, List<Booking> allAttendedBookings) {
        List<Session> lessonSessions = sessions.stream()
                .filter(s -> s.getLesson().equals(lesson))
                .toList();


        for (Session session : lessonSessions) {
            allAttendedBookings.addAll(
                    sessionService.getAttendedBookings(session)
            );
        }

        return allAttendedBookings.size();
    }
}

package application.services;

import domain.entities.Booking;
import domain.entities.Lesson;
import domain.entities.Session;
import domain.enums.LessonType;
import domain.enums.Month;

import java.util.*;

public class ReportService {

    private final SessionService sessionService;
    private final LessonService lessonService;

    public ReportService(SessionService sessionService, LessonService lessonService) {
        this.sessionService = sessionService;
        this.lessonService = lessonService;
    }

    public String getMonthlyLessonReport(int monthNumber) {
        Month month = Month.getMonthByNumber(monthNumber);
        if (month == null) {
            return "Invalid month selected.";
        }

        List<Session> sessions = sessionService.getSessionsByMonth(month);
        if (sessions.isEmpty()) {
            return "\n *** Monthly Lesson Report: " + month + "\nNo sessions found.\n";
        }

        List<Session> orderedSessions = sessions.stream()
                .sorted(Comparator
                        .comparingInt((Session s) -> s.getSessionDate().getDayOfWeek().ordinal())
                        .thenComparing(Session::getTimeSlot))
                .toList();

        StringBuilder result = new StringBuilder();
        result.append("\n *** Monthly Lesson Report: ").append(month).append("\n");

        Map<Integer, List<Session>> sessionsByDay = new LinkedHashMap<>();
        for (Session session : orderedSessions) {
            int day = session.getSessionDate().getDayOfWeek().ordinal();
            sessionsByDay.computeIfAbsent(day, key -> new ArrayList<>()).add(session);
        }

        for (List<Session> daySessions : sessionsByDay.values()) {
            Session firstSessionOfDay = daySessions.getFirst();
            result.append("\n")
                    .append(firstSessionOfDay.getSessionDate().getDayOfWeek().name())
                    .append(" ")
                    .append(firstSessionOfDay.getSessionDate().getMonth().monthName)
                    .append(":\n");

            Map<LessonType, List<Booking>> attendedBookingsByLesson = new LinkedHashMap<>();
            for (Session session : daySessions) {
                LessonType lessonType = session.getLesson().getLessonType();
                attendedBookingsByLesson
                        .computeIfAbsent(lessonType, key -> new ArrayList<>())
                        .addAll(sessionService.getAttendedBookings(session));
            }

            for (Map.Entry<LessonType, List<Booking>> entry : attendedBookingsByLesson.entrySet()) {
                List<Booking> attendedBookings = entry.getValue();
                double averageRating = attendedBookings.stream()
                        .filter(b -> b.getReview() != null)
                        .mapToInt(b -> b.getReview().getRate().getRateNumber())
                        .average()
                        .orElse(0);

                result.append(entry.getKey().name())
                        .append(": average => ")
                        .append(((int) (averageRating * 100)) / 100.0)
                        .append(", attendees => ")
                        .append(attendedBookings.size())
                        .append("\n");
            }
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

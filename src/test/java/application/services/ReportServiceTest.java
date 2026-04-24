package application.services;

import domain.entities.*;
import domain.enums.*;
import domain.repositories.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ReportServiceTest {

    @Test
    void getMonthlyLessonReport_shouldAggregateSameLessonAcrossSessionsOnSameDay() {
        LessonService lessonService = new LessonService(new LessonRepository());
        SessionService sessionService = new SessionService(new SessionRepository());
        BookingService bookingService = new BookingService(new BookingRepository());
        ReportService reportService = new ReportService(sessionService, lessonService);

        lessonService.createLesson(LessonType.Yoga, 10);
        Lesson yoga = lessonService.getLesson(LessonType.Yoga);

        Session morning = new Session(yoga, new SessionDate(Month.JUNE, 1), TimeSlot.morning, 4);
        Session evening = new Session(yoga, new SessionDate(Month.JUNE, 1), TimeSlot.evening, 4);
        sessionService.createSession(yoga, morning.getSessionDate(), morning.getTimeSlot(), 4);
        sessionService.createSession(yoga, evening.getSessionDate(), evening.getTimeSlot(), 4);

        Session createdMorning = sessionService.getSessionsByTheDayOfWeek(DayOfWeek.SATURDAY).stream()
                .filter(s -> s.getTimeSlot() == TimeSlot.morning)
                .findFirst().orElseThrow();
        Session createdEvening = sessionService.getSessionsByTheDayOfWeek(DayOfWeek.SATURDAY).stream()
                .filter(s -> s.getTimeSlot() == TimeSlot.evening)
                .findFirst().orElseThrow();

        Member member = new Member("Melika", "Jameian");
        bookingService.create(member, createdMorning);
        bookingService.create(member, createdEvening);

        Booking first = bookingService.getAll().get(0);
        Booking second = bookingService.getAll().get(1);
        first.setReview(new Review("good", Rate.satisfied));
        second.setReview(new Review("ok", Rate.dissatisfied));
        bookingService.markAsAttended(first.getBookingId());
        bookingService.markAsAttended(second.getBookingId());

        String report = reportService.getMonthlyLessonReport(6);

        assertTrue(report.contains("Yoga: average => 3.0, attendees => 2"));
    }
}

package application.services;

import domain.entities.*;
import domain.enums.*;
import domain.repositories.BookingRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookingServiceTest {

    @Test
    void create_shouldStoreBookingAndAttachItToSession() {
        BookingRepository bookingRepository = new BookingRepository();
        BookingService bookingService = new BookingService(bookingRepository);

        Member member = new Member("Amy", "Smith");
        Session session = new Session(
                new Lesson(LessonType.Yoga, 10),
                new SessionDate(Month.JUNE, 1),
                TimeSlot.morning,
                4
        );

        bookingService.create(member, session);

        assertEquals(1, bookingService.getAll().size());
        assertEquals(1, session.getBookings().size());
        assertEquals(BookingStatus.Booked, bookingService.getAll().get(0).getStatus());
    }

    @Test
    void changeBookingsSession_shouldMoveBookingAndMarkStatusChanged() {
        BookingRepository bookingRepository = new BookingRepository();
        BookingService bookingService = new BookingService(bookingRepository);

        Member member = new Member("Amy", "Smith");
        Lesson yoga = new Lesson(LessonType.Yoga, 10);

        Session firstSession = new Session(yoga, new SessionDate(Month.JUNE, 1), TimeSlot.morning, 4);
        Session secondSession = new Session(yoga, new SessionDate(Month.JUNE, 2), TimeSlot.evening, 4);

        bookingService.create(member, firstSession);
        String bookingId = bookingService.getAll().get(0).getBookingId();

        boolean changed = bookingService.changeBookingsSession(bookingId, secondSession);

        assertTrue(changed);
        Booking booking = bookingService.getAll().get(0);
        assertEquals(secondSession, booking.getSession());
        assertEquals(BookingStatus.Changed, booking.getStatus());
        assertTrue(firstSession.getBookings().isEmpty());
        assertEquals(1, secondSession.getBookings().size());
    }
}

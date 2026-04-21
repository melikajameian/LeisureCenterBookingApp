package application.services;

import domain.entities.Booking;
import domain.entities.Member;
import domain.entities.Session;
import domain.repositories.BookingRepository;

import java.util.List;

public class BookingService {


    private BookingRepository bookingRepository;

    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public Booking createBooking(Member member, Session session) {

        Booking booking = new Booking(member);
        booking.setSession(session);
        bookingRepository.add(booking);

        return booking;
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.getAll();
    }
}

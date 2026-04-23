package application.services;

import domain.entities.Booking;
import domain.entities.Member;
import domain.entities.Session;
import domain.enums.BookingStatus;
import domain.repositories.BookingRepository;

import java.util.List;
import java.util.Optional;

public class BookingService {


    private final BookingRepository bookingRepository;

    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public void create(Member member, Session session) {
        if (session.isFull()) {
            throw new IllegalStateException("Session is full");
        }

        Booking booking = new Booking(member,session);

        bookingRepository.add(booking);
        session.addBooking(booking);
    }

    public boolean markAsAttended(String bookingId) {
        Optional<Booking> booking = bookingRepository.findById(bookingId);

        if(booking.isEmpty()){
            return false;
        }

        if (booking.get().getStatus() != BookingStatus.Booked) {
           return false;
        }

        booking.get().setStatus(BookingStatus.Attended);
        bookingRepository.save();
        return true;
    }

    public boolean markAsCanceled(String bookingId,Session session) {
        Optional<Booking> booking = bookingRepository.findById(bookingId);

        if(booking.isEmpty()){
            return false;
        }

        if (booking.get().getStatus() != BookingStatus.Booked) {
            return false;
        }

        booking.get().setStatus(BookingStatus.Cancelled);
        session.removeBooking(booking.get());
        bookingRepository.save();
        return true;
    }

    public boolean changeBookingsSession(String bookingId, Session selectedSession) {
        Optional<Booking> booking = bookingRepository.findById(bookingId);

        if(booking.isEmpty()){
            return false;
        }

        if (booking.get().getStatus() == BookingStatus.Cancelled || booking.get().getStatus() == BookingStatus.Attended) {
            return false;
        }

        booking.get().setStatus(BookingStatus.Changed);
        selectedSession.removeBooking(booking.get());
        bookingRepository.save();
        return true;
    }

    public List<Booking> getByMemberId(String memberId) {
        return bookingRepository.findByMemberId(memberId);
    }

    public List<Booking> getByMemberIdAndStatus(String memberId, BookingStatus status) {
        return bookingRepository.findByMemberIdAndStatus(memberId, status);
    }

    public List<Booking> getAll() {
        return bookingRepository.getAll();
    }

}

package application.services;

import domain.entities.Booking;
import domain.entities.Member;
import domain.entities.Session;
import domain.enums.BookingStatus;
import domain.repositories.BookingRepository;

import java.util.List;

public class BookingService {


    private final BookingRepository bookingRepository;

    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public void create(Member member, Session session) {

        Booking booking = new Booking(member);
        booking.setSession(session);
        bookingRepository.add(booking);

    }

    public boolean markAsAttended(String bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        if(booking==null){
            return false;
        }

        if (booking.getStatus() != BookingStatus.Booked) {
           return false;
        }

        booking.setStatus(BookingStatus.Attended);
        bookingRepository.save();
        return true;
    }

    public boolean markAsCanceled(String bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        if(booking==null){
            return false;
        }

        if (booking.getStatus() != BookingStatus.Booked) {
            return false;
        }

        booking.setStatus(BookingStatus.Cancelled);
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

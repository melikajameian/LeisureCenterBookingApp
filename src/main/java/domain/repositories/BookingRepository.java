package domain.repositories;

import domain.entities.Booking;
import domain.enums.BookingStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookingRepository {
    private final List<Booking> bookings = new ArrayList<>();

    public void add(Booking booking) {
        bookings.add(booking);
    }

    public List<Booking> getAll() {
        return bookings;
    }

    public List<Booking> findByMemberId(String memberId) {
        return bookings.stream()
                .filter(booking ->
                        booking.getMember().getId().equals(memberId))
                .toList();
    }

    public List<Booking> findByMemberIdAndStatus(String memberId, BookingStatus status) {
        return bookings.stream()
                .filter(b -> b.getMember().getId().equals(memberId))
                .filter(b -> b.getStatus() == status)
                .toList();
    }

    public Optional<Booking> findById(String bookingId) {
        return bookings.stream()
                .filter(b -> b.getBookingId().equals(bookingId))
                .findFirst();
    }

    public void save(){
        // no needed to implement in basic level
    }
}

package domain.repositories;

import domain.entities.Booking;

import java.util.ArrayList;
import java.util.List;

public class BookingRepository {
    private List<Booking> bookings = new ArrayList<>();;

    public void add(Booking booking) {
        bookings.add(booking);
    }

    public List<Booking> getAll() {
        return bookings;
    }
}

package domain.entities;

import domain.enums.BookingStatus;

import java.time.LocalDateTime;

public class Booking {
    private String bookingId;
    private Member member;
    private BookingStatus status;
    private Session session;
    private Review review;

    public Booking(Member member,Session session) {
        this.bookingId = generateBookingId(member);
        this.member = member;
        this.session = session;
        this.status = BookingStatus.Booked;
    }

    public String getBookingId() {
        return bookingId;
    }

    public Member getMember() {
        return member;
    }

    public BookingStatus getStatus() {
        return status;
    }

    public void setStatus(BookingStatus status) {
        this.status = status;
    }


    // Generates a unique id for booking
    private static String generateBookingId(Member member) {
        return member.getFirstName().charAt(0)
                + member.getId().substring(0, 2)
                + member.getLastName().charAt(0)
                + LocalDateTime.now().getNano();
    }

    @Override
    public String toString() {
        return member.toString()
                + ": \n       ⤷" + session.toString()
                + " ›››› " + status.toString();
    }
}


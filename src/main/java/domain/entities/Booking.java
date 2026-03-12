package domain.entities;

import domain.enums.BookingStatus;

import java.time.LocalDateTime;

public class Booking {
    private String bookingId;
    private Member member;
    private BookingStatus status;
    private Session session;
    private Review review;

    private Booking() {
    }

    public Booking(Member member, BookingStatus status ) {
        this.bookingId = generateBookingId(member);
        this.member = member;
        this.status = status;
    }

    public Member getMember() {
        return member;
    }

    public BookingStatus getStatus() {
        return status;
    }

    public Session getSession() {
        return session;
    }

    public Review getReview() {
        return review;
    }

    public String getBookingId() {
        return bookingId;
    }

    private static String generateBookingId(Member member) {
        return member.getFirstName().charAt(0) + member.getId().substring(0, 2) + member.getLastName().charAt(0) + LocalDateTime.now().getNano();
    }
}


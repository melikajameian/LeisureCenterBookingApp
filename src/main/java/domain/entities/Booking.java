package domain.entities;

import domain.enums.BookingStatus;

import java.time.LocalDateTime;

public class Booking {
    private int counter = 1;
    public String bookingId;
    Member member;
    BookingStatus status;
    Session session;
    Review review;

    private Booking() {
    }

    public Booking(Member member, BookingStatus status ) {
        this.bookingId = member.firstName.charAt(0) + member.id.substring(0,2) + member.lastName.charAt(0)+ LocalDateTime.now().getNano();
        this.member = member;
        this.status = status;
        counter++;
//        this.session = session;
//        this.review = review;
    }

}


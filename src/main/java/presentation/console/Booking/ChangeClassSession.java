package presentation.console.Booking;

import application.services.BookingService;
import application.services.LessonService;
import application.services.SessionService;
import domain.entities.Booking;
import domain.entities.Member;
import presentation.console.utils.MenuUtils;

import java.util.List;
import java.util.Scanner;

public class ChangeClassSession {
    private final Scanner scanner;

    public ChangeClassSession(Member member, Scanner scanner, BookingService bookingService, LessonService lessonService,SessionService sessionService) {
        this.scanner = scanner;
        run(member, bookingService,sessionService,lessonService);
    }

    public void run(Member member, BookingService bookingService,SessionService sessionService,LessonService lessonService) {
        while (true) {
            List<Booking> memberBookings = MenuUtils.getBookedClassesForThisMember(member,bookingService,scanner);
            if(memberBookings==null) return;

            Booking selectedBooking = MenuUtils.selectBookFromList(memberBookings, scanner);
            if (selectedBooking==null) return;

            new CreateBooking(sessionService, lessonService, scanner, bookingService, member, selectedBooking.getBookingId());

        }


    }

}

package presentation.console.Booking;

import application.services.BookingService;
import domain.entities.Booking;
import domain.entities.Member;
import presentation.console.utils.ConsoleTextUtils;
import presentation.console.utils.MenuUtils;

import java.util.List;
import java.util.Scanner;

public class AttendClassSession {
    private final Scanner scanner;

    public AttendClassSession(Scanner scanner, BookingService bookingService,Member member) {
        this.scanner = scanner;
        run( bookingService,member);
    }

    public void run(BookingService bookingService,Member member) {
            if (member == null) return;

            List<Booking> memberBookings = MenuUtils.getBookedClassesForThisMember(member,bookingService);
            if(memberBookings==null) return;
            Booking selectedBooking = MenuUtils.selectBookingFromList(memberBookings,scanner);
            if (selectedBooking == null) return;
            if(!bookingService.markAsAttended(selectedBooking.getBookingId())){
                ConsoleTextUtils.printInRed("Cannot attend a attended/cancelled/changed booking");
            }
            ConsoleTextUtils.printInGreen("Booking has been successfully attended");


    }
}

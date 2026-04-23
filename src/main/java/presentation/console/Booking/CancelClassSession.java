package presentation.console.Booking;

import application.services.BookingService;
import domain.entities.Booking;
import domain.entities.Member;
import presentation.console.utils.ConsoleTextUtils;
import presentation.console.utils.MenuUtils;

import java.util.List;
import java.util.Scanner;

public class CancelClassSession {
    private final Scanner scanner;

    public CancelClassSession(Member member, Scanner scanner, BookingService bookingService) {
        this.scanner = scanner;
        run(member, bookingService);
    }

    public void run(Member member, BookingService bookingService) {

        while (true) {
            List<Booking> memberBookings = MenuUtils.getBookedClassesForThisMember(member,bookingService,scanner);
            if(memberBookings==null) return;
            Booking selectedBooking = MenuUtils.selectBookFromList(memberBookings, scanner);

            if (selectedBooking==null) return;
            if (!bookingService.markAsCanceled(selectedBooking.getBookingId(), selectedBooking.getSession())) {
                ConsoleTextUtils.printInRed("Cannot cancel an attended/cancelled/changed booking");
            }
            ConsoleTextUtils.printInGreen("Booking has been successfully canceled");
        }


    }

}

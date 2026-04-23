package presentation.console.Booking;

import application.services.BookingService;
import domain.entities.Booking;
import domain.entities.Member;
import domain.entities.Review;
import domain.enums.Rate;
import presentation.console.utils.ConsoleMessages;
import presentation.console.utils.ConsoleTextUtils;
import presentation.console.utils.MenuUtils;

import java.util.List;
import java.util.Scanner;

public class AttendClassSession {
    private final Scanner scanner;

    public AttendClassSession(Scanner scanner, BookingService bookingService, Member member) {
        this.scanner = scanner;
        run(bookingService, member);
    }

    public void run(BookingService bookingService, Member member) {
        if (member == null) return;

        List<Booking> memberBookings = MenuUtils.getBookedClassesForThisMember(member, bookingService);
        if (memberBookings == null) return;
        Booking selectedBooking = MenuUtils.selectBookingFromList(memberBookings, scanner);
        if (selectedBooking == null) return;
        ConsoleTextUtils.printInYellow("Enter a rate number for this session(from 1-5)");
        var rateInput = scanner.nextLine();

        int rateNumber = MenuUtils.parseMenuInput(rateInput, 5);
        if (rateNumber == 0) return;
        if (rateNumber == -1) return;

        ConsoleTextUtils.printInYellow("\n Thank you,Write about your experience in less than 100 characters \n");
        var reviewDescriptionInput = scanner.nextLine();

        if (reviewDescriptionInput.length() > 100) {
            ConsoleMessages.showWrongInputMessage("for less than 100 characters \n");
        } else {
            selectedBooking.setReview(new Review(reviewDescriptionInput, Rate.getRateByNumber(rateNumber)));
            ConsoleTextUtils.printInGreen("\n Your review has been saved successfully \n");
        if (!bookingService.markAsAttended(selectedBooking.getBookingId())) {
            ConsoleTextUtils.printInRed("Cannot attend a attended/cancelled/changed booking");
        }
        ConsoleTextUtils.printInGreen("Booking has been successfully attended");
        }


    }
}

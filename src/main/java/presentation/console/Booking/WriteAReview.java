package presentation.console.Booking;

import application.services.BookingService;
import domain.entities.Booking;
import domain.entities.Member;
import domain.entities.Review;
import domain.enums.Rate;
import presentation.console.ConsoleMessages;
import presentation.console.utils.ConsoleTextUtils;
import presentation.console.utils.MenuUtils;

import java.util.Scanner;

public class WriteAReview {
    public WriteAReview(Member member, BookingService bookingService, Scanner scanner) {
        run(member, bookingService, scanner);
    }

    public void run(Member member, BookingService bookingService, Scanner scanner) {

        while (true) {
            ConsoleMessages.showSelectOptionMessage("Writing review for " + member.toString() + " Classes");

            var memberBookings = bookingService.getByMemberId(member.getId());

            Booking selectedBooking = MenuUtils.selectBookingFromList(memberBookings, scanner);

            if (selectedBooking == null) return;
            bookingService.markAsAttended(selectedBooking.getBookingId());

            if (selectedBooking.getReview() != null) {
                ConsoleTextUtils.printInRed("Sorry, This booking already has a review. \n");
            } else {
                ConsoleTextUtils.printInYellow("Enter a rate number for this session(from 1-5)");
                var rateInput = scanner.nextLine();

                int rateNumber = MenuUtils.catchNumberFormatException(rateInput, 5);
                if (rateNumber == 0) return;
                if (rateNumber == -1) continue;

                ConsoleTextUtils.printInYellow("\n Thank you,Write about your experience in less than 100 characters \n");
                var reviewDescriptionInput = scanner.nextLine();

                if (reviewDescriptionInput.length() > 100) {
                    ConsoleMessages.showWrongInputMessage("for less than 100 characters \n");
                } else {
                    selectedBooking.setReview(new Review(reviewDescriptionInput, Rate.getRateByNumber(rateNumber)));
                    ConsoleTextUtils.printInGreen("\n Your review has been saved successfully \n");
                }

            }

        }

    }
}

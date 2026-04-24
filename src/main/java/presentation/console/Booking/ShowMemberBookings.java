package presentation.console.Booking;

import application.services.BookingService;
import domain.entities.Booking;
import domain.entities.Member;
import presentation.console.utils.ConsoleTextUtils;

public class ShowMemberBookings {
    public ShowMemberBookings(Member member, BookingService bookingService) {
        run(member, bookingService);
    }

    private void run(Member member, BookingService bookingService) {
        var bookings = bookingService.getByMemberId(member.getId());

        if(bookings.isEmpty()){
            ConsoleTextUtils.printInRed("Sorry, there are no bookings " + member.toString());
            return;
        }
        for (Booking booking : bookings) {
            ConsoleTextUtils.printInYellow("\n ~ " + booking.toString());
            if (booking.getReview() != null)
                ConsoleTextUtils.printInYellow(booking.getReview().toString());
        }

    }
}

package presentation.console.Booking;

import application.services.BookingService;
import application.services.MemberService;
import domain.entities.Booking;
import domain.entities.Member;
import domain.enums.BookingStatus;
import presentation.console.ConsoleMessages;
import presentation.console.utils.ConsoleTextUtils;
import presentation.console.utils.MenuUtils;

import java.util.List;
import java.util.Scanner;

public class CancelClassSession {
    private final Scanner scanner;

    public CancelClassSession(MemberService memberService, Scanner scanner, BookingService bookingService) {
        this.scanner = scanner;
        run(memberService, bookingService);
    }

    public void run(MemberService memberService, BookingService bookingService) {
        List<Member> members = memberService.getMembers();
        List<Booking> bookings = bookingService.getAll();

        while (true) {
            MenuUtils.showMemberList(members, "(the member that wants to cancel a class) \n");

            Member member = MenuUtils.findMember(members, scanner);
            if (member == null) return;

            ConsoleMessages.showSelectOptionMessage("Booked classes for" + member.getFirstName());

            List<Booking> memberBookings = bookingService.getByMemberIdAndStatus(member.getId(), BookingStatus.Booked);
            if (memberBookings.isEmpty()) {
                ConsoleTextUtils.printInRed("⚠ ⚠ There are no classes for this member to cancel. ⚠ ⚠");
                return;
            }
            for (Booking booking : memberBookings) {
                System.out.println(memberBookings.indexOf(booking) + 1 + "- " + booking.toString());
            }
            ConsoleMessages.showBackOption();

            String selectedOption = scanner.nextLine();
            var selectedNumber = MenuUtils.catchNumberFormatException(selectedOption, memberBookings.size());
            if(selectedNumber == -1|| selectedNumber==0) {
                return;
            }
            int selectedIndex = selectedNumber -1 ;
            Booking selectedBooking = memberBookings.get(selectedIndex);
            if(!bookingService.markAsCanceled(selectedBooking.getBookingId())){
                ConsoleTextUtils.printInRed("Cannot cancel an attended/cancelled/changed booking");
            }
            ConsoleTextUtils.printInGreen("Booking has been successfully canceled");
        }


    }
}

package presentation.console.Booking;

import application.services.BookingService;
import application.services.MemberService;
import domain.entities.Booking;
import domain.entities.Member;
import domain.entities.Session;
import domain.enums.BookingStatus;
import presentation.console.ConsoleMessages;
import presentation.console.utils.ConsoleTextUtils;
import presentation.console.utils.MenuUtils;

import java.util.List;
import java.util.Scanner;

public class AttendClassSession {
    private final Scanner scanner;

    public AttendClassSession(MemberService memberService, Scanner scanner, BookingService bookingService) {
        this.scanner = scanner;
        run(memberService, bookingService);
    }

    public void run(MemberService memberService, BookingService bookingService) {
        List<Member> members = memberService.getMembers();
        List<Booking> bookings = bookingService.getAll();

        while (true) {
            MenuUtils.showMemberList(members, "(the member that wants to attend a class) \n");

            Member member = MenuUtils.findMember(members, scanner);
            if (member == null) return;

            ConsoleMessages.showSelectOptionMessage("Booked/unattended classes for" + member.getFirstName());

            List<Booking> memberBookings = bookingService.getByMemberId(member.getId());
            if (memberBookings.isEmpty()) {
                ConsoleTextUtils.printInRed("⚠ ⚠ There are no unattended classes for this member ⚠ ⚠");
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
            Booking selectedBooking = bookings.get(selectedIndex);
            if(!bookingService.markAsAttended(selectedBooking.getBookingId())){
                ConsoleTextUtils.printInRed("Cannot attend a attended/cancelled/changed booking");
            }
            ConsoleTextUtils.printInGreen("Booking has been successfully attended");
        }


    }
}

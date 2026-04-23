package presentation.console.utils;

import application.services.BookingService;
import domain.entities.Booking;
import domain.entities.Member;
import domain.enums.BookingStatus;
import org.jetbrains.annotations.Nullable;
import presentation.console.ConsoleMessages;

import java.util.List;
import java.util.Scanner;

public class MenuUtils {

    public static int catchNumberFormatException(String userInput, int listSize) {
        int number = -1;
        try {
            number = Integer.parseInt(userInput);

            if (number < 0 || number > listSize) {
                ConsoleMessages.showWrongInputMessage("a number between options available");
                return -1;
            }
        } catch (NumberFormatException e) {
            ConsoleMessages.showWrongInputMessage("a number");
        }


        return number;
    }

    public static @Nullable Member findMember(List<Member> members, Scanner scanner) {
        String inputString = scanner.nextLine();
        var inputNumber = MenuUtils.catchNumberFormatException(inputString, members.size());
        if (inputNumber == -1 || inputNumber == 0) {
            return null;
        }
        int memberIndex = inputNumber - 1;
        return members.get(memberIndex);
    }

    public static void showMemberList(List<Member> members, String intro) {
        ConsoleMessages.showSelectOptionMessage("Member");
        System.out.println(intro);
        for (Member m : members) {
            System.out.println(members.indexOf(m) + 1 + " - " + (m.toString()));
        }
        ConsoleMessages.showBackOption();
    }

    public static @Nullable Booking selectBookingFromList(List<Booking> memberBookings, Scanner scanner) {
        showBookingList(memberBookings);

        String selectedOption = scanner.nextLine();
        var selectedNumber = MenuUtils.catchNumberFormatException(selectedOption, memberBookings.size());
        if (selectedNumber == -1 || selectedNumber == 0) {
            return null;
        }
        int selectedIndex = selectedNumber - 1;
        return memberBookings.get(selectedIndex);
    }

    public static void showBookingList(List<Booking> memberBookings) {
        for (Booking booking : memberBookings) {
            System.out.println(memberBookings.indexOf(booking) + 1 + "- " + booking.toString());
        }
        ConsoleMessages.showBackOption();
    }


    public static List<Booking> getBookedClassesForThisMember(Member member, BookingService bookingService, Scanner scanner) {
        if (member == null) return null;

        ConsoleMessages.showSelectOptionMessage("Booked classes for" + member.toString());

        List<Booking> memberBookings = bookingService.getByMemberIdAndStatus(member.getId(), BookingStatus.Booked);
        List<Booking> changedBookings = bookingService.getByMemberIdAndStatus(member.getId(), BookingStatus.Changed);
        if (!changedBookings.isEmpty()) {
            memberBookings.addAll(changedBookings);
        }

        if (memberBookings.isEmpty()) {
            ConsoleTextUtils.printInRed("⚠ ⚠ There are no classes for this member to cancel/change. ⚠ ⚠");
            return null;
        }
        return memberBookings;
    }
}

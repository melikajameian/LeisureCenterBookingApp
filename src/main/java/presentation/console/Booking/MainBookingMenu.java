package presentation.console.Booking;

import application.services.BookingService;
import application.services.LessonService;
import application.services.MemberService;
import application.services.SessionService;
import domain.entities.Member;
import presentation.console.utils.ConsoleMessages;
import presentation.console.utils.MenuUtils;

import java.util.Scanner;

public class MainBookingMenu {
    private final Scanner scanner;
    private final SessionService sessionService;
    private final LessonService lessonService;
    private final BookingService bookingService;
    private final MemberService memberService;

    public MainBookingMenu(SessionService sessionService, LessonService lessonService, Scanner scanner,
                           BookingService bookingService, MemberService memberService) {
        this.sessionService = sessionService;
        this.lessonService = lessonService;
        this.bookingService = bookingService;
        this.memberService = memberService;
        this.scanner = scanner;
        showBookingMenu();
    }

    public void showBookingMenu() {
            Member member = selectMember();

            if (member == null) return;
            while (true) {
            ConsoleMessages.showSelectOptionMessage("Booking Menu");
            System.out.println("1- Book a class\n2- Attend a class\n3- Change a class\n4- Cancel a class\n5- Write a review");
            ConsoleMessages.showBackOption();
            String bookingMenuInput = scanner.nextLine();
            switch (bookingMenuInput) {
                case "1":
                    new CreateBooking(sessionService, lessonService, scanner, bookingService, member,null);
                    break;
                case "2":
                    new AttendClassSession(scanner, bookingService, member);
                    break;
                case "3":
                    new ChangeClassSession(member,scanner, bookingService,lessonService,sessionService);
                    break;
                case "4":
                    new CancelClassSession(member,scanner, bookingService);
                    break;
                case "5":
                    new WriteAReview(member,bookingService,scanner);
                    break;
                case "0":
                    return;
                default:
                    ConsoleMessages.showWrongInputMessage("between 1-5");
            }
        }
    }

    private Member selectMember() {
        var members = memberService.getMembers();
        MenuUtils.showMemberList(members, "(member you are managing bookings for) \n");

        return MenuUtils.findMember(members, scanner);
    }


}

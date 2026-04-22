package presentation.console;

import application.services.BookingService;
import application.services.LessonService;
import application.services.MemberService;
import application.services.SessionService;
import presentation.console.Booking.MainBookingMenu;

import java.util.Scanner;

public class MainMenu {
    private final Scanner scanner;
    private final SessionService sessionService;
    private final LessonService lessonService;
    private final BookingService bookingService;
    private final MemberService memberService;


    public MainMenu(Scanner scanner, SessionService sessionService, LessonService lessonService,
                     MemberService memberService, BookingService bookingService) {
        this.scanner = scanner;
        this.lessonService = lessonService;
        this.sessionService = sessionService;
        this.bookingService = bookingService;
        this.memberService = memberService;

    }

    public void start() {
        ConsoleMessages.showWelcomingText();

        while (true){
            ConsoleMessages.showSelectOptionMessage("");
            System.out.println("1- Booking menu\n2- Report menu");
            String input = scanner.nextLine();


            if(input.equals("1")) {
                new MainBookingMenu(sessionService,lessonService,scanner,bookingService,memberService);
            }
            else if(input.equals("2")) {
                ReportMenu.showReportMenu();
                break;
            }
            else
                ConsoleMessages.showWrongInputMessage("1 or 2");
        }
    }


}

package presentation.console;

import application.services.*;
import presentation.console.Booking.MainBookingMenu;
import presentation.console.Report.ReportMainMenu;
import presentation.console.utils.ConsoleMessages;

import java.util.Scanner;

public class MainMenu {
    private final Scanner scanner;
    private final SessionService sessionService;
    private final LessonService lessonService;
    private final BookingService bookingService;
    private final MemberService memberService;
    private final ReportService reportService;


    public MainMenu(Scanner scanner, SessionService sessionService, LessonService lessonService,
                    MemberService memberService, BookingService bookingService, ReportService reportService) {
        this.scanner = scanner;
        this.lessonService = lessonService;
        this.sessionService = sessionService;
        this.bookingService = bookingService;
        this.memberService = memberService;
        this.reportService = reportService;
    }

    public void start() {
        ConsoleMessages.showWelcomingText();

        while (true) {
            ConsoleMessages.showSelectOptionMessage("Main Menu");
            System.out.println("1- Booking menu\n2- Report menu");
            ConsoleMessages.showExitOption();
            String input = scanner.nextLine();


            if (input.equals("1")) {
                new MainBookingMenu(sessionService, lessonService, scanner, bookingService, memberService);
            } else if (input.equals("2")) {
                new ReportMainMenu(scanner, reportService);
            } else if (input.equals("0")) {
                System.out.println("Goodbye!");
                return;
            } else
                ConsoleMessages.showWrongInputMessage("1, 2 or 0 (exit)");
        }
    }


}

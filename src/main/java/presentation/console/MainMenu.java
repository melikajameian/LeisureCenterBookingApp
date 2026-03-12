package presentation.console;

import application.services.LessonService;
import application.services.SessionService;
import domain.entities.Session;

import java.util.Scanner;

public class MainMenu {
    private final Scanner scanner;
    private final SessionService sessionService;
    private final LessonService lessonService;
    private final BookingMenu bookingMenu;


    public MainMenu(Scanner scanner, SessionService sessionService, LessonService lessonService, BookingMenu bookingMenu) {
        this.scanner = scanner;
        this.lessonService = lessonService;
        this.sessionService = sessionService;
        this.bookingMenu = bookingMenu;

    }

    public void start() {
        ConsoleMessages.showWelcomingText();

        while (true){
            ConsoleMessages.showSelectOptionMessage("");
            System.out.println("1- Booking menu\n2- Report menu");
            String input = scanner.nextLine();


            if(input.equals("1")) {
                bookingMenu.showBookingMenu();
                break;
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

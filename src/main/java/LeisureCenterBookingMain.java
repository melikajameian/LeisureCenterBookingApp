import application.services.LessonService;
import application.services.MemberService;
import application.services.SessionService;
import infrastructure.factories.DataFactory;
import presentation.console.BookingMenu;
import presentation.console.MainMenu;

import java.util.Scanner;


public class LeisureCenterBookingMain {
    public static void main(String[] args) {
        LessonService lessonService = new LessonService();
        MemberService memberService = new MemberService();
        SessionService sessionService = new SessionService();

        DataFactory.initialize(lessonService,memberService,sessionService);

        Scanner scanner = new Scanner(System.in);
        BookingMenu bookingMenu = new BookingMenu(sessionService,lessonService,scanner);
        MainMenu mainMenu = new MainMenu(scanner,sessionService,lessonService,bookingMenu);


        mainMenu.start();

        scanner.close();
    }
}

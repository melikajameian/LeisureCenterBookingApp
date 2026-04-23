import application.services.*;
import domain.repositories.BookingRepository;
import domain.repositories.LessonRepository;
import domain.repositories.MemberRepository;
import domain.repositories.SessionRepository;
import infrastructure.factories.DataFactory;
import presentation.console.MainMenu;

import java.util.Scanner;


public class LeisureCenterBookingMain {
    public static void main(String[] args) {
        LessonRepository lessonRepository = new LessonRepository();
        MemberRepository memberRepository = new MemberRepository();
        SessionRepository sessionRepository = new SessionRepository();
        BookingRepository bookingRepository = new BookingRepository();

        LessonService lessonService = new LessonService(lessonRepository);
        MemberService memberService = new MemberService(memberRepository);
        SessionService sessionService = new SessionService(sessionRepository);
        BookingService bookingService = new BookingService(bookingRepository);
        ReportService reportService = new ReportService(sessionService,lessonService);


        DataFactory.initialize(lessonService, memberService, sessionService);

        Scanner scanner = new Scanner(System.in);
        MainMenu mainMenu = new MainMenu(scanner, sessionService, lessonService, memberService, bookingService ,reportService);


        mainMenu.start();

        scanner.close();
    }
}
